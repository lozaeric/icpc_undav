n = int(input())
num = [int(input()) for i in range(n)]
res = 0
aux = sum([v*v for v in num])
aux2 = 0
for i in range(n-1, 0, -1):
    aux -= num[i]*num[i]
    aux2 += num[i]
    res = max(res, aux*aux2)
print(res)