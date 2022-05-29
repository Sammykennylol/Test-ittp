import org.junit.Assert;

import org.junit.jupiter.api.RepeatedTest;
/**
 * MainTest - класс-тест,
 * который используется для проверки, что два потока Cher и Sonny не нарушают порядка в процессе гонки
 * для того чтобы убедиться, что тесты проходят всегда и не появляется случайных ошибок из-за гонки потоков - тест выполняется 500 раз
 * @author Semyon
 */
public class SongTest {


    @RepeatedTest(500)
    public void shouldBeTrue() throws InterruptedException {

        Assert.assertEquals(true,Main.doMain());
    }
}
