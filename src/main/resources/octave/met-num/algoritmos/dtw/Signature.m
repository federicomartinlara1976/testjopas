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
      
      sig.minX = -1; 
      sig.minY = -1; 
      sig.maxX = -1; 
      sig.maxY = -1;
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
			sig.minX = min(sig.x);
			sig.maxX = max(sig.x);
			
			sig.minY = min(sig.y);
			sig.maxY = max(sig.y);
      
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
				if eq(divisorX, 0)
					sig.x(i) = inf;
				else
					sig.x(i) = sig.x(i) / divisorX;
				endif
				
				if eq(divisorY, 0)
					sig.y(i) = inf;
				else
					sig.y(i) = sig.y(i) / divisorY;
				endif
			end
	
		endfunction
		
	endmethods
endclassdef
