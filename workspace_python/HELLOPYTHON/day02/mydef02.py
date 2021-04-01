# JS function과 같은 기능 def
# 멀티 리턴
def add_min_mul_div_mod(a, b):
    return a+b, a-b, a*b, a/b,a%b


sum, min, mul, div, mod = add_min_mul_div_mod(1, 5)

print(sum,min,mul,div,mod)