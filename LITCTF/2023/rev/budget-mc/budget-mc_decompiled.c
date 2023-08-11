void FUN_00101249(void) {
  char cVar1;
  long in_FS_OFFSET;
  char local_31;
  char local_30;
  char local_2f;
  char local_2e;
  char local_2d;
  int local_2c;
  int local_28;
  uint local_24;
  uint local_20;
  int local_1c;
  FILE *local_18;
  long local_10;

  local_10 = *(long *)(in_FS_OFFSET + 0x28);
  setbuf(stdin,(char *)0x0);
  setbuf(stdout,(char *)0x0);
  local_18 = fopen("flag.txt","r");
  if (local_18 == (FILE *)0x0) {
    puts("Uh oh! Missing flag.txt file. If this is on the server please contact admin.");
                    /* WARNING: Subroutine does not return */
    exit(1);
  }
  fgets(&DAT_00104160,0x22,local_18);
  puts("Welcome to budget-mc. Have fun!");
  for (local_2c = 0; local_2c < 2; local_2c = local_2c + 1) {
    for (local_28 = 0; local_28 < 0x19; local_28 = local_28 + 1) {
      (&DAT_00104060)[(long)local_2c * 0x19 + (long)local_28] = 1;
    }
  }
LAB_0010133c:
  puts("Your world:");
  for (local_24 = 9; -1 < (int)local_24; local_24 = local_24 - 1) {
    for (local_20 = 0; (int)local_20 < 0x19; local_20 = local_20 + 1) {
      if ((local_24 == DAT_0010401c) && (local_20 == DAT_00104020)) {
        putchar(0x58);
      }
      else {
        cVar1 = (&DAT_00104060)[(long)(int)local_24 * 0x19 + (long)(int)local_20];
        putchar((int)s__#!@$%^&*+_00104010
                     [((char)(cVar1 + (((char)((uint)(ushort)(short)cVar1 * 0x67 >> 8) >> 2) -
                                      (cVar1 >> 7)) * -10) + 10) % 10]);
      }
    }
    putchar(10);
  }
  printf("You are currently at position (%d, %d), standing on block id %d.\n",(ulong)DAT_00104020,
         (ulong)DAT_0010401c,
         (ulong)(uint)(int)(char)(&DAT_00104060)
                                 [(long)(int)DAT_0010401c * 0x19 + (long)(int)DAT_00104020]);
  printf(">>> ");
  __isoc99_scanf(&DAT_001020df,&local_31);
  if (local_31 == 'q') {
    if (local_10 == *(long *)(in_FS_OFFSET + 0x28)) {
      return;
    }
    __stack_chk_fail();
  }
  if (local_31 == 'm') {
    __isoc99_scanf(&DAT_001020e3,&local_2f);
    if (local_2f == 'l') {
      if ((&DAT_00104060)[(long)(int)DAT_0010401c * 0x19 + (long)(int)(DAT_00104020 - 1)] != '\x01')
      {
        DAT_00104020 = DAT_00104020 - 1;
        goto LAB_00101959;
      }
      goto LAB_0010133c;
    }
    if ((local_2f != 'r') ||
       ((&DAT_00104060)[(long)(int)DAT_0010401c * 0x19 + (long)(int)(DAT_00104020 + 1)] == '\x01'))
    goto LAB_0010133c;
    DAT_00104020 = DAT_00104020 + 1;
  }
  else {
    if (local_31 == 'j') goto code_r0x001015c7;
    if (local_31 == 'b') {
      __isoc99_scanf(&DAT_001020e3,&local_2f);
      if (local_2f == 'l') {
        (&DAT_00104060)[(long)(int)DAT_0010401c * 0x19 + (long)(int)(DAT_00104020 - 1)] = 0;
      }
      else {
        if (local_2f == 'r') {
          (&DAT_00104060)[(long)(int)DAT_0010401c * 0x19 + (long)(int)(DAT_00104020 + 1)] = 0;
          goto LAB_00101959;
        }
        if (local_2f == 'u') {
          (&DAT_00104060)[(long)(int)(DAT_0010401c + 1) * 0x19 + (long)(int)DAT_00104020] = 0;
        }
        else {
          if (local_2f != 'd') goto LAB_0010133c;
          (&DAT_00104060)[(long)(int)(DAT_0010401c - 1) * 0x19 + (long)(int)DAT_00104020] = 0;
        }
      }
    }
    else if (local_31 == 'p') {
      __isoc99_scanf(&DAT_001020e3,&local_30);
      __isoc99_scanf(&DAT_001020e3,&local_2f);
      local_2e = local_2f + -0x30;
      if ((local_2e < '\0') || ('\t' < local_2e)) goto LAB_0010133c;
      if (local_30 == 'l') {
        (&DAT_00104060)[(long)(int)DAT_0010401c * 0x19 + (long)(int)(DAT_00104020 - 1)] = local_2e;
      }
      else if (local_30 == 'r') {
        (&DAT_00104060)[(long)(int)DAT_0010401c * 0x19 + (long)(int)(DAT_00104020 + 1)] = local_2e;
      }
      else if (local_30 == 'u') {
        (&DAT_00104060)[(long)(int)(DAT_0010401c + 1) * 0x19 + (long)(int)DAT_00104020] = local_2e;
      }
      else {
        if (local_30 != 'd') goto LAB_0010133c;
        (&DAT_00104060)[(long)(int)(DAT_0010401c - 1) * 0x19 + (long)(int)DAT_00104020] = local_2e;
      }
    }
  }
  goto LAB_00101959;
code_r0x001015c7:
  if ((&DAT_00104060)[(long)(int)(DAT_0010401c + 1) * 0x19 + (long)(int)DAT_00104020] == '\x01')
  goto LAB_0010133c;
  __isoc99_scanf(&DAT_001020e3,&local_2f);
  local_2d = local_2f + -0x30;
  if ((local_2d < '\0') || ('\t' < local_2d)) goto LAB_0010133c;
  (&DAT_00104060)[(long)(int)DAT_0010401c * 0x19 + (long)(int)DAT_00104020] = local_2d;
  DAT_0010401c = DAT_0010401c + 1;
LAB_00101959:
  local_1c = 0;
  while ((&DAT_00104060)[(long)(int)(DAT_0010401c - 1) * 0x19 + (long)(int)DAT_00104020] != '\x01') {
    DAT_0010401c = DAT_0010401c - 1;
    local_1c = local_1c + 1;
    if (10 < local_1c) {
      puts("You died from falling too far.");
      exit(0);
    }
  }
  goto LAB_0010133c;
}

