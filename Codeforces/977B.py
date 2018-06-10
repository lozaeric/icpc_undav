from itertools import combinations

input()
word = input()

res = 0
wres = ""
for i in set(combinations(word,2)):
    w = ''.join(i)
    count = sum([1 for i in range(len(word)-1) if w == word[i:i+2]])
    if count > res:
        res = count
        wres = w
print(wres)
