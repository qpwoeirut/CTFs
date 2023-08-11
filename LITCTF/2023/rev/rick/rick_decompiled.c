void main(void) {
    char target[44];
    char str[73];

    puts("wat is flag");
    scanf(&DAT_00402010, str + 1);
    for (int i = 0; i * 2 < strlen(str + 1); i++) {
        char c = str[i + 1];
        str[i + 1] = str[strlen(str + 1) - i];
        str[strlen(str + 1) - i] = c;
    }
    memcpy(target, rick + 0x14785,0x2c);  // s_}1l0rkc1r_7xen_3ht_3k4m_4nn0g_7p_00418805
    bool different = strcmp(str + 1, target);
    if (different == 0) {
        puts("ur right");
    }
    else {
        puts("ur wrong");
    }
    return;
}

// Use gdb to find the actual value of the target string
// run `x/s` on the memory address
// LITCTF{ch4tgp7_g0nn4_m4k3_th3_nex7_r1ckr0l1}
