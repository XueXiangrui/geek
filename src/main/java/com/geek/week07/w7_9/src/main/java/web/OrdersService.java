package web;

import annotation.DataSource;
import domain.Orders;
import mapper.OrdersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xiangrui.xue on 2021/11/9.
 */
@Service
public class OrdersService {
    @Autowired
    private OrdersMapper ordersMapper;

    @DataSource(name = "write")
    public void batchInsert(List<Orders> ordersList) {
        ordersMapper.batchInsert(ordersList);
    }

    @DataSource(name = "read")
    public List<Orders> query() {
        return ordersMapper.query();
    }
}
