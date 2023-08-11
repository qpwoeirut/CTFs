from time import sleep

flag = "LITCTF{g00d_j0b_ur_d1d_1t}"
passwd = "this is not it, but please try again!"

print("Welcome to the flag access point.")
print("Press Ctrl+C to quit.")

try:
    while True:
        user_input = input("Please enter your password: ")
        print("Loading...")
        sleep(0.5)
        print("Busy bamboozling some spam...")
        sleep(2)
        if user_input == passwd:
            print("Nice one!")
            print(flag)
        else:
            print("Oops.")
            print("Try again.")
except KeyboardInterrupt:
    print("Bye! :-)")