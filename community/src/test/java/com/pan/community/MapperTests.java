package com.pan.community;

import com.pan.community.dao.DiscussPostMapper;
import com.pan.community.dao.UserMapper;
import com.pan.community.entity.DiscussPost;
import com.pan.community.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)//删除spring-boot-starter-test 中的 <scope>test</scope>-->
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)//以它为配置类
public class MapperTests {
    @Autowired
    private UserMapper userMapper;
    @Test
    public void testSelectUser(){
        User user = userMapper.selectByName("test");
        System.out.println(user);
    }
    @Test
    public void testInsertUser(){
        User user = new User();
        user.setUsername("test");
        user.setPassword("123456");
        user.setSalt("abc");
        user.setEmail("test@qq.com");
        user.setHeaderUrl("http://www.newdocer.com/101.png");
        user.setCreateTime(new Date());

        int rows = userMapper.insertUser(user);
        System.out.println("插入了"+ rows +"行数据");
    }
    @Test
    public void updateTest(){
        int rows = userMapper.updateStatus(150,100);
        System.out.println(rows);

        rows = userMapper.updateHeader(150,"www.gaile.com");
        System.out.println(rows);

        rows = userMapper.updatePassword(150,"234567");
        System.out.println(rows);
    }

    @Autowired
    private DiscussPostMapper discussPostMapper;
    @Test
    public void selectDiscussPostsTest(){
        List<DiscussPost> list = discussPostMapper.selectDiscussPosts(0, 0, 10);
        for(DiscussPost post:list){
            System.out.println(post);
        }
        int rows = discussPostMapper.selectDiscussPostRows(0);
        System.out.println(rows);
    }
}
