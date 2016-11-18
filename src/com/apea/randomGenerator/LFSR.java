package com.apea.randomGenerator;

/**
 * n-битовый LFSR(8 =< n =< 19).
 */
public class LFSR {
	
	/**
	 * Полиномы по-умолчанию, начиная с разрядности 8.<br>
	 * Они представляются массивом булевских констант таким образом:
	 * коду {@code false, true, false} соответсвует полином:
	 * 1 + x<sup>2</sup> + x<sup>4</sup>. Как видно, старшая
	 * степень и нулевая не учитываются.
	 */
	private static final boolean[][] DEFAULT_POLYNOMS = {
			
			{false, true, true, true, false, false, false},
			
		    {false, false, false, true, false, false, false,
				false},
				
		    {false, false, true, false, false, false, false,
				false, false},
				
		    {false, true, false, false, false, false, false,
			    false, false, false},
			    
			{true, false, false, true, false, true, false,
			    false, false, false, false},
			    
			{true, false, true, true, false, false, false,
			    false, false, false, false, false},
			    
			{true, false, false, false, false, true, false,
			    false, false, true, false, false, false},
			    
			{true, false, false, false, false, false, false,
			    false, false, false, false, false, false, false},
			    
			{true, false, true, false, false, false, false,
			    false, false, false, false, true, false, false, false},
			    
			{false, false, true, false, false, false, false,
			    false, false, false, false, false, false, false, false, 
			    	false},
			    	
	    	{false, false, false, false, false, false, true,
			    false, false, false, false, false, false, false, false, 
			    	false, false},
			    	
	    	{true, true, false, false, true, false, false,
			    false, false, false, false, false, false, false, false, 
			    	false, false, false}
	};
	/**
	 * Действующий полином.
	 */
	private boolean[] polynom;
	
	/**
	 * Внутреннее состояние регистра.
	 */
	private byte[] state;
	
	/**
	 * Принимает разрядность регистра и инициализирующее значение.
	 * @param bits разрядность.
	 * @param seed начальное значение.
	 */
	public LFSR(int bits, int seed) {
		this(bits, DEFAULT_POLYNOMS[bits - 8], seed);
	}
	
	/**
	 * Принимает разрядность регистра, соответствующий ей
	 * полином в форме булевого массива и инициализирующее значение.
	 * @param bits разрядность.
	 * @param polynom полином.
	 * @param seed начальное значение.
	 * @see  #DEFAULT_POLYNOMS
	 */
	public LFSR(int bits, boolean[] polynom, int seed) {
		state = new byte[bits];
		for (int i = 0; i < state.length; i++) {
			state[i] = (byte) (seed & 1);
			seed >>= 1;
		}
		this.polynom = polynom;
	}
	
	/**
	 * 
	 * @return следующий бит.
	 */
	public byte next() {
		byte buf;
		byte current = state[0];
		byte last = state[state.length - 1];
		state[0] = last;
		for (int i = 0; i < state.length - 1; i++) {
			buf = state[i + 1];
			if (polynom[i]) {
				state[i + 1] = (byte) (current ^ last); 
			} else state[i + 1] = current;
			current = buf;
		}
		return current;
	}
	
	/**
	 * @return разрядность регистра.
	 */
	public int length() {
		return state.length;
	}
}