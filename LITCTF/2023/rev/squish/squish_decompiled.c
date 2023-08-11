
undefined * FUN_00101209(int param_1,int param_2) {
  int iVar1;

  iVar1 = param_2 * param_1 + 0x36;
  DAT_00152240 = 0x42;
  DAT_00152241 = 0x4d;
  DAT_00152242 = (char)iVar1;
  DAT_00152243 = (char)((uint)iVar1 >> 8);
  DAT_00152244 = (char)((uint)iVar1 >> 0x10);
  DAT_00152245 = (char)((uint)iVar1 >> 0x18);
  DAT_0015224a = 0x36;
  return &DAT_00152240;
}

undefined * FUN_0010126f(undefined4 param_1, undefined4 param_2) {
  DAT_00152260 = 0x28;
  DAT_00152264 = (char)param_2;
  DAT_00152265 = (char)((uint)param_2 >> 8);
  DAT_00152266 = (char)((uint)param_2 >> 0x10);
  DAT_00152267 = (char)((uint)param_2 >> 0x18);
  DAT_00152268 = (char)param_1;
  DAT_00152269 = (char)((uint)param_1 >> 8);
  DAT_0015226a = (char)((uint)param_1 >> 0x10);
  DAT_0015226b = (char)((uint)param_1 >> 0x18);
  DAT_0015226c = 1;
  DAT_0015226e = 0x18;
  return &DAT_00152260;
}


void FUN_001012f5(long param_1,int param_2,int param_3,char *param_4) {
  FILE *__s;
  void *pvVar1;
  int local_28;

  __s = fopen(param_4,"wb");
  pvVar1 = (void *)FUN_00101209(param_2,param_3 * 3);
  fwrite(pvVar1,1,0xe,__s);
  pvVar1 = (void *)FUN_0010126f(param_2,param_3);
  fwrite(pvVar1,1,0x28,__s);
  for (local_28 = 0; local_28 < param_2; local_28 = local_28 + 1) {
    fwrite((void *)(local_28 * param_3 * 3 + param_1),3,(long)param_3,__s);
  }
  fclose(__s);
  return;
}


