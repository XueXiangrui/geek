package mapper;

import domain.Orders;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by xiangrui.xue on 2021/11/9.
 */
@Repository
public interface OrdersMapper {
    void batchInsert(List<Orders> ordersList);

    List<Orders> query();
}

