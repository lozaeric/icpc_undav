import parser 

n = input()
for i in range(n):
    formula = "x+"+raw_input() 
    code = parser.expr(formula).compile() 
    x = 0
    res = eval(code)
    print("It is the ultimate question of life, the universe and everything." if res==42 else "It is not the ultimate question of life, the universe and everything.")
