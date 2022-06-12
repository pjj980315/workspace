package com.pan.community.service;

import com.pan.community.dao.AlphaDao;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
//@Scope("prototype")
// 多例模式
public class AlphaService {
    @Autowired
    private AlphaDao alphaDao;

    public  AlphaService(){
        System.out.println("实例化AlphaService");
    }
//    @PostConstruct
    public void init(){
        System.out.println("初始化AlphaService");
    }
//    @PreDestroy
    public void destory(){
        System.out.println("销毁AlphaService");
    }
    public String find(){
        return alphaDao.select();
    }
}
