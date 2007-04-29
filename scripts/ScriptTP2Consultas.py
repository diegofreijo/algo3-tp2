import random
import sys

def rango(x,y):
    a = random.randrange(x)
    b = random.randrange(y)
    return str(a) + " " + str(b)

cantidad = sys.argv[1]
cantidad = int(cantidad)
s = sys.argv[2]
s = int(s)
t = sys.argv[3]
t = int(t)

salida = "Tp2Ej4.in"
f = open(salida,'w')
f.write(str(cantidad))
f.write('\n')

i = 0

while i < cantidad:
    consulta = rango(s,t)
    f.write(consulta)
    f.write('\n')
    i = i+1

f.close()


    