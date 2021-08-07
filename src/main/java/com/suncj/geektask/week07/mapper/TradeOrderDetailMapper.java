package com.suncj.geektask.week07.mapper;

import com.suncj.geektask.week07.entity.TradeOrderDetailEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TradeOrderDetailMapper {

    List<TradeOrderDetailEntity> findList();

    void create(TradeOrderDetailEntity entity);

    int update(@Param("productName") String productName, @Param("id")Long id);

    void batchCreate(List<TradeOrderDetailEntity> list);
}
