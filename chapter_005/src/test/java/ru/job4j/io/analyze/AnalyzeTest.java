package ru.job4j.io.analyze;

import org.junit.Test;

import java.io.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Класс содержит тесты для класса анализа доступности сервера.
 */
public class AnalyzeTest {

    /**
     * Системные разделители пути и строк.
     */
    private String slash = System.getProperty("file.separator");
    private String line = System.getProperty("line.separator");

    /**
     * Временная директория для тестов.
     */
    private File root = new File(System.getProperty("java.io.tmpdir"));

    /**
     * Временный файл для тестового лога событий сервера.
     */
    private File inFile = new File(root.getAbsolutePath() + slash + "unavailable.csv");

    /**
     * Временный файл для записи результатов тестового анализа.
     */
    private File outFile = new File(root.getAbsolutePath() + slash + "target.csv");


    /**
     * В этом тесте проверяется случай, когда лог событий содержит один период недоступности сервера.
     *
     * @throws FileNotFoundException если файл лога не найден.
     * @throws IOException           если чтение файла лога или запись в файл результата привела к ошибке.
     */
    @Test
    public void whenLogHaveOnePeriodThenAnalyzeSuccess() throws FileNotFoundException, IOException {
        PrintWriter out = new PrintWriter(new FileOutputStream(inFile));
        out.println("200;15:00:30");
        out.println("300;15:01:32");
        out.println("400;15:08:19");
        out.println("500;16:28:03");
        out.println("400;17:49:25");
        out.println("400;18:09:32");
        out.println("500;18:19:51");
        out.println("500;18:22:33");
        out.println("300;18:56:44");
        out.println("300;18:59:59");
        out.println("200;19:34:07");
        out.flush();
        Analyze analyze = new Analyze();
        analyze.unavailable(inFile.getAbsolutePath(), outFile.getAbsolutePath());
        String result = "15:08:19;18:56:44;" + line;
        StringBuilder actual = new StringBuilder();
        InputStreamReader reader = new InputStreamReader(new FileInputStream(outFile));
        while (reader.ready()) {
            actual.append((char) reader.read());
        }
        assertThat(actual.toString(), is(result));
    }

    /**
     * В этом тесте проверяется случай, когда лог событий содержит несколько периодов недоступности сервера.
     *
     * @throws FileNotFoundException если файл лога не найден.
     * @throws IOException           если чтение файла лога или запись в файл результата привела к ошибке.
     */
    @Test
    public void whenLogHaveTwoPeriodsThenAnalyzeSuccess() throws FileNotFoundException, IOException {
        PrintWriter out = new PrintWriter(new FileOutputStream(inFile));
        out.println("200;15:00:30");
        out.println("300;15:01:32");
        out.println("200;15:08:19");
        out.println("400;16:28:03");
        out.println("200;17:49:25");
        out.println("400;18:09:32");
        out.println("500;18:19:51");
        out.println("500;18:22:33");
        out.println("300;18:56:44");
        out.println("300;18:59:59");
        out.println("200;19:34:07");
        out.flush();
        Analyze analyze = new Analyze();
        analyze.unavailable(inFile.getAbsolutePath(), outFile.getAbsolutePath());
        String result = "16:28:03;17:49:25;" + line + "18:09:32;18:56:44;" + line;
        StringBuilder actual = new StringBuilder();
        InputStreamReader reader = new InputStreamReader(new FileInputStream(outFile));
        while (reader.ready()) {
            actual.append((char) reader.read());
        }
        assertThat(actual.toString(), is(result));
    }

    /**
     * В этом тесте проверяется случай, когда лог событий не содержит периодов недоступности сервера.
     *
     * @throws FileNotFoundException если файл лога не найден.
     * @throws IOException           если чтение файла лога или запись в файл результата привела к ошибке.
     */
    @Test
    public void whenLogHaveNoPeriodsThenAnalyzeSuccess() throws FileNotFoundException, IOException {
        PrintWriter out = new PrintWriter(new FileOutputStream(inFile));
        out.println("200;15:00:30");
        out.println("300;15:01:32");
        out.println("200;15:08:19");
        out.println("200;16:28:03");
        out.println("200;17:49:25");
        out.println("3400;18:09:32");
        out.println("300;18:19:51");
        out.println("300;18:22:33");
        out.println("300;18:56:44");
        out.println("300;18:59:59");
        out.println("200;19:34:07");
        out.flush();
        Analyze analyze = new Analyze();
        analyze.unavailable(inFile.getAbsolutePath(), outFile.getAbsolutePath());
        String result = "";
        StringBuilder actual = new StringBuilder();
        InputStreamReader reader = new InputStreamReader(new FileInputStream(outFile));
        while (reader.ready()) {
            actual.append((char) reader.read());
        }
        assertThat(actual.toString(), is(result));
    }
}