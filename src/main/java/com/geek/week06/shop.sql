create TABLE USER (
id            VARCHAR(20)  NOT NULL  COMMENT '用户id',
userName      VARCHAR(20)  NOT NULL  COMMENT '用户昵称',
mobile        VARCHAR(20)  NOT NULL  COMMENT '用户手机'
# ,pwd           VARCHAR(20)  NOT NULL  COMMENT '用户密码',
# createDATE    DATE                   COMMENT '创建日期',
# userLevel     VARCHAR(20)  NOT NULL  COMMENT '用户等级'
);

create TABLE goods (
id               VARCHAR(20)  NOT NULL  COMMENT '商品id',
shopDetail       VARCHAR(20)  NOT NULL  COMMENT '商品详情',
price            INT          NOT NULL  COMMENT '商品价格',
shopId           VARCHAR(20)  NOT NULL  COMMENT '商家',
createDATE       DATE         NOT NULL  COMMENT '创建日期',
creater          DATE         NOT NULL  COMMENT '创建人员',
oprice           INT          NOT NULL  COMMENT '商品原价',
stock            VARCHAR(20)  NOT NULL  COMMENT '库存',
soldNum          VARCHAR(20)  NOT NULL  COMMENT '卖出数量'
);

CREATE TABLE evaGood(
id  VARCHAR(20) NOT NULL  COMMENT '评论id',
goodId VARCHAR(20) NOT NULL  COMMENT '商品id',
evaDetail VARCHAR(20) NOT NULL  COMMENT '评论详情',
createDATE DATE NOT NULL  COMMENT '创建日期',
creater DATE NOT NULL  COMMENT '创建人员',
anwId  VARCHAR(20) NOT NULL  COMMENT '评论回复id'
);

create TABLE orders(
id   VARCHAR(20)  COMMENT '订单id',
userId  VARCHAR(20)   COMMENT  '购买用户ID',
goodId  VARCHAR(20)   COMMENT  '商品id',
createDate DATE   COMMENT '购买日期',
num  INT   COMMENT '购买数量',
price INT  COMMENT '商品购买价格',
payDetail  VARCHAR(20) NOT NULL  COMMENT '支付信息'
);
-- 不能用关键词命名