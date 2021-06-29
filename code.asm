lfp
lfp
cfp
push 0
lir1 7
swfp 1
push 0

lfp
lfp
cfp
push 0
lfp
lfp
sal
lw 0
lal
cfp
cra
b fentry0
pop
sal
sfp
pop
pop
sal
sfp
halt

fentry0:
lra
lfp
lfp
cfp
push 0
lir1 3
swfp 1
lir1 8
print
pop
sal
sfp
sra
sfp
jr
