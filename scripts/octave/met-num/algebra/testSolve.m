A = [1.0,5.0,2.0;2.0,9.0,7.0;8.0,5.0,3.0];

b = [4.0;6.0;1.0];

tic1 = tic();
c = solve(A, b);
elapsed_1 = toc(tic1);

fprintf("Resolución: %d\n", elapsed_1);