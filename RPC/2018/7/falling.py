n = int(input())
num = [int(i) for i in input().split()]

num.sort()
a = 0
b = 0
ok = True
for i in reversed(range(len(num))):
    if ok:
        a += num[i]
    else:
        b += num[i]
    ok = not ok

print(str(a)+" "+str(b))
