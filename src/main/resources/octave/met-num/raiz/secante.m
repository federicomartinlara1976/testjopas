function [x,sol,ni,error]=secante(puntoInicial, aproximacionPrimera, tolerancia, iteraciones)
  x(1) = puntoInicial;
  x(2) = aproximacionPrimera;
  ni = 0;
  error = "";
  
  for i=3:iteraciones
    x(i) = x(i-1)-(f(x(i-1))*(x(i-1)-x(i-2)))/(f(x(i-1))-f(x(i-2)));
    
    if abs(x(i)-x(i-1)) < tolerancia
      sol = x(i);
      break;
    endif
    
    ni = i;
  end
  
  if ni == iteraciones
	sol = NaN;
    error = sprintf('Convergencia no lograda tras %d iteraciones\n', ni);
  endif
endfunction