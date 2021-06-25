lfp
push 10
push 10
bleq label2
push 1
b label3
label2:
push 0
label3:
push 1
beq label0
b label1
label0:
push 20
print
label1:
push 30
print
sfp
halt