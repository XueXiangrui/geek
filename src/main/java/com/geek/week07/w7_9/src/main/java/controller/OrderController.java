package controller;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import domain.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import web.OrdersService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by xiangrui.xue on 2021/11/9.
 */
@RestController
public class OrderController {

    @Autowired
    private OrdersService ordersService;

    Snowflake snowflake = IdUtil.createSnowflake(1,1);

    @PostMapping("/insert")
    public void batchInsert(){
        List<Orders> ordersList = new ArrayList();
        Orders orders = new Orders();
        orders.setOrderId(snowflake.nextId());
        orders.setNotes("这是一个订单");
        orders.setTotalPrice(new BigDecimal("100"));
        orders.setStatus(0);
        orders.setPaymentTime(new Date());
        orders.setShipTime(new Date());
        orders.setDealTime(new Date());
        orders.setCreateUserId(1L);
        orders.setCreateTime(new Date());
        orders.setModifyUserId(1L);
        orders.setModifyTime(new Date());
        ordersList.add(orders);
        ordersService.batchInsert(ordersList);
    }

    @GetMapping("/query")
    public void query(){
        ordersService.query();
    }
}
