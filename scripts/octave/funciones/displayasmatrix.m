function displayasmatrix(x)
  [nr, nc] = size(x);
  
	fprintf('matrix:');
  for (i = 1:nr)
    for (j = 1:nc)
      fprintf("%f", x(i,j));
      
      if (j < nc)
        fprintf(",");
      endif
    end
    
    if (i < nr)
      fprintf(";");
    endif 
    
  end
	fprintf('\n');
endfunction