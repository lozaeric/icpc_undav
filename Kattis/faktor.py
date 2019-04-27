from math import ceil

a,i = [int(i) for i in input().split()]
r = a*(i-1+0.000001)
print(ceil(r))
