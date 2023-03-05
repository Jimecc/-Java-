package com.jim.mp;

import com.jim.mp.mapper.UserMapper;
import com.jim.mp.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author Jim
 * @Description
 * @createTime 2023年03月05日
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MyBatisTest {

    @Autowired
    UserMapper userMapper;

    @Test
    public void testSelect(){
        // 查询tb_user表
        List<User> list = userMapper.selectList(null);
        for (User user : list) {
            System.out.println(user.toString());
        }

    }
}
