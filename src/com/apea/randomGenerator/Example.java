package com.apea.randomGenerator;

public class Example {

	public static void main(String[] args) {
		TabularGenerator g = new TabularGenerator(10);
		byte[] seq1 = g.randomSeq(15000);
		System.out.println("Доля единиц в последовательности = "
				+ Analyzer.frequencyTest(seq1) + "%");
		System.out.println("Доля единиц в xor-последовательности = "
				+ Analyzer.differentialTest(seq1) + "%");
		System.out.println("*******************************************");
		Analyzer.rankTest(seq1, 3);
		System.out.println("*******************************************");
		System.out.println("Линейная сложность последовательности длины 15000 = "
				+ Analyzer.getLinearСomplexity(seq1));
	}
}