lfp
lfp
cfp
push 0
lir1 1
swfp 1
push 0
lfp
lfp
sal
lal
lir1 5
lr1
mfp 1
cra
b fentry0
lir1 16
print
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
lfp
sal
lw 0
lw 0
lw1 1
print
lir1 1
lr1
lfp
sal
lw 0
lw 0
lw1 1
sr2
add
lfp
sal
lw 0
lw 0
sw1 1
lir1 500
lr1
lfp
sal
lw 0
lw 0
lw1 1
sr2
bless label2
lir1 0
b label3
label2:
lir1 1
label3:
lir2 1
beq label0
b label1
label0:
lfp
lfp
sal
lw 0
lw 0
lal
lfp
sal
lw 0
lw 0
lw1 1
lr1
mfp 1
cra
b fentry0
label1:
sal
sfp
sra
pop
sfp
jr
