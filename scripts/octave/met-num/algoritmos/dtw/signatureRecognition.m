function distance = signatureRecognition(a, b)
	algoritmo = new Dtw(a, b);
	algoritmo.normaliseData();
	distance = algoritmo.findDistance();
endfunction