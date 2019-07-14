n = int(input())
h = [int(i) for i in input().split()]
v = [int(i) for i in input().split()]

d = max(0, v[0]-h[0])
isLess = False
for i in range(len(h)):
    h[i] += d
    if h[i] != v[i]:
        isLess = h[i] < v[i]
        break
print(d+(1 if isLess else 0))