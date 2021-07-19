int main(void) {
// 0x25f839810977f55 / 0xab = 999999999999999
// 0x39 is factor of 0xab
// xor cancels out so we can probably just patch the big constant to 0xab
// constant is at 0x130b
    for (long i = 0; i < 0x25f839810977f55; i++) {
        flag[i % 0x39] = flag[i % 0x39] ^ orz[i % 0xab];  // only line that matters
        printf("Loading flag");
        for (long j = 0; j <= i % 3; j++) {
            putchar(0x2e);
        }
        long x = 0;
        while ( true ) {
        if (3 - i % 3 <= x) break;
            putchar(0x20);
            x++;
        }
        printf("(Time remaining: %lld seconds)                   \r",0x25f839810977f55 - local_10);
        fflush(stdout);
        sleep(1);
    }
    printf("\n%s\n",flag);
    return 0;
}