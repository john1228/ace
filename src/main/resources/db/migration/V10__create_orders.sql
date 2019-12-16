DROP TABLE IF EXISTS `bb_orders`;
CREATE TABLE bb_orders (
  id           BIGINT(11) unsigned NOT NULL AUTO_INCREMENT,
  account_id   VARCHAR(32)         NOT NULL
  COMMENT '订单所属账户',
  account_name VARCHAR(32) COMMENT '订单所属账户名',
  total        DECIMAL(12, 2)               DEFAULT 0
  COMMENT '订单总额',
  coupon       DECIMAL(12, 2)               DEFAULT 0
  COMMENT '优惠金额',
  pay_amount   DECIMAL(12, 2)               DEFAULT 0
  COMMENT '实付款',
  pay_type     VARCHAR(32) COMMENT '支付类型',
  status       TINYINT(1)                   DEFAULT 0
  COMMENT '订单状态',
  remark       TEXT COMMENT '订单备注',
  created_at   TIMESTAMP                    DEFAULT CURRENT_TIMESTAMP,
  updated_at   TIMESTAMP                    DEFAULT CURRENT_TIMESTAMP
  ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
CREATE INDEX bb_orders_account_id ON bb_orders (account_id);