
void FUN_001011ef(void)

{
  long in_FS_OFFSET;
  byte local_129;
  undefined local_128 [176];
  undefined8 local_78;
  undefined8 local_70;
  undefined8 local_68;
  undefined8 local_60;
  undefined8 local_58;
  undefined8 local_50;
  undefined8 local_48;
  undefined8 local_40;
  undefined8 local_38;
  undefined8 local_30;
  undefined8 local_28;
  undefined8 local_20;
  long local_10;

  local_10 = *(long *)(in_FS_OFFSET + 0x28);
  local_78 = 0xff;
  local_70 = 0;
  local_68 = 0;
  local_60 = 0;
  local_58 = 0;
  local_50 = 0;
  local_48 = 0;
  local_40 = 0;
  local_38 = 0;
  local_30 = 0;
  local_28 = 0;
  local_20 = 0;
  __isoc99_scanf(&DAT_00102009,&local_68);
  __isoc99_scanf(&DAT_00102009,&local_78);
  FUN_001015e8(local_128,&local_78);  // ends up calling FUN_00101329 directly
  for (local_129 = 0; local_129 < 5; local_129 = local_129 + 1) {
    FUN_00101a72(local_128,(long)&local_68 + (long)(int)((uint)local_129 << 4));
    FUN_0010119d((long)&local_68 + (long)(int)((uint)local_129 << 4));
  }
  if (local_10 != *(long *)(in_FS_OFFSET + 0x28)) {
                    /* WARNING: Subroutine does not return */
    __stack_chk_fail();
  }
  return;
}

void FUN_00101329(long param_1,long param_2)

{
  long lVar1;
  uint uVar2;
  uint uVar3;
  long in_FS_OFFSET;
  uint local_20;
  byte local_14;
  byte local_13;
  byte local_12;
  byte local_11;

  lVar1 = *(long *)(in_FS_OFFSET + 0x28);
  for (local_20 = 0; local_20 < 4; local_20 = local_20 + 1) {
    *(undefined *)(param_1 + (ulong)(local_20 << 2)) =
         *(undefined *)(param_2 + (ulong)(local_20 << 2));
    *(undefined *)(param_1 + (ulong)(local_20 * 4 + 1)) =
         *(undefined *)(param_2 + (ulong)(local_20 * 4 + 1));
    *(undefined *)(param_1 + (ulong)(local_20 * 4 + 2)) =
         *(undefined *)(param_2 + (ulong)(local_20 * 4 + 2));
    *(undefined *)(param_1 + (ulong)(local_20 * 4 + 3)) =
         *(undefined *)(param_2 + (ulong)(local_20 * 4 + 3));
  }
  for (local_20 = 4; local_20 < 0x2c; local_20 = local_20 + 1) {
    uVar2 = (local_20 - 1) * 4;
    local_14 = *(byte *)(param_1 + (ulong)uVar2);
    local_13 = *(byte *)(param_1 + (ulong)(uVar2 + 1));
    local_12 = *(byte *)(param_1 + (ulong)(uVar2 + 2));
    local_11 = *(byte *)(param_1 + (ulong)(uVar2 + 3));
    if ((local_20 & 3) == 0) {
      uVar2 = (uint)local_13;
      local_13 = (&DAT_00102020)[(int)(uint)local_12];
      local_12 = (&DAT_00102020)[(int)(uint)local_11];
      local_11 = (&DAT_00102020)[(int)(uint)local_14];
      local_14 = (&DAT_00102120)[local_20 >> 2] ^ (&DAT_00102020)[(int)uVar2];
    }
    uVar2 = local_20 * 4;
    uVar3 = (local_20 - 4) * 4;
    *(byte *)(param_1 + (ulong)uVar2) = local_14 ^ *(byte *)(param_1 + (ulong)uVar3);
    *(byte *)(param_1 + (ulong)(uVar2 + 1)) = local_13 ^ *(byte *)(param_1 + (ulong)(uVar3 + 1));
    *(byte *)(param_1 + (ulong)(uVar2 + 2)) = local_12 ^ *(byte *)(param_1 + (ulong)(uVar3 + 2));
    *(byte *)(param_1 + (ulong)(uVar2 + 3)) = local_11 ^ *(byte *)(param_1 + (ulong)(uVar3 + 3));
  }
  if (lVar1 != *(long *)(in_FS_OFFSET + 0x28)) {
                    /* WARNING: Subroutine does not return */
    __stack_chk_fail();
  }
  return;
}


void FUN_00101a72(undefined8 param_1,undefined8 param_2)

{
  FUN_00101a22(param_2,param_1);
  return;
}


void FUN_00101a22(undefined8 param_1)

{
  char local_9;

  local_9 = '\x01';
  while( true ) {
    FUN_001016b7(param_1);
    FUN_00101733(param_1);
    if (local_9 == '\n') break;
    FUN_00101843(param_1);
    local_9 = local_9 + '\x01';
  }
  return;
}


void FUN_0010119d(long param_1)

{
  byte local_a;

  for (local_a = 0; local_a < 0x10; local_a = local_a + 1) {
    printf("%.2x",(ulong)*(byte *)(param_1 + (ulong)local_a));
  }
  return;
}


