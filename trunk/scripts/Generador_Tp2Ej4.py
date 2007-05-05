import random
import sys

# Cantidad de consultas
consultas = int(sys.argv[1])
# Ancho maximo de las imagenes
pantalla_x = int(sys.argv[2])
# Alto maximo de las imagenes
pantalla_y = int(sys.argv[3])

f = open('Tp2Ej4.in','w')

# Escribo la cantidad de consultas
f.write(str(consultas) + '\n')

for i in range(consultas):
	# Genero las coordenadas
	x = random.randint(1, pantalla_x)
	y = random.randint(1, pantalla_y)
	# Escribo la consulta
	f.write(str(x) + ' ' + str(y) + '\n')
f.close()



    
