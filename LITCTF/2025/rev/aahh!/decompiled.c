undefined8 main(void)

{
  int iVar1;
  long in_FS_OFFSET;
  int local_11c;
  byte local_118 [264];
  long local_10;
  
  local_10 = *(long *)(in_FS_OFFSET + 0x28);
  gets((char *)local_118);
  for (local_11c = 0; local_11c < 0x100; local_11c = local_11c + 1) {
    local_118[local_11c] = local_118[local_11c] ^ (char)local_11c * '\x03' + 6U;
    local_118[local_11c] = (char)local_11c + local_118[local_11c];
  }
  iVar1 = strncmp((char *)local_118,&DAT_00102008,0x36);
  if (iVar1 == 0) {
    puts("win");
  }
  else {
    puts("lose");
  }
  if (local_10 != *(long *)(in_FS_OFFSET + 0x28)) {
                    /* WARNING: Subroutine does not return */
    __stack_chk_fail();
  }
  return 0;
}

