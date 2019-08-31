import org.junit.Test;

/**
 * @description:
 * @author: huangbo
 * @create: 2019-08-28 10:00
 **/

public class LongTest {

    @Test
    public void longEq() {
        Long id = null;
        Long ids = 3L;
        Long idss = 3L;
        if (idss.longValue()==ids.longValue()){
            System.out.println("不想打");
        }
    }
}