package com.tencent.wxcloudrun.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.config.PageResponse;
import com.tencent.wxcloudrun.dto.TweetQueryRequest;
import com.tencent.wxcloudrun.dto.TweetRequest;
import com.tencent.wxcloudrun.model.Tweet;
import com.tencent.wxcloudrun.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 推文控制器
 */
@RestController
public class TweetController {

  final TweetService tweetService;
  final Logger logger;

  public TweetController(@Autowired TweetService tweetService) {
    this.tweetService = tweetService;
    this.logger = LoggerFactory.getLogger(TweetController.class);
  }

  /**
   * 发推文
   * @param request {@link TweetRequest}
   * @return API response json
   */
  @PostMapping(value = "/api/tweets")
  ApiResponse createTweet(@RequestBody TweetRequest request) {
    logger.info("/api/tweets post request, content: {}", request.getContent());
    
    if (request.getContent() == null || request.getContent().trim().isEmpty()) {
      return ApiResponse.error("推文内容不能为空");
    }
    
    Tweet tweet = tweetService.createTweet(request);
    return ApiResponse.ok(tweet);
  }

  /**
   * 查询推文（分页）
   * @param page 页码，从1开始
   * @param pageSize 每页数量
   * @param author 作者（可选）
   * @param status 状态（可选）：0-已删除，1-草稿，2-已发布
   * @return API response json
   */
  @GetMapping(value = "/api/tweets")
  ApiResponse queryTweets(@RequestParam(required = false) Integer page,
                          @RequestParam(required = false) Integer pageSize,
                          @RequestParam(required = false) String author,
                          @RequestParam(required = false) Integer status) {
    logger.info("/api/tweets get request, page: {}, pageSize: {}, author: {}, status: {}", 
                page, pageSize, author, status);
    
    TweetQueryRequest request = new TweetQueryRequest();
    request.setPage(page);
    request.setPageSize(pageSize);
    request.setAuthor(author);
    request.setStatus(status);
    
    PageResponse<Tweet> pageResponse = tweetService.queryTweets(request);
    return ApiResponse.ok(pageResponse);
  }
}
