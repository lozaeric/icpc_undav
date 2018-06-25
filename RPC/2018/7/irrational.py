p,q = [int(i) for i in input().split()]

if (p % 2 == 1 and q % 2 == 1):
    print(1)
elif (p % 2 == 0):
    print(0)
elif (q > p):
    print(2)
else:
    print(0)
