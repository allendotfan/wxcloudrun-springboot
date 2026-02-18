package com.tencent.wxcloudrun.dto;

import lombok.Data;

@Data
public class TweetRequest {

  private String content;

  private String author;

  private Integer status;
}
