lfp
lfp
cfp
push 0
lfp
lfp
sal
lal
lir1 5
lr1
lir1 3
lr1
mfp 2
cra
b fentry0
pop
sal
sfp
halt
fentry0:
lra
lfp
lfp
cfp
lir1 0
lr1
lfp
sal
lw 0
lw 0
lw 0
lw1 2
sr2
beq label2
lir1 0
b label3
label2:
lir1 1
label3:
lir2 1
beq label0
lfp
lfp
cfp
lir1 1
lr1
lfp
sal
lw 0
lw 0
lw 0
lw 0
lw1 1
sr2
sub
lfp
sal
lw 0
lw 0
lw 0
lw 0
sw1 1
lfp
lfp
sal
lw 0
lw 0
lw 0
lw 0
lw 0
lw 0
lal
lfp
sal
lw 0
lw 0
lw 0
lw 0
lw1 2
lr1
lfp
sal
lw 0
lw 0
lw 0
lw 0
lw1 1
lr1
mfp 2
cra
b 
sal
sfp
b label1
label0:
lfp
sal
lw 0
lw 0
lw 0
lw1 1
print
label1:
sal
sfp
sra
pop
pop
sfp
jr
