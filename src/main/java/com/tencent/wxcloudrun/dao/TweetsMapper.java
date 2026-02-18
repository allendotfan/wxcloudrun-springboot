package com.tencent.wxcloudrun.dao;

import com.tencent.wxcloudrun.model.Tweet;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TweetsMapper {

  void insertTweet(Tweet tweet);

  List<Tweet> queryTweets(@Param("author") String author,
                          @Param("status") Integer status,
                          @Param("offset") Integer offset,
                          @Param("pageSize") Integer pageSize);

  Integer countTweets(@Param("author") String author,
                     @Param("status") Integer status);
}
