
int main(int argc,char **argv)

{
  _Bool _Var1;
  int iVar2;
  time_t tVar3;
  size_t sVar4;
  char **argv-local;
  int argc-local;
  _Bool auth;
  size_t len;

  tVar3 = time((time_t *)0x0);
  srand((uint)tVar3);
  if (argc < 2) {
    printf("Usage: %s <key_value>\n",*argv);
    iVar2 = -1;
  }
  else {
    sVar4 = strlen(argv[1]);
    _Var1 = check_password(argv[1],(long)(int)sVar4);
    if (_Var1) {
      puts(&DAT_00102065);
      iVar2 = 0;
    }
    else {
      iVar2 = rand();
      printf("%s",fail_msgs[iVar2 % 0xe]);
      iVar2 = -1;
    }
  }
  return iVar2;
}



_Bool check_password(char *passwd,size_t len)

{
  char cVar1;
  size_t sVar2;
  size_t len-local;
  char *passwd-local;
  int result;
  int i;

  result = 0;
  for (i = 0; (ulong)(long)i < len; i = i + 1) {
    cVar1 = passwd[i];
    sVar2 = strlen(enc_flag);
    result = result | (uint)sVar2 ^ (int)cVar1 ^ (uint)len;
    printf("%d",(ulong)(uint)result);
  }
  printf("%d\n",(ulong)(uint)result);
  return result == 0;
}

