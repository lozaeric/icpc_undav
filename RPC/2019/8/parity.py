w = input()
letters = {}
for c in w:
    if c in letters:
        letters[c] += 1
    else:
        letters[c] = 1
odd = True
even = True
for c in letters:
    v = letters[c]
    if v%2 == 0:
        odd = False
    else:
        even = False 
if not odd and not even:
    print(2)
elif odd:
    print(1)
elif even:
    print(0)