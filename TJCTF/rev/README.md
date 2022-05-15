# TJCTF 2022 Rev

## take-a-l
> Author: kpdfgo<br>
> I need W :angry:
> 
> Downloads: [chall](https://tjctf-2022.storage.googleapis.com/uploads/669d71c892ef494d621eebdf3a7fa83fc3e50974d2f1e0035d80ee2c16526829/chall)

We can use Ghidra to decompile the binary, which yields this C code:
```c
undefined8 main(void)

{
  size_t sVar1;
  undefined8 uVar2;
  long in_FS_OFFSET;
  ulong local_68;
  byte local_58 [72];
  long local_10;
  
  local_10 = *(long *)(in_FS_OFFSET + 0x28);
  puts("What\'s your flag?");
  fgets((char *)local_58,0x40,stdin);
  sVar1 = strlen((char *)local_58);
  if (sVar1 == 0x1a) {
    for (local_68 = 0; local_68 < 0x19; local_68 = local_68 + 1) {
      if (((&flag)[local_68] ^ local_58[local_68]) != 0x12) {
        puts("L");
        uVar2 = 1;
        goto LAB_00101278;
      }
    }
    puts("W");
    uVar2 = 0;
  }
  else {
    puts("L");
    uVar2 = 1;
  }
LAB_00101278:
  if (local_10 != *(long *)(in_FS_OFFSET + 0x28)) {
                    /* WARNING: Subroutine does not return */
    __stack_chk_fail();
  }
  return uVar2;
}
```
Not the cleanest, but we can see that it xors the input with the flag and checks if that's equal to `0x12`.
The input also needs to be 25 chars long.
Ghidra also tells us that the encoded flag is stored at 0x2010.
We can recover it with a hexdump:
```
00002010: 6678 7166 7469 7575 7573 7f77 6061 6161  fxqftiuuus.w`aaa
00002020: 6161 6161 6161 2761 6f00 5768 6174 2773  aaaaaa'ao.What's
```
I tried copying the flag from Ghidra itself, but I missed the `7f` byte, and it took me a while to figure out why my flag was too short.
In the end I got the string directly from the hexdump. Once we get the correct encoded flag, we can xor each byte with `0x12` to get the actual flag.

`tjctf{gggamersssssssss5s}`

## block-game
> Author: kpdfgo<br>
> I made a game with some blocks! I worked out how to save but I can't load my worlds back. It's ok, it wasn't that important anyway...
> 
> Downloads: [data.dat](https://tjctf-2022.storage.googleapis.com/uploads/6df22ad6e78e051b433b58c17c740c1a12f1ffc049b86345f7f3c932067e9767/data.dat), [chall.jar](https://tjctf-2022.storage.googleapis.com/uploads/1a59bfcf86ca436d2b9d16ac738c106c0eeb674dd640a9f36a7ed1815c9ea78b/chall.jar) 

We need to reverse a JAR, which creates a 3D block game that's somewhat similar to Minecraft except only one layer can be viewed at a time.
Fortunately, JARs can be pretty easily decompiled with tools such as this [online decompiler](https://jdec.app/).
From there, we can see that the `data.dat` file holds data from `Game.saveData`.
I've changed the variable names from the decompiled code to add clarity.

```java
public void saveData() {
    int pos = 0;
    byte[] data = new byte[17 + this.tiles.length * this.tiles[0].length * 4];
    byte[] buffer = ByteBuffer.allocate(4).putInt((int)this.player.getX()).array();
    
    byte tmp;
    for(int i = 0; i < buffer.length; ++i) {
        tmp = buffer[i];
        System.out.println(tmp);
        data[pos++] = tmp;
    }
    
    buffer = ByteBuffer.allocate(4).putInt((int)this.player.getY()).array();
    
    for(int i = 0; i < buffer.length; ++i) {
        tmp = buffer[i];
        data[pos++] = tmp;
    }
    
    data[pos++] = (byte)this.player.getZ();
    buffer = ByteBuffer.allocate(4).putInt(this.mapWidth).array();
    
    for(int i = 0; i < buffer.length; ++i) {
        tmp = buffer[i];
        data[pos++] = tmp;
    }
    
    buffer = ByteBuffer.allocate(4).putInt(this.mapHeight).array();
    
    for(int i = 0; i < buffer.length; ++i) {
        tmp = buffer[i];
        data[pos++] = tmp;
    }
    
    for(int i = 0; i < this.tiles.length; ++i) {
        for(int j = 0; j < this.tiles[i].length; ++j) {
            for(int k = 0; k < 8; k += 2) {
                tmp = 0;
                
                for(int a = 0; a < 2; ++a) {
                    tmp |= (byte)(this.tiles[i][j][a + k].getType().id << a * 4);
                }
                
                data[pos++] = tmp;
            }
        }
    }
    
    FileManager.saveData(data);
}
```

Our first step should be to recover the data from the `data.dat` file.
I originally wrote a data reader in Python and then ported to Java as `FileManager.readData` after realizing it would be easier to just run the game once I had the data.
Then I replaced the tile generation code with a call to `FileManager.readData` and ran the game (after editing the decompiled code slightly so that it would compile).

Fortunately the flag was drawn near the spawn point and I just had to explore a bit to find the flag.
If it had been farther away then I would have needed to decode the player position too and figure out the mechanics of the coordinate system.

The final Java code that I ran is in the [SolveBlockGame directory](/TJCTF/rev/block-game/SolveBlockGame).

`tjctf{i_lov3_b10Ck5_cbd7c}`
