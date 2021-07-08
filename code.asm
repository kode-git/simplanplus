lfp
lfp
cfp
push 0
new
swfp 1
push 0
lfp
lfp
sal
lal
lfp
sal
lw1 1
lr1
lir1 7
lr1
mfp 2
cra
lra
b fentry0
pop
pop
sal
sfp
halt
fentry0:
lfp
lfp
cfp
lir1 0
lr1
lfp
sal
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
lw1 1
lwhp 0
sr2
sub
lr1
lfp
sal
lw 0
lw 0
lw1 1
sr2
swhr2 0
lfp
lfp
sal
lw 0
lw 0
lw 0
lal
lfp
sal
lw 0
lw 0
lw1 1
lr1
lir1 1
lr1
lfp
sal
lw 0
lw 0
lw1 2
sr2
sub
lr1
mfp 2
cra
lra
b fentry0
sal
sfp
b label1
label0:
label1:
sal
sfp
sra
pop
pop
pop
sfp
jr
