package com.tencent.wxcloudrun.service.impl;

import com.tencent.wxcloudrun.config.PageResponse;
import com.tencent.wxcloudrun.dao.TweetsMapper;
import com.tencent.wxcloudrun.dto.TweetQueryRequest;
import com.tencent.wxcloudrun.dto.TweetRequest;
import com.tencent.wxcloudrun.model.Tweet;
import com.tencent.wxcloudrun.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TweetServiceImpl implements TweetService {

  final TweetsMapper tweetsMapper;

  public TweetServiceImpl(@Autowired TweetsMapper tweetsMapper) {
    this.tweetsMapper = tweetsMapper;
  }

  @Override
  public Tweet createTweet(TweetRequest request) {
    Tweet tweet = new Tweet();
    tweet.setContent(request.getContent());
    tweet.setAuthor(request.getAuthor());
    // 默认状态为已发布(2)，如果请求中有指定则使用请求的值
    tweet.setStatus(request.getStatus() != null ? request.getStatus() : 2);
    tweet.setLikeCount(0);
    tweet.setCommentCount(0);
    
    tweetsMapper.insertTweet(tweet);
    return tweet;
  }

  @Override
  public PageResponse<Tweet> queryTweets(TweetQueryRequest request) {
    // 参数校验和默认值设置
    Integer page = request.getPage() != null && request.getPage() > 0 ? request.getPage() : 1;
    Integer pageSize = request.getPageSize() != null && request.getPageSize() > 0 ? request.getPageSize() : 10;
    
    // 计算偏移量
    Integer offset = (page - 1) * pageSize;
    
    // 查询列表
    List<Tweet> tweets = tweetsMapper.queryTweets(
        request.getAuthor(),
        request.getStatus(),
        offset,
        pageSize
    );
    
    // 查询总数
    Integer total = tweetsMapper.countTweets(
        request.getAuthor(),
        request.getStatus()
    );
    
    return new PageResponse<>(tweets, total, page, pageSize);
  }
}
