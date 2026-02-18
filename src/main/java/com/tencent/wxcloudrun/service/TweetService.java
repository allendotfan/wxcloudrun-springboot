package com.tencent.wxcloudrun.service;

import com.tencent.wxcloudrun.config.PageResponse;
import com.tencent.wxcloudrun.dto.TweetQueryRequest;
import com.tencent.wxcloudrun.dto.TweetRequest;
import com.tencent.wxcloudrun.model.Tweet;

public interface TweetService {

  Tweet createTweet(TweetRequest request);

  PageResponse<Tweet> queryTweets(TweetQueryRequest request);
}
