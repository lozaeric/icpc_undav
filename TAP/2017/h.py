n = int(input())
num = list(map(lambda x:int(x), input().split()))
m = 0
up = True
ant =  num[0]
for v in num[1:]:
    if up and v<ant:
        m += 1
        up = False
    elif not up and v>ant:
        up = True
    ant = v
if up and num[-1]!=0:
    m += 1
print (m)

