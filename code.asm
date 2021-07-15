lfp
lfp
cfp
push 0
lfp
lfp
sal
lal
lir1 10
lr1
mfp 1
cra
lra
b fentry0
print
pop
sal
sfp
halt
fentry0:
lfp
lfp
cfp
lir1 1
lr1
lfp
sal
lw 0
lw1 1
sr2
bleq label2
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
lir1 2
lr1
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
lw1 1
sr2
sub
sr2
add
sr2
sub
print
lfp
lfp
sal
lw 0
lw 0
lw 0
lal
lir1 2
lr1
lfp
sal
lw 0
lw 0
lw1 1
sr2
sub
lr1
mfp 1
cra
lra
b fentry0
lr1
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
sub
lr1
mfp 1
cra
lra
b fentry0
sr2
add
srv
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
lw1 1
print
lfp
sal
lw 0
lw 0
lw1 1
srv
sal
sfp
label1:
sal
sfp
sra
pop
pop
sfp
jr
