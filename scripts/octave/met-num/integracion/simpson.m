function sum=simpson(a, b, n, h)
  
  sum1=0.0;
  for i=1:2:n-1
    sum1=sum1+4*f(a+i*h);
  end
       
  sum2=0.0;
  for i=2:2:n-2
    sum2=sum2+2*f(a+i*h);
  end
  
  sum=(h/3)*(f(a)+sum1+sum2+f(b));
  
endfunction