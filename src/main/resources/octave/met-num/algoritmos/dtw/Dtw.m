classdef Dtw
	properties
		
		signature1
		signature2
		
	endproperties
  
	methods
		function dtw = Dtw (a, b)
		  dtw.signature1 = Signature(a);
		  dtw.signature2 = Signature(b);
		endfunction
		
		function norm = normaliseData (dtw)
			dtw.signature1.normaliseData();
			dtw.signature2.normaliseData();
		endfunction
		
		function [dist, dtwArray] = findDistance(dtw)
			[nr1, nc1] = size(dtw.signature1.x);
			[nr2, nc2] = size(dtw.signature2.x);
			m = nc1 + 1;
			n = nc2 + 1;
      printf("m = %d, n = %d\n", m, n);
	
			for i=2:n
				dtwArray(i, 1) = inf;
			end
    
			for i=2:m
				dtwArray(1, i) = inf;
			end
    
			dtwArray(1, 1) = 0;
			
			rows = rows(dtwArray) - 1;
			cols = columns(dtwArray) - 1;
    
			for i=2:rows
				for j=2:cols
					cost = dtw.pointDistance(i-1, j-1);
					dtwArray(i, j) = cost + dtw.myMin(dtwArray(i-1, j), dtwArray(i, j-1), dtwArray(i-1, j-1));
					%printf("dtwArray(%d, %d) = %d\n", i, j, dtwArray(i, j));
				end
			end
      
			d = dtwArray(nc1, nc2);
   
		endfunction

  endmethods

  methods (Access = private)  
   
		function p = pointDistance(dtw, i, j)
			p = sqrt((dtw.signature1.x(i) - dtw.signature2.x(j))^2 + (dtw.signature1.y(i) - dtw.signature2.y(j))^2);
		endfunction
  
  
		function min = myMin(dtw, a, b, c)
			if (a < b)
				if (a < c)
					min = a;
				else 
					min = c;
				endif
			else
				if(b < c)
					min = b;
				else 
					min = c;
				endif
			endif
		endfunction
		
	endmethods
endclassdef
