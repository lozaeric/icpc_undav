n = int(input())
num = []

for i in range(n):
	num.append([int(i) for i in input().split()])	

id1 = sum(num[0])
count = 1
for v in num:
	if sum(v)>id1:
		count += 1
print(count)