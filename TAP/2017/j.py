n,m = map(lambda x: int(x), input().split(' '))
nombres = {}
for i in range(n):
	for nom in input().split(' '):
		if not nom in nombres:
			nombres[nom] = [0]*4
		nombres[nom][i//30] |= 1<<(i%30)

if len(nombres)>2*m:
	print ("N")
else:
	usados = set()
	diferentes = len(nombres)
	for nom in nombres:
		for nom2 in nombres:
			if nom != nom2 and not nom in usados and not nom2 in usados:
				ok = True
				nn = n
				for i in range(n//30+1):
					enc = nombres[nom][i] ^ nombres[nom2][i]
					if (nn<30 and enc != 2**nn-1) or (nn>=30 and enc != 2**30-1):
						ok = False
						break
					nn -= 30
				if ok:
					usados.add(nom)
					usados.add(nom2)
					diferentes -= 1
	if diferentes <= m:
		print ("S")
	else:
		print ("N")
