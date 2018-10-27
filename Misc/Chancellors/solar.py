
import sys

for line in sys.stdin:
    n = int(line)
    res = "S"
    while n>1:
        g,b,r = n+1,n,n-1
        if b%3 == 0:
            res = "B"+res
            n = b//3
        elif g%3 == 0:
            res = "G"+res
            n = g//3
        elif r%3 == 0:
            res = "R"+res
            n = r//3
    print(res)
    