undefined8 FUN_001013e1(void) {
  long lVar1;
  long lVar2;
  int iVar3;
  int iVar4;
  size_t sVar5;
  ulong uVar6;
  undefined *puVar7;
  undefined *puVar8;
  long in_FS_OFFSET;
  undefined auStack_198 [8];
  undefined *local_190;
  long local_188;
  undefined8 local_180;
  long local_178;
  undefined8 local_170;
  long local_168;
  undefined8 local_160;
  long local_158;
  undefined8 local_150;
  long local_148;
  undefined8 local_140;
  long local_138;
  undefined8 local_130;
  long local_128;
  undefined8 local_120;
  long local_118;
  undefined8 local_110;
  int local_100;
  int local_fc;
  int local_f8;
  int local_f4;
  int local_f0;
  int local_ec;
  int local_e8;
  int local_e4;
  int local_e0;
  int local_dc;
  int local_d8;
  int local_d4;
  int local_d0;
  int local_cc;
  undefined4 local_c8;
  int local_c4;
  long local_c0;
  long local_b8;
  undefined *local_b0;
  long local_a8;
  long local_a0;
  undefined *local_98;
  undefined8 local_90;
  char local_88 [72];
  long local_40;

  local_190 = auStack_198;
  local_40 = *(long *)(in_FS_OFFSET + 0x28);
  puts("what to squish?");
  __isoc99_scanf(&DAT_00102017,local_88);
  local_d0 = 0x41;
  sVar5 = strlen(local_88);
  local_cc = (int)sVar5 * 0x32;
  local_c8 = 0x5c;
  local_cc = local_cc + (0x5c - local_cc % 0x5c) % 0x5c;
  local_c0 = (long)local_cc + -1;
  lVar2 = (long)local_cc * 3;
  local_b8 = (long)local_d0 + -1;
  local_118 = (long)local_cc;
  local_110 = 0;
  local_128 = (long)local_cc;
  local_120 = 0;
  local_138 = (long)local_d0;
  local_130 = 0;
  uVar6 = (((long)local_cc * (long)local_d0 * 3 + 0xfU) / 0x10) * 0x10;
  for (puVar7 = auStack_198; puVar7 != auStack_198 + -(uVar6 & 0xfffffffffffff000);
      puVar7 = puVar7 + -0x1000) {
    *(undefined8 *)(puVar7 + -8) = *(undefined8 *)(puVar7 + -8);
  }
  lVar1 = -(ulong)((uint)uVar6 & 0xfff);
  local_b0 = puVar7 + lVar1;
  puVar8 = puVar7 + lVar1;
  if ((uVar6 & 0xfff) != 0) {
    *(undefined8 *)(puVar7 + ((ulong)((uint)uVar6 & 0xfff) - 8) + lVar1) =
         *(undefined8 *)(puVar7 + ((ulong)((uint)uVar6 & 0xfff) - 8) + lVar1);
  }
  for (local_d4 = 0; local_d4 < local_d0; local_d4 = local_d4 + 1) {
    for (local_d8 = 0; local_d8 < local_cc; local_d8 = local_d8 + 1) {
      puVar7[local_d4 * lVar2 + (long)local_d8 * 3 + lVar1 + 2] = 0xff;
      puVar7[local_d4 * lVar2 + (long)local_d8 * 3 + lVar1 + 1] =
           puVar7[local_d4 * lVar2 + (long)local_d8 * 3 + lVar1 + 2];
      puVar7[local_d4 * lVar2 + (long)local_d8 * 3 + lVar1] =
           puVar7[local_d4 * lVar2 + (long)local_d8 * 3 + lVar1 + 1];
    }
  }
  local_90 = 0x706d622e74756f;
  local_dc = 0;
  while( true ) {
    uVar6 = (ulong)local_dc;
    *(undefined8 *)(puVar7 + lVar1 + -8) = 0x1018ee;
    sVar5 = strlen(local_88);
    if (sVar5 <= uVar6) break;
    local_c4 = (int)local_88[local_dc];
    for (local_e0 = 0; local_e0 < 0x32; local_e0 = local_e0 + 1) {
      for (local_e4 = 0; local_e4 < 0x32; local_e4 = local_e4 + 1) {
        for (local_e8 = 0; local_e8 < 3; local_e8 = local_e8 + 1) {
          local_b0[(0x39 - local_e0) * lVar2 +
                   (long)local_e8 + (long)(local_dc * 0x32 + local_e4) * 3] =
               (&DAT_00104020)[(long)(local_e0 * 0x32 + local_e4) + (long)local_c4 * 0x9c4];
        }
      }
    }
    local_dc = local_dc + 1;
  }
  iVar3 = local_cc / 0x17;
  iVar4 = local_d0 / 0x17;
  local_a8 = (long)iVar3 + -1;
  local_148 = (long)iVar3;
  local_140 = 0;
  local_a0 = (long)iVar4 + -1;
  local_158 = (long)iVar3;
  local_150 = 0;
  local_168 = (long)iVar4;
  local_160 = 0;
  local_178 = (long)iVar3;
  local_170 = 0;
  local_188 = (long)iVar4;
  local_180 = 0;
  uVar6 = (((long)iVar3 * (long)iVar4 * 3 + 0xfU) / 0x10) * 0x10;
  for (; puVar8 != puVar7 + (lVar1 - (uVar6 & 0xfffffffffffff000)); puVar8 = puVar8 + -0x1000) {
    *(undefined8 *)(puVar8 + -8) = *(undefined8 *)(puVar8 + -8);
  }
  lVar1 = -(ulong)((uint)uVar6 & 0xfff);
  local_98 = puVar8 + lVar1;
  if ((uVar6 & 0xfff) != 0) {
    *(undefined8 *)(puVar8 + ((ulong)((uint)uVar6 & 0xfff) - 8) + lVar1) =
         *(undefined8 *)(puVar8 + ((ulong)((uint)uVar6 & 0xfff) - 8) + lVar1);
  }
  for (local_ec = 0; local_ec < local_d0 / 0x17; local_ec = local_ec + 1) {
    for (local_f0 = 0; local_f0 < local_cc / 0x17; local_f0 = local_f0 + 1) {
      local_f4 = 0;
      for (local_f8 = 0; local_f8 < 0x17; local_f8 = local_f8 + 1) {
        for (local_fc = 0; local_fc < 0x17; local_fc = local_fc + 1) {
          local_f4 = local_f4 +
                     (uint)(byte)local_b0[(local_ec * 0x17 + local_f8) * lVar2 +
                                          (long)(local_fc + local_f0 * 0x17) * 3];
        }
      }
      local_f4 = local_f4 / 0x211;
      for (local_100 = 0; local_100 < 3; local_100 = local_100 + 1) {
        puVar8[(long)local_ec * (long)iVar3 * 3 + (long)local_100 + (long)local_f0 * 3 + lVar1] =
             (char)local_f4;
      }
    }
  }
  iVar3 = local_cc / 0x17;
  iVar4 = local_d0 / 0x17;
  *(undefined8 *)(puVar8 + lVar1 + -8) = 0x101d2d;
  FUN_001012f5((long)puVar8 + lVar1,iVar4,iVar3,&local_90);
  *(undefined8 *)(puVar8 + lVar1 + -8) = 0x101d39;
  puts("ok it squished");
  if (local_40 != *(long *)(in_FS_OFFSET + 0x28)) {
                    /* WARNING: Subroutine does not return */
    *(undefined8 *)(local_190 + -8) = 0x101d5c;
    __stack_chk_fail();
  }
  return 0;
}

