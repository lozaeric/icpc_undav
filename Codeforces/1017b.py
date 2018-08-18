n = int(input())
a = input()
b = input()
aa = "0011"
bb = "0101"
counts = [0,0,0,0]

for i in range(n):
	for j in range(len(aa)):
		if a[i]==aa[j] and b[i]==bb[j]:
			counts[j]+=1
res = counts[0]*counts[2]+counts[0]*counts[3]+counts[1]*counts[2]
print(res)