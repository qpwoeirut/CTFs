from types import CodeType


def x():
    pass

code = x.__code__.co_code

print(x.__code__.co_argcount)
print(x.__code__.co_cellvars)
print(x.__code__.co_code)
print(x.__code__.co_consts)
print(x.__code__.co_exceptiontable)
print(x.__code__.co_filename)
print(x.__code__.co_firstlineno)
print(x.__code__.co_flags)
print(x.__code__.co_freevars)
print(x.__code__.co_kwonlyargcount)
print(x.__code__.co_lines)
print(x.__code__.co_linetable)
print(x.__code__.co_lnotab)
print(x.__code__.co_name)
print(x.__code__.co_names)
print(x.__code__.co_nlocals)
print(x.__code__.co_positions)
print(x.__code__.co_posonlyargcount)
print(x.__code__.co_qualname)
print(x.__code__.co_stacksize)
print(x.__code__.co_varnames)
print(x.__code__.replace)

def y():
    pass

y.__code__ = CodeType(0,0,0,0,0,0,code,(),(),(),'Δ','♦','✉︎',0,bytes(),bytes(),(),())
y()