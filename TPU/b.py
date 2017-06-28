def resolver (palabra, longitud):
	divisores = [i for i in range(1, longitud) if longitud%i==0]
	for d in divisores:
		prefijo = palabra[:d]
		if prefijo*(longitud/d)==palabra:
			return prefijo+" "+str(longitud/d)
	return palabra+" 1"

longitud = input()
palabra = raw_input()
print resolver(palabra, longitud)
