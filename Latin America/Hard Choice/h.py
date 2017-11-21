l = list(map(lambda x:int(x), input().split()))
m = list(map(lambda x:int(x), input().split()))
s = 0
for i in range(3):
	s += max(0,m[i]-l[i])
print (str(s))
