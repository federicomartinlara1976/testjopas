classdef Signature
	
  properties
		x, y, time
  endproperties
  
  properties (Access = private)
		minX, minY, maxX, maxY
  endproperties
  
	methods
		
    function sig = Signature (a)
			for i=1:rows(a)
				sig.x(i) = a(i, 1);
				sig.y(i) = a(i, 2);
				sig.time(i) = a(i, 5);
			end
      
      sig.minX = -1.0; 
      sig.minY = -1.0; 
      sig.maxX = -1.0; 
      sig.maxY = -1.0;
		endfunction
    
    function normaliseData (sig)
			sig.findMinAndMaxElement();
			sig.translate(sig.x, sig.minX);
			sig.translate(sig.y, sig.minY);
			sig.normalise(300, 100);
		endfunction
    
  endmethods
  
  methods (Access = private)
		
		function find = findMinAndMaxElement (sig)
			for i=1:size(sig.x)
				itemX = sig.x(i);
				itemY = sig.y(i);
      
				if (sig.minX > itemX || sig.minX == -1.0)
					sig.minX = itemX;
				endif
				if (sig.maxX < itemX || sig.maxX == -1.0)
					sig.maxX = itemX;
				endif
				if (sig.minY > itemY || sig.minY == -1.0)
					sig.minY = itemY;
				endif
				if (sig.maxY < itemY || sig.maxY == -1.0)
					sig.maxY = itemY;
				endif
			end
      
      printf("minX = %d, minY = %d, maxX = %d, maxY = %d\n", sig.minX, sig.minY, sig.maxX, sig.maxY);
		endfunction
		
		function trans = translate (sig, list, translate)
			for i=1:size(list)
				list(i) = list(i) - translate;
			end
		endfunction
		
		function norm = normalise (sig, normaliseWidth, normaliseHeight)
			divisorX = (sig.maxX - sig.minX) / normaliseWidth;
			divisorY = (sig.maxY - sig.minY) / normaliseHeight;
			
			for i = 1:size(sig.x)
				sig.x(i) = sig.x(i) / divisorX;
				sig.y(i) = sig.y(i) / divisorY;
			end
	
		endfunction
		
	endmethods
endclassdef
