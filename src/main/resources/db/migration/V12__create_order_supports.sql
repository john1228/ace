DROP TABLE IF EXISTS `bb_order_supports`;
CREATE TABLE bb_order_supports (
  id             bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  order_id       bigint(11) unsigned NOT NULL comment '订单编号',
  support_id     bigint(11) unsigned NOT NULL comment '服务编号',
  support_name   varchar(32)         NOT NULL comment '服务名',
  support_cover  varchar(128)        NOT NULL comment '服务封面',
  support_unit   varchar(32)         NOT NULL comment '服务单位',
  support_price  varchar(32)         NOT NULL comment '服务价格',
  support_amount varchar(11)         NOT NULL comment '服务数量',
  created_at     timestamp           DEFAULT CURRENT_TIMESTAMP,
  updated_at     timestamp           DEFAULT CURRENT_TIMESTAMP
  ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
CREATE INDEX bb_order_supports_order_id on bb_order_supports(order_id);
CREATE INDEX bb_order_supports_support_id on bb_order_supports(support_id)