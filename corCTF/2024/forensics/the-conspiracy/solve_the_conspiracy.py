import ast

from scapy.layers.inet import TCP
from scapy.packet import Packet
from scapy.utils import rdpcap

arr = []

scapy_cap = rdpcap('challenge.pcap')
for packet in scapy_cap:
    assert isinstance(packet, Packet)
    try:
        data = packet[TCP].load.decode()
        arr.append(ast.literal_eval(data))
    except IndexError:  # not a TCP packet
        pass

assert len(arr) % 2 == 0

for i in range(0, len(arr), 2):
    enc, keys = arr[i], arr[i + 1]
    msg = [e // k for e, k in zip(enc, keys)]

    print(''.join([chr(c) for c in msg]))