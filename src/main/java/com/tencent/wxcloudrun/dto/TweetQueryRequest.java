package com.tencent.wxcloudrun.dto;

import lombok.Data;

@Data
public class TweetQueryRequest {

  private Integer page = 1;

  private Integer pageSize = 10;

  private String author;

  private Integer status;
}
