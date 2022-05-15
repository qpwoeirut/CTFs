/* Decompiler 4ms, total 93ms, lines 15 */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileManager {
   public static void saveData(byte[] var0) {
      try {
         FileOutputStream var1 = new FileOutputStream("data.dat");
         var1.write(var0);
         var1.close();
      } catch (Exception var2) {
         var2.printStackTrace();
      }

   }
   public static Tile[][][] readData(int width, int height) {

      Tile[][][] tiles = new Tile[height][width][8];
      int pos = 17;  // ignore the player position stuff

      try {
         byte[] data = Files.readAllBytes(Paths.get("data.dat"));

         for (int i=0; i<height; i++) {
            for (int j=0; j<width; j++) {
               for (int k=0; k<8; k+=2) {
                  tiles[i][j][k] = new Tile(Tile.TileType.values()[data[pos] & 0xF], j, i, k);
                  tiles[i][j][k + 1] = new Tile(Tile.TileType.values()[(data[pos] >> 4) & 0xF], j, i, k);
                  pos++;
               }
            }
         }
      } catch (IOException e) {
         e.printStackTrace();
      }

      return tiles;
   }
}

/*
with open("data.dat", 'rb') as data_file:
    data = data_file.read()

width = 3000  # from Main.java
height = 2000

tile_size = (len(data) - 17) / 4
assert width * height == tile_size

x = int.from_bytes(data[0:4], 'big')  # small endian produces much larger number
y = int.from_bytes(data[4:8], 'big')
z = int.from_bytes(data[8:9], 'big')

print(x, y, z)

map_width = int.from_bytes(data[9:13], 'big')
map_height = int.from_bytes(data[13:17], 'big')

assert width == map_width and height == map_height, f"{width} {map_width} {height} {map_height}"

tiles = [[[0 for _ in range(8)] for _ in range(width)] for _ in range(height)]

pos = 17
for i in range(height):
    for j in range(width):
        for k in [0, 2, 4, 6]:
            tiles[i][j][k] = data[pos] & 0xF
            tiles[i][j][k + 1] = (data[pos] >> 4) & 0xF
            pos += 1

 */