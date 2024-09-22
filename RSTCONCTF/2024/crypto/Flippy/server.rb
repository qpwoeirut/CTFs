#!/usr/local/bin/ruby

require 'openssl'
require 'io/console'
$stdout.sync = true

FLAG = ENV['FLAG'] || 'MetaCTF{test_flag}'

def generate_random_key
  OpenSSL::Cipher.new('AES-128-CBC').random_key
end

def encrypt(msg, key)
  cipher = OpenSSL::Cipher.new('AES-128-CBC')
  cipher.encrypt
  iv = cipher.random_iv
  cipher.key = key
  encrypted = cipher.update(msg) + cipher.final
  iv + encrypted
end

def decrypt(ct, key)
  decipher = OpenSSL::Cipher.new('AES-128-CBC')
  decipher.decrypt
  decipher.key = key
  iv = ct[0..15]
  encrypted_msg = ct[16..-1]
  decipher.iv = iv
  decipher.update(encrypted_msg) + decipher.final
end

def main
  key = generate_random_key

  while true
    puts '1. Request print command'
    puts '2. Execute print command'
    print '> '
    choice = STDIN.gets.chomp

    case choice.strip
    when '1'
      print 'Enter a message: '
      msg = STDIN.gets.chomp
      if !msg.match(/\A[a-zA-Z0-9\.!]*\Z/)
        puts 'Invalid message!'
      else
        encrypted_msg = encrypt("puts '#{msg}'", key)
        puts "Encrypted message: #{encrypted_msg.unpack('H*')[0]}"
      end
    when '2'
      print 'Enter an encrypted command (hex): '
      enc_cmd = STDIN.gets.chomp
      cmd = decrypt([enc_cmd].pack('H*'), key)
      eval cmd
    else
      puts 'Invalid choice.'
    end
  end
end

main
