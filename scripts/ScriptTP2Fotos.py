import random
import sys

def randomSinCero(a):
	ret = 0
	while ret == 0:
		ret = random.randrange(a)
	return ret

def rango(x):
    a = random.randrange(x)
    b = random.randrange(x)
    while b < a:
        b = random.randrange(x)
    return str(a) + " " + str(b)


cantidad = sys.argv[1]
cantidad = int(cantidad)

s = sys.argv[2]
s = int(s)
t = sys.argv[3]
t = int(t)

rangofotos = sys.argv[4]
rangofotos = int(rangofotos)

salida = "Tp2Ej1.in"
f = open(salida,'w')
f.write(str(cantidad))
f.write('\n')

i = 0

while i < cantidad:
	fotos = randomSinCero(rangofotos)
	k = 0
	f.write(str(fotos))
	f.write('\n')
	while k < fotos:
		x = rango(s)
		y = rango(t)
		f.write(str(x))
		f.write(' ')
		f.write(str(y))
		f.write('\n')
		k = k+1
	i = i+1

f.close()
        
        








    