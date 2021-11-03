package com.donation.demo.dao;

import com.donation.demo.model.Type;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TypeMapper {
    List<Type> list(Type type,int start,int pageSize);
    int getCount(Type type);
    boolean insertOne(Type type);
    boolean updateOne(Type type);
    Type get(int id);
    boolean del(int id);
}
