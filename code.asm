lfp
lfp
cfp
push 0
lfp
lfp
sal
lal
lir1 4
lr1
lir1 5
lr1
mfp 2
cra
lra
b fentry0
pop
sal
sfp
halt
fentry0:
lfp
lfp
cfp
lfp
sal
lw 0
lw1 2
lr1
lfp
sal
lw 0
lw1 1
sr2
bleq label2
lir1 1
b label3
label2:
lir1 0
label3:
lir2 1
beq label0
lfp
lfp
cfp
push 0
lir1 1
swfp 1
lfp
lfp
sal
lw 0
lw 0
lw 0
lal
lir1 1
lr1
lfp
sal
lw 0
lw 0
lw1 1
sr2
add
lr1
lir1 1
lr1
lfp
sal
lw 0
lw 0
lw1 2
sr2
add
lr1
mfp 2
cra
lra
b fentry0
pop
sal
sfp
b label1
label0:
lfp
lfp
cfp
lfp
sal
lw 0
lw 0
lw1 2
lr1
lfp
sal
lw 0
lw 0
lw1 1
sr2
add
print
sal
sfp
label1:
sal
sfp
sra
pop
pop
pop
sfp
jr
