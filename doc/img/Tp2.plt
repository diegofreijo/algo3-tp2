set style data points
set term png



set xlabel "Cantidad de imágenes (n)"
set ylabel "Operaciones básicas"
set output "Tp2(armado).png"
plot '..\..\dat\Tp2(armado).dat' tit 'Práctica', x * log(x)/log(2) tit 'Teórica [n*log(n)]', 10 * x * log(x)/log(2) tit 'Teórica ajustada [10*n*log(n)]'


set xlabel "Cantidad de imágenes (n)"
set ylabel "Operaciones básicas"
set output "Tp2(consulta).png"
plot '..\..\dat\Tp2(consulta).dat' tit 'Práctica', x + log(x)/log(2) tit 'Teórica [log(n)+n]', 3*log(x)/log(2) + 3*x tit 'Teórica ajustada [3(log(n)+n)]'


set xlabel "Cantidad de imágenes (n)"
set ylabel "Operaciones básicas"
set output "Tp2(fusion).png"
plot '..\..\dat\Tp2(fusion).dat' tit 'Práctica', x tit 'Teórica [k]', 6*x tit 'Teórica ajustada [6k]'


set xlabel "Cantidad de imágenes (n)"
set ylabel "Operaciones básicas"
set output "Tp2(fb).png"
plot '..\..\dat\Tp2(consulta_mas_fusion).dat' tit 'Con fusión', '..\..\dat\Tp2(consulta).dat' tit 'Sin fusión', '..\..\dat\Tp2(fb).dat' tit 'Fuerza Bruta'



set output
set terminal win