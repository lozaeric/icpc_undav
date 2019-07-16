n,k = [int(i) for i in input().split()]
num = [int(i) for i in input().split()]

num.sort()
s = 0
for v in num:
    mv = v-s
    if mv > 0:
        print(mv)
        s += mv
        k -= 1
    if k == 0:
        break
for i in range(k):
    print(0)

