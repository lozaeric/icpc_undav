input()
s = input()

one = s.count("1")
zero = s.count("0")
if one == zero:
    print("2")
    print(s[:-1]+" "+s[-1])
else:
    print("1")
    print(s)