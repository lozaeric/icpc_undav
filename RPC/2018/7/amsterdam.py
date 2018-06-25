import math

m,n,r = [float(i) for i in input().split()]
ax,ay,bx,by = [int(i) for i in input().split()]
res = 10**9+11

for i in range(0,min(ay,by)+1):
    lc = (r*i)/n
    dx = (math.pi*lc*abs(ax-bx))/m
    dy = (r*(abs(ay-by)+2*(min(ay,by)-i)))/n
    res = min(res,dx+dy)

print("{0:.6f}".format(res))
