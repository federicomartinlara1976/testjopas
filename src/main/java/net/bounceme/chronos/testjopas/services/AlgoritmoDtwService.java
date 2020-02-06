package net.bounceme.chronos.testjopas.services;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import net.bounceme.chronos.logger.Log;
import net.bounceme.chronos.logger.LogFactory;
import net.bounceme.chronos.utils.calc.dto.MatrixDTO;

@Service("algoritmoDtwService")
public class AlgoritmoDtwService {

	/** The logger. */
	private Log logger;

	/**
	 * Initialize.
	 */
	@PostConstruct
	public void initialize() {
		logger = LogFactory.getInstance().getLogger(AlgoritmoDtwService.class, "LOG4J");
	}
	
	/**
	 * @param parameters
	 * @return
	 */
	public BigDecimal[][] getParametersMatrix(List<String[]> parameters) {
		int f = parameters.size();
		int c = parameters.get(0).length;
		
		BigDecimal[][] matrix = new BigDecimal[f][c];
		
		int i=0;
		int j=0;
		for (String[] tokens : parameters) {
			
			for (String num : tokens) {
				Float value = Float.parseFloat(num);
				matrix[i][j] = BigDecimal.valueOf(value);
				j++;
			}
			
			j=0;
			i++;
		}
		
		return matrix;
	}
	
	/**
	 * @param name
	 * @param value
	 * @return
	 */
	public MatrixDTO getAsMatrix(String name, BigDecimal[][] value) {
		return new MatrixDTO(name, value);
	}
}
