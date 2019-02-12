package ru.job4j.io.abuse;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import static org.hamcrest.CoreMatchers.is;

/**
 * Класс содержит тесты класса Abuse.
 */
public class AbuseTest {

    /**
     * Проверка ситуации, когда в тексте не встречено запрещенных слов.
     */
    @Test
    public void whenNoAbuseInTextThenReturnAllText() {
        String text = "The StreamTokenizer class takes an input stream and parses it into \"tokens\","
                + "allowing the tokens to be read one at a time. The parsing process is controlled by a table"
                + "and a number of flags that can be set to various states. The stream tokenizer can recognize identifiers,"
                + "numbers, quoted strings, and various comment styles.";
        String[] abuses = new String[]{"fuck", "bitch"};
        InputStream inputStream = new ByteArrayInputStream(text.getBytes());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Abuse abuse = new Abuse();
        abuse.dropAbuses(inputStream, outputStream, abuses);
        String result = new String(outputStream.toByteArray());
        Assert.assertThat(result, is(text));
    }

    /**
     * Проверка ситуации, когда в тексте встретились и были заменены запрещенные слова.
     */
    @Test
    public void whenHaveAbuseInTextThenReturnTextDroppedAbuses() {
        String text = "The fucking StreamTokenizer class takes an input stream and parses it into \"tokens\","
                + "allowing the tokens to be read one at a time. The parsing process is controlled by a table"
                + "and a number of flags that can be set to various states, bitch. The stream tokenizer can recognize identifiers,"
                + "numbers, quoted strings, and various comment styles.";
        String expected = "The ***ing StreamTokenizer class takes an input stream and parses it into \"tokens\","
                + "allowing the tokens to be read one at a time. The parsing process is controlled by a table"
                + "and a number of flags that can be set to various states, ***. The stream tokenizer can recognize identifiers,"
                + "numbers, quoted strings, and various comment styles.";
        String[] abuses = new String[]{"fuck", "bitch"};
        InputStream inputStream = new ByteArrayInputStream(text.getBytes());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Abuse abuse = new Abuse();
        abuse.dropAbuses(inputStream, outputStream, abuses);
        String result = new String(outputStream.toByteArray());
        Assert.assertThat(result, is(expected));
    }
}