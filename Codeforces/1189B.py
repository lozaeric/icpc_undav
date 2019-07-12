n = int(input())
num = [int(i) for i in input().split()]

num.sort()
if num[-1] < num[-2]+num[-3]:
    print("YES")
    print(num[-1], end=' ')
    for i in range(n-2, -1, -2):
        print(num[i], end=' ')
    for i in range(1-(n-2)%2, n-2, 2):
        print(num[i], end=' ' if i+2 < n-2 else '')
else:
    print("NO")