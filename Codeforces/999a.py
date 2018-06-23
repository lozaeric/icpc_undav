n,k = [int(i) for i in input().split()]
num = [int(i) for i in input().split()]

i = 0
j = n-1
res = 0
while i<n and num[i]<=k:
    res += 1
    i+=1
while i<=j and num[j]<=k:
    res += 1
    j-=1
print(res)
