a,b = [int(i) for i in input().split()]
diff = {}
m = 0
for i in range(1,a+1):
	for j in range(1,b+1):
		k = i+j
		if not k in diff:
			diff[k] = 1
		else:
			diff[k] += 1
		m = max(m,diff[k])
for n in diff.keys():
	if diff[n] == m:
		print(n)
