val = [int(i) for i in input().split()]
w,h,d = val 
if 2*d >= w or 2*d >= h:
    print(0)
else:
    print((w-2*d)*(h-2*d))