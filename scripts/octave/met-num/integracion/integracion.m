function [valor, int, error] = integracion(a, b, tolerancia)
  error = "";
  n0 = 14;
  n = n0;
  h = (b-a) / n0;
  sum = simpson(a,b,n,h);
  int(1) = sum;
  ni = 0;
  for j = 2:8
    n = (2^j) * n0;
    h = (b-a) / n;
    sum = simpson(a,b,n,h);
    int(j) = sum;
    err = (int(j) - int(j-1)) / 15;
    if (abs(err) < tolerancia)
      valor = int(j) + err;
      break;
    endif
    
    ni = j;
  end 
  
  if (ni == 8)
    valor = NaN;
    error = "Convergencia no lograda tras 8 iteraciones.";
  endif
endfunction