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
		
		function d = findDistance(dtw)
			n = size(dtw.signature1.x);
			m = size(dtw.signature2.x);
    
			dtwArray = zeros(n+1, m+1);
    
			for i=2:n+1
				dtwArray(i, 1) = inf;
			end
    
			for i=2:m+1
				dtwArray(1, i) = inf;
			end
    
			dtwArray(1, 1) = 0;
    
			for i=2:n
				for j=2:m
					cost = dtw.pointDistance(i-1, j-1);
					dtwArray(i, j) = cost + dtw.myMin(dtwArray(i-1, j), dtwArray(i, j-1), dtwArray(i-1, j-1));
				end
			end
      
			d = dtwArray(n, m);
   
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
