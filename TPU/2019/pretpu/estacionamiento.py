n = int(input())
for i in range(n):
	m = int(input())
	v = [int(j) for j in input().split()]
	print(2*(max(v)-min(v)))
