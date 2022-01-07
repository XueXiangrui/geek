package domain;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by xiangrui.xue on 2021/11/9.
 */
@Data
public class Orders implements Serializable{

    private Long orderId;

    private String notes;

    private BigDecimal totalPrice;

    private Integer status;

    private Date paymentTime;

    private Date shipTime;

    private Date dealTime;

    private Long createUserId;

    private Date createTime;

    private Long modifyUserId;

    private Date modifyTime;

}
