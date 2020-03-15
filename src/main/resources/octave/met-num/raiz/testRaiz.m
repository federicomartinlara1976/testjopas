x = [1.0;2.0;3.0;4.0;5.0];

c = 2.5;

tic1 = tic();
[Y1,DD1,SP1] = interpoladorFuncion(c,x);
elapsed_1 = toc(tic1);

tic2 = tic();
[Y2,DD2,SP2] = interpoladorTablaValores(c,x);
elapsed_2 = toc(tic2);

fprintf("Interpolador por funcion: %d\n", elapsed_1);
fprintf("Interpolador por tabla de valores: %d\n", elapsed_2);