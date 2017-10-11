def tsearch (l, r, f):
    while (r-l > 0.01):  # precision en x
        m1 = (r+2*l)/3
        m2 = (2*r+l)/3
        if f(m1)>f(m2):  # maximo
            r = m2
        else:
            l = m1
    return l
def bsearch (l, r, v, f):  
    while (r-l > 0.01):  # precision en x
        med = (l+r)/2
        if f(med,v): # mayor
            r = med
        else:
            l = med
    return l

def bsearch_array (l, r, v, arr):
    while (r-l > 1):
        med = (l+r)//2
        if arr[med]>v:
            r = med
        else:
            l = med
    return l

f = lambda x,y: x**2>y  # continua
g = lambda x,y: 1 if x>9 else 0  # booleano
h = lambda x: -((x-25)**2)  # continua

print (bsearch(0, 100, 10, f))
print (bsearch(0, 100, 1, g))
print (tsearch(0, 40, h))
arr = [2,4,6,8,10]
print (bsearch_array(0, len(arr), 8, arr))
