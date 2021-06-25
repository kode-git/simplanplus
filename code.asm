lfp
push 10
push -1
mult
push 10
push -1
mult
beq label2
push 0
b label3
label2:
push 1
label3:
push 1
beq label0
b label1
label0:
lfp
push 10
push 4
add
push 9
push 5
add
bleq label6
push 0
b label7
label6:
push 1
label7:
push 1
beq label4
push 10
push 4
add
print
b label5
label4:
push 10
push 5
add
print
label5:
sfp
label1:
push 30
push 3
push -1
mult
mult
print
sfp
halt