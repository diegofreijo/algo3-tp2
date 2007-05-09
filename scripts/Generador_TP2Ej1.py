import random
import sys

# Cantidad de instancias
instancias = int(sys.argv[1])
# Ancho maximo de las imagenes
pantalla_x = int(sys.argv[2])
# Alto maximo de las imagenes
pantalla_y = int(sys.argv[3])
# Cantidad maxima de imagenes por instancia
imagenes_max = int(sys.argv[4])

f = open('../in/Tp2Ej1.in','w')

# Escribo la cantidad de instancias
f.write(str(instancias) + '\n')

for i in range(instancias):
	# Genero la cantidad de imagenes
	imagenes = random.randint(1,imagenes_max)
	f.write(str(imagenes) + '\n')
	for j in range(imagenes):
		# Genero la imagen
		xi = random.randint(1, pantalla_x - 1)
		xf = random.randint(xi, pantalla_x)
		yi = random.randint(1, pantalla_y - 1)
		yf = random.randint(yi, pantalla_y)
		# Escribo la imagen
		f.write(str(xi) + ' ' + str(xf) + ' ' + str(yi) + ' ' + str(yf) + '\n')
f.close()
