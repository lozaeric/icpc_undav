def solve(x):
    for a in range(1, x+1):
        for b in range(1, x+1):
            if isValid(a, b, x):
                print(a, b)
                return
    print(-1)
    return

isValid = lambda a,b,x: 1<=a and a<=x and 1<=b and b<=x and a%b==0 and a*b>x and a/b<x
x = int(input())
solve(x)