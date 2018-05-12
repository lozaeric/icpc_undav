n = int(input())
m = []
for i in range(n):
    m.append(int(input()))

m = [10**6]+m+[10**6]

valles = 0
diff = -1

for i in range(0,len(m)-1):
    if m[i+1] == m[i]:
        continue
    curDiff = 1 if m[i+1]-m[i]>0 else -1
    if curDiff > 0 and diff < 0:
        valles += 1
    diff = curDiff
print (valles)
