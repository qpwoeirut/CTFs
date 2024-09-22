#include <stdio.h>
#include <string.h>

int main() {
    int len = strlen("\x0e\x13\t\x1d\x13\x04");
    long long x = 0x4131d09130e00;
    char* str = &x;
    for (int i=0; i<7; ++i) printf("%d\n", str[i]);
    int res = strcmp((char *)&x, "\x0e\x13\t\x1d\x13\x04");
    printf("%d %d\n", res, len);
    return 0;
}