set style data points
set term png


set xlabel "Dimensiones de la matriz (k)"
set ylabel "Operaciones básicas"
set output "Tp1Ej3(multiplicar).png"
plot '..\..\ej3\dat\Tp1Ej3(multiplicar).dat' tit 'Práctica', x*x*x tit 'Teórica [k^3]', x*x*x+5*x*x tit 'Teórica ajustada [k^3 + 5*k^2]


set xlabel "Exponente (n)"
set ylabel "Cantidad de multiplicaciones"
set output "Tp1Ej3(cant_mul).png"
plot '..\..\ej3\dat\Tp1Ej3(cant_mul).dat' tit 'Práctica', log(x)/log(2) tit 'Teórica [log(n)]', log(x)/log(2) + 1 tit 'Mejor caso teórico [log2(n) + 1]', 2*log(x)/log(2) - 1 tit 'Teórica ajustada [2*log2(n) - 1]'


set xlabel "Dimensiones de la matriz (k)"
set ylabel "Exponente (n)"
set zlabel "Operaciones básicas"
set output "Tp1Ej3(potenciar).png"
splot '..\..\ej3\dat\Tp1Ej3(potenciar).dat' tit 'Práctica', log(y)/log(2)*x*x*x tit 'Teórica [log2(n)*k^3]', log(y)/log(2)*x*x*x + 7*x*x*x tit 'Teórica ajustada [log2(n)*k^3 + 7*k^3]'


set xlabel "Dimensiones de la matriz (k)"
set ylabel "Exponente (n)"
set zlabel "Operaciones básicas"
set output "Tp1Ej3(potenciar_fb).png"
splot '..\..\ej3\dat\Tp1Ej3(potenciar).dat' tit 'Óptimo', '..\..\ej3\dat\Tp1Ej3(potenciar_fb).dat' tit 'Fuerza Bruta'


set output
set terminal win