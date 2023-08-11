
void FUN_001011e9(ulong param_1)

{
  int iVar1;

  iVar1 = getpagesize();
  mprotect((void *)(param_1 - param_1 % (ulong)(long)iVar1),0x18000,7);
  return;
}


uint FUN_00101244(void)

{
  DAT_0010d028 = (DAT_0010d020 + DAT_0010d028 * DAT_0010d018) % DAT_0010d010;
  return ((uint)DAT_0010d028 & 0x1ff) + 0x200 & 0x1ff;
}


void FUN_0010129a(void)

{
  uint uVar1;
  char *pcVar2;
  char in_CL;
  char cVar3;
  undefined8 in_R8;
  undefined7 uVar4;
  bool bVar5;
  ulong local_4a0;

  uVar4 = (undefined7)((ulong)in_R8 >> 8);
  cVar3 = (char)in_R8;
  FUN_001011e9(FUN_0010129a);
  puts("whar is the flag?");
  __isoc99_scanf(&DAT_0010b01a);
  for (local_4a0 = 0; bVar5 = local_4a0 == 0x1ff, local_4a0 < 0x200; local_4a0 = local_4a0 + 1) {
    uVar1 = FUN_00101244();
    DAT_0010d040 = (ulong)(uVar1 & 0x1ff);
    DAT_0010d038 = &DAT_0010149a + local_4a0;
    DAT_0010d050 = &DAT_0010149a + DAT_0010d040;
    DAT_0010d048 = *DAT_0010d038;
    *DAT_0010d038 = *DAT_0010d050;
    *DAT_0010d050 = DAT_0010d048;
  }
  pcVar2 = (char *)func_0x0f106829();
  if (!bVar5) {
    *(char *)CONCAT71(uVar4,cVar3) = *(char *)CONCAT71(uVar4,cVar3) + cVar3;
  }
  pcVar2[-0x78] = pcVar2[-0x78] + in_CL;
  *pcVar2 = *pcVar2 + in_CL;
                    /* WARNING: Bad instruction - Truncating control flow here */
  halt_baddata();
}

