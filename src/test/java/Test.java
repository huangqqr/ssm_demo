/**
 * @公司 *松的个人工作室
 * @用户: Administrator
 * @类命: Test
 * @创建时间: 2018/11/17/017/ 10:29
 * @创建人:
 * @描叙:
 */

import com.hb.ssm.user.mapper.UserMapper;
import com.hb.ssm.user.model.User;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * Created by Administrator on 2018/11/17/017.
 */
// 加载spring配置文件
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-mybatis.xml"})
public class Test {

    @Autowired
    private UserMapper userMapper;

    @org.junit.Test
    public void selectId(){
        User user = userMapper.selectByPrimaryKey(1);
        System.out.println(user.toString());
    }
}