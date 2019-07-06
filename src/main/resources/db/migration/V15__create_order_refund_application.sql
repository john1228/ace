DROP TABLE IF EXISTS `bb_order_refund_applications`;
CREATE TABLE bb_order_refund_applications (
  id             bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  account_id     varchar(32)         comment '退款账户编号',
  account_name   varchar(32)         comment '退款账户名字',
  order_id       bigint(11) unsigned NOT NULL comment '订单编号',
  amount         decimal(10,2)       NOT NULL comment '订单金额',
  confirm_amount decimal(10,2)       NOT NULL comment '确认退款金额',
  status         tinyint(1)          DEFAULT 0 comment '申请处理状态',
  created_at     timestamp           DEFAULT CURRENT_TIMESTAMP,
  updated_at     timestamp           DEFAULT CURRENT_TIMESTAMP
  ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
CREATE INDEX bb_order_refund_applications_order_id on bb_order_refund_applications(order_id);
CREATE INDEX bb_order_refund_applications_account_id on bb_order_refund_applications(account_id)