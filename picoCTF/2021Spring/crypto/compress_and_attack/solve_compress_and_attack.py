# https://github.com/EiNSTeiN-/compression-oracle/blob/master/src/compression_oracle.py

import random
import threading
import time
from typing import Callable


class TwoTriesGuess(object):
    def __init__(self, oracle, prefix: str, letter: str, complement: str):
        """ Create Guess object.
        `prefix` is a known prefix to prepend with the guessed letter.
        `letter` is the new guessed letter.
        `complement` the alphabet complement to use for detecting false-positives.
        """
        self.oracle = oracle
        self.prefix = prefix
        self.letter = letter
        self.complement = complement

        self.good_length: int or None = None  # length of the presumed good guess
        self.bad_length: int or None = None  # length of the presumed bad guess

    def __len__(self):
        return self.good_length

    def __str__(self):
        return self.prefix + self.letter

    def __repr__(self):
        return f"{self.__class__.__name__}({repr(self.prefix)}+{repr(self.letter)}->{'unknown length' if self.good_length is None else f'{self.good_length}, {self.bad_length}'})"

    def keep(self):
        """ return True if the good guess should be kept. """
        return self.good_length < self.bad_length

    def discard(self):
        """ return True if the good guess should be discarded. """
        return self.good_length > self.bad_length

    def run(self):
        """ implement the logic behind determining the length of this guess """

        # run it!
        self.good_length = self.oracle.oracle(self.prefix + self.letter + self.complement)
        self.bad_length = self.oracle.oracle(self.prefix + self.complement + self.letter)


class TwoTriesBlockCipherGuess(TwoTriesGuess):
    def range(self):
        """ Default range for 8 or 16 bytes block cipher. """
        return range(0, 20)

    def guesses(self, uncompressible_bytes: str):
        good = self.oracle.oracle(uncompressible_bytes + self.prefix + self.letter + self.complement)
        bad = self.oracle.oracle(uncompressible_bytes + self.prefix + self.complement + self.letter)
        return good, bad

    def run(self):
        """ implement the logic behind determining the length of this guess """
        # print('guess', self.prefix, self.letter)

        ref = ref_good, ref_bad = self.guesses(uncompressible_bytes='')
        if ref_good != ref_bad:
            # print('keep ref', repr(ref))
            self.good_length, self.bad_length = ref
            return

        for n in self.range():
            bytes = self.oracle.get_uncompressible_bytes(n)
            this = this_good, this_bad = self.guesses(uncompressible_bytes=bytes)
            if this_good != ref_good or this_bad != ref_bad:
                self.good_length, self.bad_length = this
                # print('keep this', n, repr(ref), '->', repr(this))
                return

        # print('not found')
        return


class CompressionOracleRunner(threading.Thread):
    def __init__(self, guess):
        threading.Thread.__init__(self)
        self.guess = guess

    def run(self):
        return self.guess.run()


