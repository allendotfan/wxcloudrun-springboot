package com.tencent.wxcloudrun.config;

import lombok.Data;

import java.util.List;

@Data
public class PageResponse<T> {

  private List<T> list;

  private Integer total;

  private Integer page;

  private Integer pageSize;

  public PageResponse(List<T> list, Integer total, Integer page, Integer pageSize) {
    this.list = list;
    this.total = total;
    this.page = page;
    this.pageSize = pageSize;
  }
}
