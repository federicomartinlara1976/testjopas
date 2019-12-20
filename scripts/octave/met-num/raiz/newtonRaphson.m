function [x,sol,ni,error]=newtonRaphson(puntoInicial, tolerancia, iteraciones)
  x(1) = puntoInicial;
  ni = 0;
  error = "";
  
  for i=2:iteraciones
    x(i) = x(i-1) - (f(x(i-1)))/df(x(i-1));
    
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
