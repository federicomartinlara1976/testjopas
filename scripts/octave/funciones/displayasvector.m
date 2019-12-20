function displayasvector(x)
	fprintf('vector:');
  for (i = 1:length(x))
    if (i == length(x))
	    fprintf("%f", x(i));
    else
      fprintf("%f,", x(i));
    endif
  end
	fprintf('\n');
endfunction
