package com.suncj.geektask.week07.controller;

import com.suncj.geektask.week07.entity.TradeOrderDetailEntity;
import com.suncj.geektask.week07.service.TradeOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("dynamic/db")
public class DynamicSwitchDataSourceTestController {
    @Autowired
    private TradeOrderDetailService tradeOrderDetailService;

    @RequestMapping("query")
    public List<TradeOrderDetailEntity> queryDb(){
        return tradeOrderDetailService.findList();
    }

    @RequestMapping("create")
    public boolean create(){
        TradeOrderDetailEntity entity = buildEntity(0);
        tradeOrderDetailService.create(entity);
        return true;
    }

    @RequestMapping("update")
    public int create(String productName,long id){
        return tradeOrderDetailService.update(productName,id);
    }

    /**
     * 1w一次执行会爆Packet for query is too large (5544651 > 4194304),sql过长
     * @return
     */
    @RequestMapping("batch100w")
    public boolean create100wData(){

        List<TradeOrderDetailEntity> list = new ArrayList<>(10000);
        for (int i=0;i<1000000;i++){
            list.add(buildEntity(i));
            //1w插入一次
            if(list.size() > 10000){
                tradeOrderDetailService.batchCreate(list);
                list = new ArrayList<>(10000);
            }
        }
        return true;
    }

    private TradeOrderDetailEntity buildEntity(int i){
        TradeOrderDetailEntity entity = new TradeOrderDetailEntity();
        entity.setAverageCost(BigDecimal.valueOf(1.02));
        entity.setBuyNum(10);
        entity.setFeeMoney(BigDecimal.valueOf(0.02));
        entity.setOrderId(UUID.randomUUID().toString().replaceAll("-",""));
        entity.setProductId(12100L);
        entity.setProductPrice(BigDecimal.valueOf(6.02));
        entity.setProductName("测试商品");
        entity.setWarehouseId(100L);
        Date nowTime = new Date();
        entity.setCreateTime(nowTime);
        entity.setUpdateTime(nowTime);
        return entity;
    }
}
