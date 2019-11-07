from functools import reduce

mod = 10**9+7

def processOperations():
    val = []
    v = op.pop()
    while isinstance(v, int):
        val.append(v)
        v = op.pop()
    if v == "*":
        return reduce(lambda x,y: (x*y)%mod, val)
    return reduce(lambda x,y: (x+y)%mod, val)

n = int(input())
op = ["+"]
mult = True
for c in input().split():
    if c == "(":
        op.append("*" if mult else "+")
        mult = not mult
    elif c == ")":
        op.append(processOperations())
        mult = not mult
    else:
        op.append(int(c))
res = processOperations()
print(res)