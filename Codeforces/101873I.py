mem = [0]*300000

def solve():
    for i in range(m, n):
        mem[i] = max(mem[i-1], val[i]+mem[i-m])
    return mem[n-1]

n,m = [int(i) for i in input().split()]
val = [int(i) for i in input().split()]
print(solve())