function [Y,DD,SP]=interpoladorTablaValores(puntoInterpolar, X)
  Y = zeros(size(X));
  DD = zeros(size(X), size(X)-1);
  
  for i=1:size(X)
    Y(i) = X(i);
    DD(i,1) = Y(i);
  end
  
  for j=2:size(X)
    for i=1:size(X)-j
      DD(i,j) = (DD(i+1,j-1)-DD(i,j-1))/(X(i+j)-X(i));
    end
  end
  
  SP = DD(1,1);
  PP = 1;
  
  for i=2:size(X)-1
    PP = PP*(puntoInterpolar-X(i-1));
    SP = SP + DD(1,i)*PP;
  end
  
endfunction