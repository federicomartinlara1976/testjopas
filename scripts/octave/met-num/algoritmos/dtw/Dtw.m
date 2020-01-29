classdef Dtw
	properties
		
		signature1 = null;
		signature2 = null;
		
	endproperties
  
	methods
		function d = Dtw (a, b)
			if (nargin <> 2)
				print_usage ();
			endif

			if (nargin == 2)
				if (isreal (a) && isvector (a))
					signature1 = new Signature(a);
				else
					error ("DTW: A must be a matrix");
				endif
				
				if (isreal (b) && isvector (b))
					signature2 = new Signature(b);
				else
					error ("DTW: B must be a matrix");
				endif
			endif
		endfunction
		
		function normaliseData ()
			signature1.normaliseData();
			signature2.normaliseData();
		endfunction
		
		function d = findDistance()
			n = size(signature1.x);
			m = size(signature2.x);
    
			dtwArray = zeros(n+1, m+1);
    
			for i=2:n+1
				dtwArray(i, 1) = inf;
			end
    
			for i=2:m+1
				dtwArray(1, i) = inf;
			end
    
			dtwArray(1, 1) = 0;
    
			for i=2:n+1
				for j=2:m+1
					cost = pointDistance(i-1, j-1);
					dtwArray(i, j) = cost + myMin(dtwArray(i-1, j), dtwArray(i, j-1), dtwArray(i-1, j-1));
				end
			end
      
			d = dtwArray(n, m);
   
		endfunction
		
		function p = pointDistance(i, j)
			p = sqrt((signature1.x(i) - signature2.x(j))^2) + 
                     (signature1.y(i) - signature2.y(j))^2);
		endfunction
  
  
		function min = myMin(a, b, c)
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
