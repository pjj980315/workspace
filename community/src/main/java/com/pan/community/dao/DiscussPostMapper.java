package com.pan.community.dao;

import com.pan.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DiscussPostMapper {
    List<DiscussPost> selectDiscussPosts(int userId, int offset, int limit);
    //根据userId查询 发送的帖子内容，offset表示起始页，limit代表一页显示多少条

    int selectDiscussPostRows(@Param("userId") int userId);
    //查询userId 发送帖子的条数
    // Param 给 userId取一个别名
    //如果这个方法 只有一个参数并且在<if>里使用，必须加别名
}
