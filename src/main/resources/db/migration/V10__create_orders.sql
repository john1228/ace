DROP TABLE IF EXISTS `bb_orders`;
CREATE TABLE bb_orders (
  id           bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  account_id   varchar(32)         NOT NULL COMMENT '购买账户编号',
  account_name varchar(32)         COMMENT '购买账户名',
  total        decimal(12,2)       DEFAULT 0 COMMENT '订单总额',
  coupon       decimal(12,2)       DEFAULT 0 COMMENT '优惠金额',
  pay_amount   decimal(12,2)       DEFAULT 0 COMMENT '实付款',
  pay_type     varchar(32)         COMMENT '支付类型',
  status       tinyint(1)          DEFAULT 0 COMMENT '订单状态',
  remark       text                COMMENT '订单备注',
  created_at   timestamp           DEFAULT CURRENT_TIMESTAMP,
  updated_at   timestamp           DEFAULT CURRENT_TIMESTAMP
  ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
CREATE INDEX bb_orders_account_id on bb_orders(account_id);