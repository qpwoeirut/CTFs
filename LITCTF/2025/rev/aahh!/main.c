DATA = 4a415a4f4a586983835e6123217f6764796764664f2f50453b573a4d21471f2f593a40232841424542d8d4d4011bce20d7d9dbdac80d00

int main() {
  char A[264];
  
  gets(A);
  for (int i = 0; i < 0x100; i++) {
    A[i] = A[i] ^ i * 3 + 6U;
    A[i] += i;
  }
  int result = strncmp(A, data, 0x36);
  if (result == 0) {
    puts("win");
  }
  else {
    puts("lose");
  }
}

