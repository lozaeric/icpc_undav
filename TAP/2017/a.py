notas = ("DO","DO#","RE","RE#","MI","FA","FA#","SOL","SOL#","LA","LA#","SI")
s = input().split()
i = int(s[0])
n = s[1]
print (notas[notas.index(n)-i])
