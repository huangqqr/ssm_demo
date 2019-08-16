import org.junit.Test;

/**
 * @description: 测试字符串
 * @author: huangbo
 * @create: 2019-07-31 10:38
 **/

public class StringTest {

    @Test
    public void test() {
        String index = "C11&nbsp;SOR中共有7个一级总成，5530610-CA01-副仪表板安装支架-前与5610810-CA01-顶棚拉手安装支架总成能与C01共用，其余都不能共用，需对C01进行报价，详见以下列表及附件数据&nbsp;\n" +
                "：&nbsp;\n" +
                "<br>1、C01的5540100-CB01（其中5540151-CA01、5540170-CA01、5540340-CA01、5540350-CA01、5540451-CA01、5540461-CA01与C11共用）&nbsp;&nbsp;\n" +
                "<br>\n" +
                "2、5540100-CB02（其中5540151-CA01、5540170-CA01、5540340-CA01、5540350-CA01、5540451-CA01、5540461-CA01&nbsp;\n" +
                "<br>\n" +
                "3、5540211-CB01&nbsp;\n" +
                "&nbsp;\n" +
                "<br>4、5540212-CB01&nbsp;\n" +
                "&nbsp;\n" +
                "<br>5、5530620-CB01";
        int k = index.length();
        System.out.println(k);
    }
}