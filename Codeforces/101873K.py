n,d,k = [int(i) for i in input().split()]
e = {}

for i in range(n):
    v = input().split()
    e[v[0]] = int(v[1])

es = sorted(e.items(), key=lambda x: x[1], reverse=True)
out = ""
count = 0
for i in range(min(len(es), k)):
    d -= es[i][1]
    out += es[i][0]+", YOU ARE FIRED!\n"
    count += 1
    if d <= 0:
        break
if d <= 0:
    print(count)
    print(out, end='')
else:
    print("impossible")