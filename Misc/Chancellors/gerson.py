t = int(input())

def checkChars(word, must):
    for c in must.lower():
        if not c in word.lower():
            return False
    return True

for i in range(t):
    n,g,p = input(),input(),input()
    ok = checkChars(n, "Gerson") or g == "Y" or checkChars(p, "TRIH")
    print("Y" if ok else "N")
