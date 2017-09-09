t = int(input())
for tt in range(t):
    num = [int(n) for n in input().split()]
    h = max(num)
    hh = num.count(h)
    r = 0
    if hh==1:
        r = len(num)-num.index(h)
    elif hh==2:
        indexes = [i for i in range(len(num)) if num[i]==h]
        r = indexes[1]-indexes[0]+1
    else:
        indexes = [i for i in range(len(num)) if num[i]==h]
        for i in range(1,len(indexes),2):
            r += indexes[i]-indexes[i-1]+1
        if hh%2==1:
            r += len(num)-indexes[-1]
    print (r)
