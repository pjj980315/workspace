package com.pan.community.controller;

import com.pan.community.entity.DiscussPost;
import com.pan.community.entity.Page;
import com.pan.community.entity.User;
import com.pan.community.service.DiscussPostService;
import com.pan.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {
    @Autowired
    private UserService userService;
    @Autowired
    private DiscussPostService discussPostService;

    @RequestMapping(path = "/index",method = RequestMethod.GET)
    public String getIndexPage(Model model, Page page){
        //方法调用前 SpringMVC会自动实例化Model和Page，并且将Page注入Model
        //所以在Thymeleaf中可以直接访问Page对象中的内容
        page.setRows(discussPostService.findDiscussPostRows(0));
        page.setPath("/index");
        //跳转到该页面

        List<DiscussPost> list = discussPostService.findDiscussPosts(0,page.getOffset(), page.getLimit());
        //根据userId查询 发送的帖子内容，offset表示起始页，limit代表一页显示多少条
        List<Map<String,Object>> discussPosts = new ArrayList<>();
        for(DiscussPost post : list){
            Map<String,Object> map = new HashMap<>();
            map.put("post",post);
            User user = userService.findUserById(post.getUserId());
            map.put("user",user);
            discussPosts.add(map);
        }
        model.addAttribute("discussPosts",discussPosts);
        return "/index";
    }
}
