void xor_encrypt(char *param_1,char *param_2)

{
  byte bVar1;
  size_t sVar2;
  size_t sVar3;
  ulong local_28;

  sVar2 = strlen(param_1);
  for (local_28 = 0; local_28 < sVar2; local_28 = local_28 + 1) {
    bVar1 = param_1[local_28];
    sVar3 = strlen(param_2);
    param_1[local_28] = param_2[local_28 % sVar3] ^ bVar1;
  }
  return;
}


void add_encrypt(long param_1,ulong param_2,int param_3)

{
  ulong local_10;

  for (local_10 = 0; local_10 < param_2; local_10 = local_10 + 1) {
    *(int *)(local_10 * 4 + param_1) = (*(int *)(param_1 + local_10 * 4) + param_3) % 10;
  }
  return;
}


void reverse_encrypt(char *param_1)

{
  char cVar1;
  size_t sVar2;
  ulong local_18;

  sVar2 = strlen(param_1);
  for (local_18 = 0; local_18 < sVar2 >> 1; local_18 = local_18 + 1) {
    cVar1 = param_1[local_18];
    param_1[local_18] = param_1[(sVar2 - local_18) + -1];
    param_1[(sVar2 - local_18) + -1] = cVar1;
  }
  return;
}

undefined4 lookup_encrypt(uint param_1)

{
  undefined4 uVar1;
  long in_FS_OFFSET;
  undefined4 local_38 [10];
  long local_10;

  local_10 = *(long *)(in_FS_OFFSET + 0x28);
  local_38[0] = 3;
  local_38[1] = 1;
  local_38[2] = 4;
  local_38[3] = 1;
  local_38[4] = 5;
  local_38[5] = 9;
  local_38[6] = 2;
  local_38[7] = 6;
  local_38[8] = 5;
  local_38[9] = 3;
  if (((int)param_1 < 0) || (9 < param_1)) {
    uVar1 = 0xffffffff;
  }
  else {
    uVar1 = local_38[(int)param_1];
  }
  if (local_10 != *(long *)(in_FS_OFFSET + 0x28)) {
                    /* WARNING: Subroutine does not return */
    __stack_chk_fail();
  }
  return uVar1;
}


undefined8 main(void)

{
  int iVar1;
  size_t sVar2;
  undefined8 uVar3;
  long in_FS_OFFSET;
  uint local_504;
  uint local_500;
  uint local_4fc;
  uint local_4f8;
  uint local_4f4;
  undefined4 local_4f0;
  int local_4ec;
  int local_4e8;
  int local_4e4;
  ulong local_4e0;
  char *local_4d8;
  ulong local_4d0;
  uint local_4c8 [4];
  uint local_4b8 [4];
  undefined8 local_4a8;
  undefined8 local_4a0;
  undefined8 local_498;
  undefined8 local_490;
  undefined8 local_488;
  undefined local_480;
  undefined8 local_478;
  undefined8 local_470;
  undefined8 local_468;
  undefined8 local_460;
  undefined8 local_458;
  undefined8 local_450;
  undefined8 local_448;
  undefined8 local_440;
  undefined8 local_438;
  undefined8 local_430;
  undefined8 local_428;
  undefined8 local_420;
  char local_418 [1032];
  long local_10;

  local_10 = *(long *)(in_FS_OFFSET + 0x28);
  puts("Welcome to the Nuclear Power Plant Keypad Challenge!");
  puts("Security Check 1: Access Code Verification");
  local_478 = 0;
  local_470 = 0;
  local_468 = 0;
  local_460 = 0;
  local_458 = 0;
  local_450 = 0;
  fgets((char *)&local_478,0x30,stdin);
  sVar2 = strcspn((char *)&local_478,"\n");
  *(undefined *)((long)&local_478 + sVar2) = 0;
  local_4d8 = "power";
  local_4a8 = 0x4131d09130e00;
  xor_encrypt(&local_478,"power");
  iVar1 = strcmp((char *)&local_4a8,(char *)&local_478);
  if (iVar1 == 0) {
    puts("Access Code Verified!");
    puts("Security Check 2: Numeric Keypad Entry");
    __isoc99_scanf("%d %d %d %d",&local_504,&local_500,&local_4fc,&local_4f8);
    local_4c8[0] = 9;
    local_4c8[1] = 7;
    local_4c8[2] = 1;
    local_4c8[3] = 3;
    local_4b8[0] = local_504;
    local_4b8[1] = local_500;
    local_4b8[2] = local_4fc;
    local_4b8[3] = local_4f8;
    local_4d0 = 4;
    local_4f0 = 4;
    add_encrypt(local_4b8,4,4);
    for (local_4e0 = 0; local_4e0 < local_4d0; local_4e0 = local_4e0 + 1) {
      if (local_4c8[local_4e0] != local_4b8[local_4e0]) {
        puts("Access Denied: Numeric Entry Incorrect.");
        uVar3 = 1;
        goto LAB_00101a5d;
      }
    }
    do {
      local_4ec = getchar();
      if (local_4ec == -1) break;
    } while (local_4ec != 10);
    puts("Numeric Keypad Entry Verified!");
    puts("Security Check 3: Reversed Passphrase");
    local_448 = 0;
    local_440 = 0;
    local_438 = 0;
    local_430 = 0;
    local_428 = 0;
    local_420 = 0;
    fgets((char *)&local_448,0x30,stdin);
    sVar2 = strcspn((char *)&local_448,"\n");
    *(undefined *)((long)&local_448 + sVar2) = 0;
    local_4a0 = 0x72657665727365;
    reverse_encrypt(&local_448);
    iVar1 = strcmp((char *)&local_4a0,(char *)&local_448);
    if (iVar1 == 0) {
      puts("Passphrase Verified!");
      puts("Security Check 4: Lookup Table Validation");
      __isoc99_scanf(&DAT_001021c2,&local_4f4);
      local_4e8 = 9;
      local_4e4 = lookup_encrypt(local_4f4);
      if (local_4e4 == local_4e8) {
        puts("Lookup Table Validated!");
        snprintf(local_418,0x400,"%s%d%d%d%d%s%d","padlock",(ulong)local_504,(ulong)local_500,
                 (ulong)local_4fc,(ulong)local_4f8,&local_448,(ulong)local_4f4);
        local_498 = 0x65102d202f303222;
        local_490 = 0x343a19100a555352;
        local_488 = 0x120801120301021c;
        local_480 = 0;
        xor_encrypt(&local_498,local_418);
        printf("Flag: %s\n",&local_498);
        uVar3 = 0;
      }
      else {
        puts("Access Denied: Lookup Table Mismatch.");
        uVar3 = 1;
      }
    }
    else {
      puts("Access Denied: Incorrect Passphrase.");
      uVar3 = 1;
    }
  }
  else {
    puts("Access Denied: Incorrect Code.");
    uVar3 = 1;
  }
LAB_00101a5d:
  if (local_10 != *(long *)(in_FS_OFFSET + 0x28)) {
                    /* WARNING: Subroutine does not return */
    __stack_chk_fail();
  }
  return uVar3;
}

