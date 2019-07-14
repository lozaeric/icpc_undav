n = int(input())
edges = [0]*(n+1)
isPossible = True 

for i in range(n-1):
    a,b = [int(i) for i in input().split()]
    edges[a]+=1
    edges[b]+=1
for d in edges:
    if d == 2:
        isPossible = False 
        break
print("YES" if isPossible else "NO")