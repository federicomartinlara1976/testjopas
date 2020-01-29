classdef Signature
	properties
		matrix = [];
		
		minX = -1.0;
		minY = -1.0;
		maxX = -1.0;
		maxY = -1.0;
		
		x = [];
		y = [];
		time = [];
	endproperties
  
	methods
		function s = Signature (a)
			if (nargin > 1)
				print_usage ();
			endif

			if (nargin == 1)
				if (isa (a, "Signature"))
					s.matrix = a.matrix;
				elseif (isreal (a) && isvector (a))
					s.matrix = a;
				else
					error ("Signature: A must be a matrix");
				endif
				
				initializeData(s.matrix);
			endif
		endfunction
		
		function initializeData (m)
			for i=1:rows(m)
				x(i) = m(i, 1);
				y(i) = m(i, 2);
				time(i) = m(i, 4);
			end
		endfunction
		
		function normaliseData ()
			findMinAndMaxElement();
			translate(x, minX);
			translate(y, minY);
			normalise(300, 100);
		endfunction
		
		function findMinAndMaxElement ()
			for i=1:size(x)
				itemX = x(i);
				itemY = y(i);
      
				if (minX > itemX || minX == -1.0)
					minX = itemX;
				endif
				if (maxX < itemX || maxX == -1.0)
					maxX = itemX;
				endif
				if (minY > itemY || minY == -1.0)
					minY = itemY;
				endif
				if (maxY < itemY || maxY == -1.0)
					maxY = itemY;
				endif
			end
		endfunction
		
		function translate (list, translate)
			for i=1:size(list)
				list(i) = list(i) - translate;
			end
		endfunction
		
		function normalise (normaliseWidth, normaliseHeight)
			divisorX = (maxX - minX) / normaliseWidth;
			divisorY = (maxY - minY) / normaliseHeight;
			
			for i = 1:size(x)
				x(i) = x(i) / divisorX;
				y(i) = y(i) / divisorY;
			end
	
		endfunction
		
	endmethods
endclassdef
