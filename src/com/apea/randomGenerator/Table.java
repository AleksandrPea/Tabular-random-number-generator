package com.apea.randomGenerator;

/**
 * Таблица со случайными битами.
 * @author User
 */
public class Table {
	
	private byte[] table;   //Структура таблицы
	
	/**
	 * Принимает число, которое определяет размер
	 * таблицы(размер таблицы равен 2<sup>{@code size}</sup>).
	 * @param size - определяющее число. В контексте
	 * этого приложения - это количество регистров.
	 */
	public Table(int size) {
		table = new byte[(int) Math.pow(2, size)];
		byte random;
		for (int i = 0; i < table.length; i++) {
			random = (byte) (Math.random() * 100);
			table[i] = random % 2 == 0 ? (byte) 0 : 1;
		}
	}
	
	/**
	 * Возвращает бит по соответствующему индексу.
	 * @param index - индекс, начиная с 0.
	 * @return 0 или 1.
	 */
	public byte get(int index) {
		return table[index];
	}
}