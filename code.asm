lfp     // [fp]
lfp     // [fp,fp]
cfp     // fp <- sp
push 0  //[0,fp,fp]
fentry0:
cfp    // [
lra
lfp
lfp
cfp
lir1 10
srv
sal
sfp
sra
sfp
jr

lfp
cra
b fentry0
print
pop
sal
sfp
halt