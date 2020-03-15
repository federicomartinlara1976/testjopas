a = 1.0;
b = 4.0;
tolerancia = 0.000000000000000000000000001;

tic1 = tic();
[valor, int, error] = integracion(a,b,tolerancia);
elapsed_1 = toc(tic1);

fprintf("Integracion: %d\n", elapsed_1);