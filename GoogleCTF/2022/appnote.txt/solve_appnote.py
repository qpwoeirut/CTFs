from io import BytesIO
from zipfile import ZipFile, BadZipFile

CENTRAL_DIR_FILE_HEADER = b"\x50\x4b\x01\x02"
END_CENTRAL_DIR_HEADER = b"\x50\x4b\x05\x06"
ZIP_FILE_HEADER = b"\x50\x4b\x03\x04"


# from ending of dump.zip
def gen_ending(size, offset):
    size_bytes = size.to_bytes(4, 'little')
    offset_bytes = offset.to_bytes(4, 'little')
    print(size, offset)
    return END_CENTRAL_DIR_HEADER + b"\x00\x00\x00\x00\x01\x00\x01\x00" + size_bytes + offset_bytes + b"\x00\x00"


with ZipFile("in.zip", 'r') as in_zip, open("dump.zip", "wb+") as out_zip:
    in_zip.extract("dump.zip")

with open("dump.zip", 'rb') as f:
    data = f.read()

cur_data = bytearray()
file_start_offset = 0
for b in data:
    cur_data.append(b)
    if CENTRAL_DIR_FILE_HEADER in cur_data and cur_data.endswith(ZIP_FILE_HEADER):
        buffer = cur_data[:-len(ZIP_FILE_HEADER)]
        cur_data = bytearray(ZIP_FILE_HEADER)

        central_dir_offset = buffer.index(CENTRAL_DIR_FILE_HEADER)
        size = len(buffer) - central_dir_offset
        buffer += gen_ending(size, file_start_offset + central_dir_offset)

        cur_file = ZipFile(BytesIO(buffer))
        with open("cur.zip", "wb+") as f:
            f.write(buffer)

        assert len(cur_file.namelist()) == 1, cur_file.namelist()
        name = cur_file.namelist()[0]
        print(buffer)
        print(cur_file.read(name))

        file_start_offset += len(buffer)
