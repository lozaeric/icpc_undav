sumA = [0]
sumE = [0]
sumO = [0]
for j in range(1, 20011):
	sumA.append(sumA[j-1]+j)
	if j%2==1:
		k = (j+1)//2
		sumO.append(sumO[k-1]+j)
	else:
		k = j//2
		sumE.append(sumE[k-1]+j)

n = int(input())
for i in range(n):
	a,m = [int(j) for j in input().split()]
	s1,s2,s3 = sumA[m],sumO[m],sumE[m]
	print(a,s1,s2,s3)
