def solve(i, d):
    if len(word) == i:
        return 0

    a = 10**9
    if d==1 and i*2 <= len(word) and word[:i] == word[i:i*2]:
        a = solve(i*2,0)
    return 1+min(a, solve(i+1,d))

input()
word = input()
print (1+solve(1,1))
