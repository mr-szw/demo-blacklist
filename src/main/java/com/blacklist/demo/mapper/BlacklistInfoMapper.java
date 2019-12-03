package com.blacklist.demo.mapper;

import com.blacklist.demo.entity.BlacklistInfo;

public interface BlacklistInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BlacklistInfo record);

    int insertSelective(BlacklistInfo record);

    BlacklistInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BlacklistInfo record);

    int updateByPrimaryKey(BlacklistInfo record);
}