package com.donation.demo.dao;

import com.donation.demo.model.Thank;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ThankMapper {
    boolean add(Thank thank);
}
