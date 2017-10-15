package ru.job4j;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Test.
* @author b.savelev
* @version 2.0
* @since 1.8
*/
public class CalculateTest {

	/**
	* Test for echo method.
	*/
	@Test
	public void whenTakeNameThenThreeEchoPlusName() {
		String input = "b.savelev";
		String expect = "Echo, echo, echo: b.savelev";

		Calculate calc = new Calculate();
		String result = calc.echo(input);
		assertThat(result, is(expect));
	}
}