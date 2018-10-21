from math import sqrt

a,b = [int(i) for i in input().split()]
trg = lambda c,d: (c+d+1)*(d-c)//2
s = 0
m = int(sqrt(b))

for f in range(1, m+1):
    s += f * (b//f - (a-1)//f) + trg(max(m, (a-1)//f), max(m, b//f))
print(int(s))
