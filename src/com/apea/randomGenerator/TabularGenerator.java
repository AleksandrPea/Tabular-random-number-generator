package com.apea.randomGenerator;

/**
 * Табличный генератор случайных битов, последовательностей битов.
 */
public class TabularGenerator {
	
	private LFSR[] block;    // Блок регистров.
	private Table t;
	
	/**
	 * Принимает количество регистров.
	 * @param number кол-во регистров LFSR.
	 */
	public TabularGenerator(int number) {
		block = new LFSR[number];
		for (int i = 0; i < block.length; i++) {
			block[i] = new LFSR(8 + i, 120 + i * 120);
		}
		t = new Table(number);
	}
	
	/**
	 * @return случаный бит.
	 */
	public byte random() {
		int i = block. length - 1;
		int index = block[i].next();
		while (--i >= 0) {
			index <<= 1;
			index |= block[i].next();
		}
		return t.get(index);
	}
	
	/**
	 * @param sampling объем последовательности.
	 * @return последовательность битов.
	 */
	public byte[] randomSeq(int sampling) {
		byte[] result = new byte[sampling];
		int i, index;
		for (int j = 0; j < result.length; j++) {
			i = block. length - 1;
			index = block[i].next();
			while (--i >= 0) {
				index <<= 1;
				index ^= block[i].next();
			}
			result[j] = t.get(index);
		}
		return result;
	}
}