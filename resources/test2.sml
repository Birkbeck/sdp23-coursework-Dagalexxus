    avc EAX 6
    mov EBX 1 2
    mov ECX 1
f3: mul EBX EAX
    sub EAX ECX
    jnz EAX f3
    out EBX