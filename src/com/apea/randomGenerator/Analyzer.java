package com.apea.randomGenerator;

import java.util.ArrayList;

/**
 * Класс для тестирования генератора.
 */
public class Analyzer {
	
	/**
	 * Частотный тест.
	 */
	public static double frequencyTest(byte[] seq) {
		double number = 0;
		for (int i = 0; i < seq.length; i++)
			if (seq[i] == 1) {
				number++;
			}
		return (number / seq.length) * 100;
	}
	
	/**
	 * Дифференциальный тест.
	 */
	public static double differentialTest(byte[] seq) {
		double number = 0;
		for (int i = 1; i < seq.length; i++)
			if ((seq[i-1] ^ seq[i]) == 1) {
				number++;
			}
		return (number / (seq.length - 1)) * 100;
	}
	
	/**
	 * Ранговый тест. 
	 */
	public static void rankTest(byte[] seq, int window) {
		
		//массив, который отслеживает частоту подпоследовательностей длиной window
		double[] numbers = new double[(int) Math.pow(2, window)];
		int index;
		for (int i = 0; (i < seq.length) &&
				((i + window) < seq.length); i++) {
			index = 0;
			for (int j = 0; j < window; j++) {
				index <<= 1;
				index ^= seq[i + j];
			}
			numbers[index]++;
		}
		
		for (int i = 0; i < numbers.length; i++) {
			System.out.println("Доля " + Integer.toBinaryString(i)
					+ " в последовательности = "
					+ ((numbers[i] / seq.length) * 100) + "%");
		}
	}
	
	/**
	 * Тест на линейную сложность(алгоритм Berlecamp-Massey).<br>
	 *  Полиномы используются в виде(см.
	 * {@link LFSR#DEFAULT_POLYNOMS}) массива булевских констант,
	 * учитывая 0-ую степень(в отличии от используемых полиномах в {@link LFSR}).
	 * 
	 * @param seq последовательность.
	 * @return регистр, который способен повторить последовательность seq.
	 */
	public static LFSR linearСomplexityTest(byte[] seq) {
		
		//инициализация значений
		int L = 0;   //линейная сложность
		ArrayList<Boolean> C = new ArrayList<Boolean>(); //полином функции
		ArrayList<Boolean> B = new ArrayList<Boolean>(); //вспомогательный полином
		ArrayList<Boolean> buf;
		C.add(true);
		B.add(true);
		int x = 1;
		
		int j;
		byte d;
		for (int N = 0; N < seq.length; N++) {
			d = 0;
			j = 0;
			for (Boolean c: C) {
				if (c) {d ^= seq[N - j];}
				j++;
			}
			
			if (d == 0) {
				x++;
			} else {
				buf = new ArrayList<Boolean>(C);
				
				//коррекция полинома
				j = x;
				for (Boolean b: B) {
					while (j >= C.size())
						C.add(false);
					if (b)
						C.set(j, !C.get(j));
					j++;
				}
				//конец коррекции
				
				if ((2 * L) <= N) {
					L = N + 1 - L;
					B = buf;
					x = 1;
				} else {x++;}
			}
		}
		
		C.remove(0);
		boolean[] polynom = new boolean[C.size()];
		j = 0;
		for (Boolean c: C)
			polynom[j++] = c;
		return new LFSR(L, polynom, 1);
	}
	
	/**
	 * Тест на линейную сложность.
	 * @see #linearСomplexityTest
	 * @param seq последовательность.
	 * @return сложность.
	 */
	public static int getLinearСomplexity(byte[] seq) {
		return linearСomplexityTest(seq).length();
	}
}