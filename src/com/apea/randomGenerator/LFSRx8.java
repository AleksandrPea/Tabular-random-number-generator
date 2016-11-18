package com.apea.randomGenerator;

/**
 * 8-битовый LFSR.
 */
@Deprecated
public class LFSRx8 {
	
	/**
	 * ѕолином, начина€ с 0-вой степени и заканчива€ n-1.
	 */
	private boolean[] polynom = {true, false, true, true, true, false, false, false};
	
	/**
	 * ¬нутреннее состо€ние регистра.
	 */
	private int[] state = new int[8];
	
	/**
	 * ѕринимает начальное значение.
	 * @param seed начальное значение.
	 */
	public LFSRx8(int seed) {
		for (int i = 0; i < state.length; i++) {
			state[i] = seed & 1;
			seed >>= 1;
		}
	}
	
	public int next() {
		int buf;
		int current = state[0];
		state[0] = state[7];
		for (int i = 0; i < state.length - 1; i++) {
			buf = state[i + 1];
			if (polynom[i + 1]) {
				state[i + 1] = current ^ state[7]; 
			} else state[i + 1] = current;
			current = buf;
		}
		return current;
	}
}