input()
s = input()

e = 0
ok = True
for v in s:
    if v == "(":
        e += 1
    elif v == ")":
        e -= 1
    if e < -1:
        ok = False
print("Yes" if ok and e == 0 else "No")