class CompressionOracle(object):
    def __init__(self, seed, alphabet, max_threads=1, complement_size=None, retries=5, lookaheads=1,
                 guess_provider=TwoTriesGuess):
        if complement_size is None:
            complement_size = [20, 200]
        assert max_threads > 0, 'max_threads cannot be <= 0'
        self.seed = seed
        self.max_threads = max_threads
        self.alphabet = alphabet
        self.retries = retries
        self.lookaheads = lookaheads
        self.complement_size = complement_size

        self.__tries = []

        self.retrieved_guesses = None
        self.guess_provider = guess_provider

    def oracle(self, data):
        """ Must be overridden by subclasses and implement the logic to query the compression oracle. """
        raise NotImplemented('oracle() must be overridden')

    def prepare(self):
        """ May be overridden by subclasses and make any initialization before running the attack. """
        return

    def cleanup(self):
        """ May be overridden by subclasses and cleanup any ressources after running the attack. """
        return

    def get_uncompressible_bytes(self, length) -> str:
        """ This must return a string which should not compress well. It could
          be random data or it could be a sequence of bytes guaranteed to not
          contain any repetition.
          Subclasses can override this method, the default implementation
          returns a random string of letters complementary to the alphabet.
        """

        possible_complement = bytearray(list(range(256)))
        possible_complement.translate(possible_complement, self.alphabet)
        if len(possible_complement) == 0:
            possible_complement = bytearray(list(range(256)))

        return ''.join([chr(random.choice(possible_complement)) for _ in range(length)])

    def prepare_complement(self) -> str:
        """ Prepare an alphabet complement for the Two-Tries method. """

        if type(self.complement_size) in (list, tuple):
            size = random.randint(*self.complement_size)
        else:
            size = self.complement_size

        # TODO make sure this works
        possible_complement = ''.join([chr(i) for i in range(256) if chr(i) not in self.alphabet])
        if len(possible_complement) != 0:
            return ''.join(random.sample(possible_complement, 2) * size)

        return ''.join([chr(random.randint(0, 0xff)) for _ in range(2)] * size)

    def __run_all(self, guesses):

        threads = []
        queue = guesses[:]

        while len(queue) > 0:
            # start as many threads as permitted
            for i in range(self.max_threads - len(threads)):
                g = queue.pop(0)

                t = CompressionOracleRunner(g)
                t.start()
                threads.append(t)

                if len(queue) == 0:
                    break
            # print(f'currently {len(threads)} threads')
            # wait for some threads to finish
            while True:
                threads = [t for t in threads if t.is_alive()]
                if len(threads) < self.max_threads:
                    break
                time.sleep(0.1)
        # wait for remaining threads to finish
        while len(threads) > 0:
            threads = [t for t in threads if t.is_alive()]

        kept = [g for g in guesses if g.keep()]
        return kept

    def run(self):
        """ run the attack against the compression oracle. """

        complement = self.prepare_complement()
        guesses = [self.seed]

        self.prepare()

        retry = 0
        round = 0
        lookahead = 0
        while True:

            # append each letter in the keyspace to our current tries.
            old_guesses, guesses = guesses, []
            for guess in old_guesses:
                for letter in self.alphabet:
                    if lookahead > 0:
                        guesses.append(self.guess_provider(self, guess.prefix, guess.letter + letter, complement))
                    else:
                        guesses.append(self.guess_provider(self, str(guess), letter, complement))

            start_time = time.time()

            # testing phase
            kept = self.__run_all(guesses)

            # print(f'in round {round}, ran all {len(guesses)} guesses in {time.time() - start_time} seconds')

            # print(repr(kept))
            if len(kept) == 0:
                print(f"couldn't guess the next letter after {repr([str(g) for g in old_guesses])}")
                if retry >= self.retries:
                    if lookahead >= self.lookaheads:
                        print(f'stopping after {self.lookaheads} lookahead')
                        break
                    else:
                        # when performing lookahead, do not keep the known bad guesses.
                        guesses = [guess for guess in guesses if not guess.discard()]
                        lookahead += 1
                        retry = 0
                        print(
                            f'performing lookahead ({lookahead}/{self.lookaheads}) with {len(guesses)} potential candidates')
                        continue
                else:
                    retry += 1
                    print(f'changing complement ({retry}/{self.retries}) and retrying with old guesses.')
                    guesses = old_guesses
                    complement = self.prepare_complement()
                    continue
            else:
                retry = 0
                lookahead = 0

            _min = min([len(g) for g in kept])
            # print(f'keeping guesses with minimal length: {_min}')

            # switch over the new guesses
            guesses = [g for g in kept if len(g) == _min]
            if len(guesses) > 0:
                print(
                    f'after round #{round}, kept: {repr(guesses[0].prefix)}+{repr([guess.letter for guess in guesses])}')

            self.retrieved_guesses = guesses

            round += 1

        self.cleanup()

        return self.retrieved_guesses


from pwn import remote, process
import string

r = remote("mercury.picoctf.net", 2431)

# r = process(["/Users/Stanley/CTF/venv/bin/python3.8", "/Users/Stanley/CTF/CTFs/picoCTF/2021Spring/crypto/compress_and_attack/compress_and_attack.py"])


class Solver(CompressionOracle):
    def __init__(self, seed):
        CompressionOracle.__init__(self, seed=seed,
                                   alphabet=string.ascii_letters + '_{}', max_threads=1,
                                   lookaheads=1, retries=2)

    def prepare_complement(self) -> str:
        return '0123456789!@#$%^&*()'

    def oracle(self, data):
        global r
        try:
            r.sendline(data.encode())
            r.recvline()  # print(r.recvline().strip().decode())
            r.recvline()  # print(r.recvline().strip().decode())
            L = int(r.recvline().decode()) - 8
            print(data, L)
            return L
        except EOFError:
            r = remote("mercury.picoctf.net", 2431)
            return self.oracle(data)


# Solver("picoCTF").run()
Solver("picoCTF{sherif").run()
# picoCTF{sheriff_you_solved_the_crime}