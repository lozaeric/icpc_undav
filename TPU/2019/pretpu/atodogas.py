f = lambda seg,c: sum([v[0]/(v[1]+c) for v in seg])

n,t = [int(i) for i in input().split()]
seg = []
l,r = -2011,10**9
for i in range(n):
    seg.append([int(j) for j in input().split()])
    l = max(l,-seg[i][1])
c = (l+r)/2
curr = f(seg,c)
it = 1000
while abs(curr-t) > 10**(-7) and it > 0:
    if curr >= t:
        l = c
    elif curr < t:
        r = c
    c = (l+r)/2
    curr = f(seg,c)
    it -= 1
print(c)
