import math

a, p1 = [int(i) for i in input().split()]
r, p2 = [int(i) for i in input().split()]

option1 = p1/a
option2 = p2/(math.pi*r*r)
print("Whole pizza" if option2 < option1 else "Slice of pizza")