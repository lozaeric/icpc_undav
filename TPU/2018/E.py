teams = []
for i in range(8):
    teams.append(int(input()))
total = sum(teams)
diff = 1000000
for i in range(8):
    for j in range(i+1,8):
        for k in range(j+1,8):
            for l in range(k+1,8):
                a = teams[i]+teams[j]+teams[k]+teams[l]
                b = total - a
                diff = min(abs(a-b),diff)
print(diff)
