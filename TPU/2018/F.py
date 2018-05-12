x,y,op = int(input()),int(input()),input()
print(x)
for i in range(9):
    print(y)
    if op == "+":
        aux = y+x
    elif op == "-":
        aux = y-x
    else:
        aux = y*x
    x = y
    y = aux
