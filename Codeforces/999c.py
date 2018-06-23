from itertools import compress

n,k = [int(i) for i in input().split()]
w = input()

letters = {}
for i in range(len(w)):
    if not w[i] in letters:
        letters[w[i]] = []
    letters[w[i]].append(i)

deleted = [1]*n
for i in sorted(letters.keys()):
    if k==0:
        break
    for j in letters[i]:
        if k==0:
            break
        deleted[j] = 0
        k -= 1
    
print(''.join(compress(list(w),deleted)))
