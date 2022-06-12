package com.pan.community.dao;

import org.springframework.stereotype.Repository;

@Repository//加此注解才能被添加到容器
public interface AlphaDao {
    String select();
}
