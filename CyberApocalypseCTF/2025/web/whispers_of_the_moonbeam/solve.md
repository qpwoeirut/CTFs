We're given a modified shell where some commands have been renamed.

`gossip` seems to be `ls` and tells us that there's a flag.txt.

`cat flag.txt` doesn't work but `gossip; cat flag.txt` does. The tip at the bottom helpfully suggests to use "; for command injection".

`HTB{Sh4d0w_3x3cut10n_1n_Th3_M00nb34m_T4v3rn_560307b0fc35434da95fe54437cb4a98}`
