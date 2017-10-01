def jugar (ca, cb):
    j = ca[-1]
    del ca[-1]
    men = [i for i,v in enumerate(cb) if v<j]
    if len(men)>0:
        del cb[men[-1]]
        return False
    else:
        del cb[-1]
        return True
    
s = input().split()
ca = list(map(lambda x:int(x), s))
cb = [i for i in range(1,8) if not i in ca][:3]
ca.sort()
a = 0
b = 0
manoA = True
for i in range(3):
    if manoA:
        manoA = jugar(ca,cb)
    else:
        manoA = not jugar(cb,ca)
    if manoA:
        a += 1
    else:
        b += 1
if a>b:
    print ("S")
else:
    print ("N")
