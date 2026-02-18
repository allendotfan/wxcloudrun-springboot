package com.tencent.wxcloudrun.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class Tweet implements Serializable {

  private Integer id;

  private String content;

  private String author;

  private Integer status;

  private Integer likeCount;

  private Integer commentCount;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;
}
