k,p,x = [float(i) for i in input().split()]
cost = lambda k,p,x,n: n*x+(k*p)/n
prev, v = cost(k,p,x,1), cost(k,p,x,2)
n = 3
while prev > v:
    prev = v
    v = cost(k,p,x,n)
    n += 1
print("{:.3f}".format(prev))
