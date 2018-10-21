def is_palindrome(w):
    m = len(w)//2
    for i in range(0,m):
        if w[i] != w[i+m]:
            return False
    return True

w = input()
n = len(w)
c = 0
for i in range(n):
    for j in range(i,n):
        if is_palindrome(w[i:j+1]):
            c += 1
if c%2 == 1:
    print("Odd.")
else:
    print("Or not.")
