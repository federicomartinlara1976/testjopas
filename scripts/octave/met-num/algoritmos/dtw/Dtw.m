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
					error ("Signature: A must be a matrix");
				endif
				
				if (isreal (b) && isvector (b))
					signature2 = new Signature(b);
				else
					error ("Signature: B must be a matrix");
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
    
			double[][] dtwArray = new double[n+1][m+1];
    
			for(int i = 1; i < n+1; ++i)
				dtwArray[i][0] = Double.POSITIVE_INFINITY;
			end
    
			for(int i = 1; i < m+1; ++i)
				dtwArray[0][i] = Double.POSITIVE_INFINITY;
			end
    
			dtwArray[0][0] = 0;
    
			for(int i = 1; i < n+1; ++i)
				for(int j = 1; j < m+1; ++j)
					double cost = pointDistance(i-1, j-1);
					dtwArray[i][j] = cost + myMin(dtwArray[i-1][j],
                                 dtwArray[i][j-1],
                                 dtwArray[i-1][j-1]);
				end
			end
      
			d = dtwArray(n, m);
   
		endfunction
		
		function p = pointDistance(i, j)
			return sqrt(pow((float)(signature1.x.get(i)-signature2.x.get(j)), 2) + 
                        pow((float)(signature1.y.get(i)-signature2.y.get(j)), 2));
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
