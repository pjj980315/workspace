package com.pan.community.controller;

import com.pan.community.service.AlphaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@Controller
@RequestMapping("/alpha")
public class AlphaController {
    @Autowired
    private AlphaService alphaService;

    @RequestMapping("/hello")
    @ResponseBody
    public String sayHello(){
        return "Hello SpringBoot!";
    }

    @RequestMapping("/data")
    @ResponseBody
    public String getData(){
        return alphaService.find();
    }

    @RequestMapping("/http")
    public void http(HttpServletRequest request, HttpServletResponse response){
        System.out.println(request.getMethod());//请求方式
        System.out.println(request.getServletPath());//请求路径 返回/alpha/http
        Enumeration<String> enumeration = request.getHeaderNames();
        //请求行
        while(enumeration.hasMoreElements()){
            String name = enumeration.nextElement();
            String value = request.getHeader(name);
            System.out.println(name+"  :  "+value);
        }
        System.out.println(request.getParameter("code"));
        response.setContentType("text/html;charset=utf-8");

        try(PrintWriter writer = response.getWriter();){
            writer.write("<h1>陈修洁最牛！！！</h1>");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    @RequestMapping(path = "/students",method = RequestMethod.GET)
    @ResponseBody
    public String getStudents(
            @RequestParam(name="current",required=false,defaultValue = "1") int current){
        System.out.println(current);
        return "get some students";
    }
    @RequestMapping(path="/students/{id}",method = RequestMethod.GET)
    @ResponseBody
    public String getstudent(
            @PathVariable("id") int id){
        System.out.println(id);
        return "get a student";
    }
    @RequestMapping(path="student",method = RequestMethod.POST)//获取路径student的名字和年龄
    @ResponseBody
    public String savaStudent(String name,int age){
        System.out.println(name+" : "+age);//怎么去获取post请求当中的数据呢？
                                          //只需要声明参数 和表单中的参数名字一致
        return "success";
    }
    @RequestMapping(path = "/teacher",method = RequestMethod.GET)
    public ModelAndView getTeacher(){
        ModelAndView mav = new ModelAndView();
        mav.addObject("name","陈娘子");
        //addObject = getModelMap().addAttribute(attributeName, attributeValue);
        mav.addObject("age",18);
        mav.setViewName("/demo/view");
        return mav;
    }
    @RequestMapping(path = "/school",method = RequestMethod.GET)
    public String getSchool(Model model){
        model.addAttribute("name","北京工业大学");
        model.addAttribute("age",61);
        return "/demo/view";
    }
    @RequestMapping(path = "emp",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getEmp(){
        Map<String,Object> map = new HashMap<>();
        map.put("name","张三");
        map.put("salary","8000");
        return map;
    }
    @RequestMapping(path = "emps",method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String,Object>> getEmps(){
        List<Map<String,Object>> list = new ArrayList<>();

        Map<String,Object> map = new HashMap<>();
        map.put("name","张4");
        map.put("salary","9000");
        list.add(map);

        map = new HashMap<>();
        map.put("name","张5");
        map.put("salary","8000");
        list.add(map);

        map = new HashMap<>();
        map.put("name","张6");
        map.put("salary","7000");
        list.add(map);

        return list;
    }


}
