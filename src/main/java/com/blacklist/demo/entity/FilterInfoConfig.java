package com.blacklist.demo.entity;

import lombok.Data;

@Data
public class FilterInfoConfig {
    private Long id;

    private Long projectId;

    private String projectDesc;

    private Integer effectStatus;

    private Long effectTime;

    private Long expiredTime;

    private Long createTime;

    private Long updateTime;
}