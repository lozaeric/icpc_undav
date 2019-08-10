n = int(input())
days = [int(i) for i in input().split()]
res = 0

days.sort()
for i in range(1, 366):
    d = 0
    for j in days:
        if j > i:
            break
        d += i+1-j
    if d >= 20:
        days = [j for j in days if j > i]
        res += 1
print(res if len(days) == 0 else res+1)