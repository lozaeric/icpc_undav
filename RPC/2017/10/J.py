import sys

def solve (p, q, validas):
	validas.sort()
	correctas = []
	for i in validas:
		pila = []
		k = 0
		s = ""
		for j in i:
			if j=="i":
				pila.append(p[k])
				k += 1
			else:
				s += pila.pop()
		if s == q:
			correctas.append(i)
	return correctas
	
validas = ['iiiioooo','iiioiooo','iioiiooo','ioiiiooo','iiiooioo','iioioioo','ioiioioo','iiooiioo','ioioiioo','iiioooio','iioiooio','ioiiooio','iiooioio','ioioioio']
n = int(input())
for i in range(n):
	p = input()
	q = input()
	respuesta = solve(p, q, validas)
	print ("Output for "+p+" "+q)
	print("[")
	for r in respuesta:
		print(' '.join(r))
	print("]")
