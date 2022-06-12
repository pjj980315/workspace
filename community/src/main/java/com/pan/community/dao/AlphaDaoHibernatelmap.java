package com.pan.community.dao;

import org.springframework.stereotype.Repository;

@Repository("AlphaHiber")//必加这个 否则spring扫描不到
public class AlphaDaoHibernatelmap implements AlphaDao{
    @Override
    public String select() {
        return "hirenate";
    }
}
