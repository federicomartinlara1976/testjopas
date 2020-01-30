function distance = signatureRecognition(a, b)
	algoritmo = Dtw(a, b);
	algoritmo.normaliseData();
	[dist, dtwArray] = algoritmo.findDistance();
endfunction