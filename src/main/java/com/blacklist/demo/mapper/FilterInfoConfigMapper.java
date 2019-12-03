package com.blacklist.demo.mapper;

import com.blacklist.demo.entity.FilterInfoConfig;

public interface FilterInfoConfigMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FilterInfoConfig record);

    int insertSelective(FilterInfoConfig record);

    FilterInfoConfig selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FilterInfoConfig record);

    int updateByPrimaryKey(FilterInfoConfig record);
}