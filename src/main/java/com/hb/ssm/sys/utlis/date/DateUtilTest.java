package com.hb.ssm.sys.utlis.date;

import java.util.Calendar;
import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @description: 测试时间工具类
 * @author: huangbo
 * @create: 2019-08-03 14:07
 **/

public class DateUtilTest {
    @Test
    public void testAddDateByWorkDay()
    {
        Calendar src = Calendar.getInstance();
        src.set(2010, Calendar.APRIL , 29);
        Calendar result = DateUtil.addDateByWorkDay(src, 2);
        Calendar expected = Calendar.getInstance();
        expected.set(2010, Calendar.MAY , 4);
        Assert.assertEquals(expected.getTime().toString(), result.getTime().toString());
    }

    @Test
    public void testAddDateByWorkDay2()
    {
        //测试母亲节
        Calendar src = Calendar.getInstance();
        src.set(2010, Calendar.MAY , 7);
        Calendar result = DateUtil.addDateByWorkDay(src, 2);
        Calendar expected = Calendar.getInstance();
        expected.set(2010, Calendar.MAY , 11);
        Assert.assertEquals(expected.getTime().toString(), result.getTime().toString());
    }
}