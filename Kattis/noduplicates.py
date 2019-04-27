seen = {}
repeated = False
for w in input().split():
	if w in seen:
		repeated = True
		break
	seen[w] = True 
print("no" if repeated else "yes")
