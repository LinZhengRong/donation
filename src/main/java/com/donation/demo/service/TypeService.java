package com.donation.demo.service;

import com.donation.demo.model.Type;

import java.util.List;

public interface TypeService {
    List<Type> list(Type type,int start,int pageSize);
    int getCount(Type type);
    boolean updateOne(Type type);
    boolean insertOne(Type type);
    Type get(int id);
    boolean del(int id);
}
