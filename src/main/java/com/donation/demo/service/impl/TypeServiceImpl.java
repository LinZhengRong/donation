package com.donation.demo.service.impl;

import com.donation.demo.dao.TypeMapper;
import com.donation.demo.model.Type;
import com.donation.demo.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeMapper typeMapper;
    @Override
    public List<Type> list(Type type,int start,int pageSize) {
        return typeMapper.list(type,start,pageSize);
    }

    @Override
    public int getCount(Type type) {
        return typeMapper.getCount(type);
    }

    @Override
    public boolean updateOne(Type type) {
        return typeMapper.updateOne(type);
    }

    @Override
    public boolean insertOne(Type type) {
        return typeMapper.insertOne(type);
    }

    @Override
    public Type get(int id) {
        return typeMapper.get(id);
    }

    @Override
    public boolean del(int id) {
        return typeMapper.del(id);
    }
}
