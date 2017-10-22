from itertools import permutations

def a():
	global cont,l,o,s,t
	m = l//4
	l -= m*4
	cont += m

def b():
	global cont,l,o,s,t
	m = o//4
	o -= m*4
	cont += m

def c():
	global cont,l,o,s,t
	m = t//4
	t -= m*4
	cont += m

def d():
	global cont,l,o,s,t
	m = min(l//2,s//2)
	l -= m*2
	s -= m*2
	cont += m

def e():
	global cont,l,o,s,t
	m = min(l,s,t//2)
	l -= m
	s -= m
	t -= m*2
	cont += m

def f():
	global cont,l,o,s,t
	m = min(l//2,o//2)
	l -= m*2
	o -= m*2
	cont += m

line = input().split()
ll = int(line[0])
oo = int(line[1])
ss = int(line[2])
tt = int(line[3])
cont = 0

func = [a,b,c,d,e,f]
res = 0
for i in permutations(func):
	cont = 0
	l = ll
	o = oo
	s = ss
	t = tt
	for f in i:
		f()
	res = max(res, cont)
print (res)
