n = int(input())
a = [1 if c=="i" else 0 for c in input()]
b = [1 if c=="i" else 0 for c in input()]
numa = 0
numb = 0
for i in range(n):
    numa |= a[i]<<i
for i in range(n):
    numb |= b[i]<<i

if numa^numb == (1<<n)-1:
    print("SI")
else:
    print("NO")
