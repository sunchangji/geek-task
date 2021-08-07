package com.suncj.geektask.week07.service;

import com.suncj.geektask.week07.annotation.DynamicSwitchDataSource;
import com.suncj.geektask.week07.constant.Week07Constant;
import com.suncj.geektask.week07.entity.TradeOrderDetailEntity;
import com.suncj.geektask.week07.mapper.TradeOrderDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TradeOrderDetailService {

    @Autowired
    private TradeOrderDetailMapper tradeOrderDetailMapper;

    @DynamicSwitchDataSource(dataSource = Week07Constant.SLAVE)
    public List<TradeOrderDetailEntity> findList() {
        return tradeOrderDetailMapper.findList();
    }

    @DynamicSwitchDataSource(dataSource = Week07Constant.MASTER)
    public void create(TradeOrderDetailEntity entity){
        tradeOrderDetailMapper.create(entity);
    }

    @DynamicSwitchDataSource(dataSource = Week07Constant.MASTER)
    public void batchCreate(List<TradeOrderDetailEntity> list){
        tradeOrderDetailMapper.batchCreate(list);
    }

    @DynamicSwitchDataSource(dataSource = Week07Constant.MASTER)
    public int update(String productName,Long id){
        return tradeOrderDetailMapper.update(productName,id);
    }
}
