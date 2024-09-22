void xor_encrypt(char *s, char *t) {
    size_t n_s = strlen(s);
    for (ulong i = 0; i < n_s; i++) {
        char tmp = s[i];
        size_t n_t = strlen(t);
        s[i] = t[i % n_t] ^ tmp;
    }
}

void add_encrypt(int arr[], ulong n, int x) {
    for (ulong i = 0; i < n; i++) {
        arr[i] = (arr[i] + x) % 10;
    }
}

void reverse_encrypt(char *s) {
    size_t n = strlen(s);
    for (ulong i = 0; i < n >> 1; i++) {
        char tmp = s[i];
        s[i] = s[(n - i) - 1];
        s[(n - i) - 1] = tmp;
    }
}

int lookup_encrypt(int idx) {
    int arr[10] = { 3, 1, 4, 1, 5, 9, 2, 6, 5, 3};
    assert(0 <= idx && idx <= 9);
    return arr[idx];
}


int main() {
    size_t sVar2;

    uint a, b, c, d;

    int arr1[4] = { 9, 7, 1, 3 };;
    int arr2[4];

    puts("Welcome to the Nuclear Power Plant Keypad Challenge!");
    puts("Security Check 1: Access Code Verification");

    char* str1;
    fgets(str1, 0x30, stdin);
    sVar2 = strcspn(str1, "\n");
    str1[sVar2] = 0;

    char* target1 = "\x04\x13\x1d\t\x13\x0e";
    xor_encrypt(str1, "power");
    if (strcmp(target1, str1) == 0) {
        puts("Access Code Verified!");
        puts("Security Check 2: Numeric Keypad Entry");
        scanf("%d %d %d %d", &a, &b, &c, &d);
        arr2 = { a, b, c, d };
        add_encrypt(arr2, 4, 4);
        for (ulong i = 0; i < 4; i++) {
            if (arr1[i] != arr2[i]) {
                puts("Access Denied: Numeric Entry Incorrect.");
                return 1;
            }
        }

        int ch;
        do {
            ch = getchar();
        } while (ch != 10 && ch != EOF);
        puts("Numeric Keypad Entry Verified!");
        puts("Security Check 3: Reversed Passphrase");

        char* str2;
        fgets(str2, 0x30, stdin);
        sVar2 = strcspn(str2, "\n");
        str2[sVar2] = 0;
        reverse_encrypt(&str2);
        if (strcmp("reverse", str2) == 0) {
            puts("Passphrase Verified!");
            puts("Security Check 4: Lookup Table Validation");
            int inp;
            scanf("%d", &inp);
            if (lookup_encrypt(inp) == 9) {
                puts("Lookup Table Validated!");
                char xor[1032];
                snprintf(xor,0x400,"%s%d%d%d%d%s%d","padlock", a, b, c, d, str2, inp);

                char* buf = "e\x10- /02\"4:\x19\x10\nUSR\x12\x08\x01\x12\x03\x01\x02\x1c";
                xor_encrypt(&buf, xor);
                printf("Flag: %s\n", &buf);
                return 0;
            }
            else {
                puts("Access Denied: Lookup Table Mismatch.");
                return 1;
            }
        }
        else {
            puts("Access Denied: Incorrect Passphrase.");
            return 1;
        }
    }
    else {
        puts("Access Denied: Incorrect Code.");
        return 1;
    }
}
