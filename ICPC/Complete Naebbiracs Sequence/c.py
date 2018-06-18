k,n = list(map(lambda x:int(x), input().split()))
arr = list(map(lambda x:int(x), input().split()))

count = [0]*(k+1)
for v in arr:
	count[v] += 1

ok = True
m = (n+1)//k
diff = [None, None]
for i in range(1,len(count)):
	if abs(count[i]-m)>1:
		ok = False
		break
	else:
		if count[i]<m:
			if diff[0] == None:
				diff[0] = i
			else:
				ok = False
				break
		elif count[i]>m:
			if diff[1] == None:
				diff[1] = i
			else:
				ok = False
				break
if ok:
	if diff[0]!=None and diff[1]!=None:
		print ("-"+str(diff[1])+" +"+str(diff[0]))
	elif diff[1]!=None:
		print ("-"+str(diff[1]))
	else:
		print ("+"+str(diff[0]))
else:
	print ("*")

