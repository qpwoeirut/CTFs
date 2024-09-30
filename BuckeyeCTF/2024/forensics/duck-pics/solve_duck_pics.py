import enum
import string
import subprocess


# https://gist.github.com/MightyPork/6da26e382a7ad91b5496ee55fdc73db2
class ModifierKeys(enum.Enum):
    LCTRL = 0x01
    LSHIFT = 0x02
    LALT = 0x04
    LMETA = 0x08
    RCTRL = 0x10
    RSHIFT = 0x20
    RALT = 0x40
    RMETA = 0x80


class SpecialKeys(enum.Enum):
    ESCAPE = 0x29
    BACKSPACE = 0x2a
    CAPS_LOCK = 0x39


USB_CODES = {
    0x04: "aA", 0x05: "bB", 0x06: "cC", 0x07: "dD",
    0x08: "eE", 0x09: "fF", 0x0A: "gG", 0x0B: "hH", 0x0C: "iI", 0x0D: "jJ", 0x0E: "kK", 0x0F: "lL",
    0x10: "mM", 0x11: "nN", 0x12: "oO", 0x13: "pP", 0x14: "qQ", 0x15: "rR", 0x16: "sS", 0x17: "tT",
    0x18: "uU", 0x19: "vV", 0x1A: "wW", 0x1B: "xX", 0x1C: "yY", 0x1D: "zZ", 0x1E: "1!", 0x1F: "2@",
    0x20: "3#", 0x21: "4$", 0x22: "5%", 0x23: "6^", 0x24: "7&", 0x25: "8*", 0x26: "9(", 0x27: "0)",
    0x28: "\n\n",
    0x2B: "\t\t", 0x2C: "  ", 0x2D: "-_", 0x2E: "=+", 0x2F: "[{",
    0x30: "]}", 0x31: "\|", 0x32: "#~", 0x33: ";:", 0x34: "'\"", 0x36: ",<", 0x37: ".>", 0x38: "/?",
}
for val in SpecialKeys.__members__.values():
    USB_CODES[val.value] = val

# https://0xd13a.github.io/ctfs/hackit2017/foren100/
def recover_keyboard_pcap(file_path: str) -> str:
    tshark = subprocess.Popen(
        ["tshark", "-r", file_path, "-T", "fields", "-e", "usbhid.data"], stdout=subprocess.PIPE
    )

    packet_data = [data for data in tshark.stdout.read().split(b'\n\n') if data]

    output = ""
    caps_lock = False
    previous_keys = []
    for data in packet_data:
        modifier = int(data[:2], 16)
        codes = [int(data[i:i+2], 16) for i in [4, 6, 8, 10, 12, 14]]

        keys = [USB_CODES[code] for code in codes if code != 0]
        for key in keys:
            if key not in previous_keys:
                if isinstance(key, SpecialKeys):
                    if key == SpecialKeys.BACKSPACE:
                        if modifier == 1:
                            last_index = 0
                            for char in string.whitespace + string.punctuation:
                                if char in output:
                                    last_index = max(last_index, output.rindex(char))
                            output = output[:last_index + 1]
                        else:
                            output = output[:-1]
                    elif key == SpecialKeys.CAPS_LOCK:
                        caps_lock = not caps_lock
                    else:
                        raise ValueError(f"Received unhandled special key {key}")
                else:
                    shift = (modifier & ModifierKeys.LSHIFT.value) > 0 or (modifier & ModifierKeys.RSHIFT.value) > 0
                    output += key[shift or caps_lock]

        previous_keys = keys

    return output


if __name__ == '__main__':
    print(recover_keyboard_pcap("capture.pcapng"))
