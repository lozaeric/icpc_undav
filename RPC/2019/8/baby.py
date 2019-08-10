int(input())
num = input().split()

i = 1 
ok = True
for n in num:
    if n != "mumble" and i != int(n):
        ok = False
    i += 1

print("makes sense" if ok else "something is fishy")