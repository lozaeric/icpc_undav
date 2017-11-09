import sys, math

data = list(sys.stdin)
for i in range(0,len(data),2):
	n = int(data[i])
	arr = list(map(lambda x: int(x), data[i+1].split()))
	arr.sort()
	opt = [0]*n
	j = 0
	jj = -1
	for t in range(n):
		if t%2==0:
			opt[j] = arr[t]
			j += 1
		else:
			opt[jj] = arr[t]
			jj -= 1
	suma = 0
	for p in range(n-1, -1, -1):
		suma += opt[p]*opt[p-1]
	res = (math.sin((2*math.pi)/n)*suma)/2
	print ('{0:.3f}'.format(res)) 
