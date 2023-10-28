int main(int argc, char **argv) {
  int return_value;
  srand(time(NULL));  // was originally (time_t *)0x0
  if (argc < 2) {
    printf("Usage: %s <key_value>\n", *argv);
    return_value = -1;
  }
  else {
    size_t len = strlen(argv[1]);
    bool ok = check_password(argv[1], len);
    if (ok) {
      puts(&DAT_00102065);
      return_value = 0;
    }
    else {
      return_value = rand();
      printf("%s",fail_msgs[return_value % 0xe]);
      return_value = -1;
    }
  }
  return return_value;
}

bool check_password(char *passwd, size_t len) {
  int result = 0;
  for (int i = 0; i < len; i++) {
    char c = passwd[i];
    size_t enc_len = strlen(enc_flag);
    result |= enc_len ^ c ^ len;
    printf("%d", result);
  }
  printf("%d\n", result);
  return result == 0;
}

