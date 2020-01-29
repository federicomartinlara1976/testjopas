function distance = signatureRecognition(a, b)
	algoritmo = Dtw(a, b);
	algoritmo.normaliseData();
	distance = algoritmo.findDistance();
endfunction