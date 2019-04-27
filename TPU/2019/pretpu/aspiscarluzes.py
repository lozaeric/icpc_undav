gcd = lambda a,b: a if b == 0 else gcd(b,a%b)
p,q,s = [int(i) for i in input().split()]
print("yes" if p*q/gcd(p,q)<=s else "no")
