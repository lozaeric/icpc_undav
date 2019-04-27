n = int(input())
for i in range(n):
	m = int(input())
	r = 1
	for j in range(2, m+1, 1):
		r *= j
		r %= 10
	print(r)
