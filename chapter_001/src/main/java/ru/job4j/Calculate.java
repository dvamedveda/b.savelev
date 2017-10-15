package ru.job4j;

/**
* Главный класс программы.
* В дальнешем будет дорабатываться
* Сейчас его задача - вывести учебную фразу
* @author - b.savelev
*/
public class Calculate {

	/**
	* Вывод на консоль фразы Hello world.
	* @param args - аргументы командной строки.
	*/
	public static void main(String[] args) {
		System.out.println("Hello world!");
	}

	/**
	* Method echo.
	* @param name Your name
	* @return Echo plus your name
	*/
	public String echo(String name) {
		return "Echo, echo, echo: " + name;
	}
}