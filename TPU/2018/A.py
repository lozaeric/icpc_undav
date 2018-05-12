input()
n = input()
d = int(input())

resto = 0
for i in range(len(n)):
    resto = str(resto)+n[i]
    resto = int(resto)%d

if resto == 0:
    print("SI")
else:
    print("NO")
