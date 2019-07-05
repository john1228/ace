DROP TABLE IF EXISTS `bb_invoices`;
CREATE TABLE bb_invoices (
  id           bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  order_id     bigint(11)          NOT NULL COMMENT '关联订单',
  type         tinyint(1)          NOT NULL COMMENT '发票类型',
  content      text                NOT NULL COMMENT '发票内容',
  method       tinyint(1)          NOT NULL COMMENT '寄送方式',
  address      text                NOT NULL COMMENT '寄送地址',
  remark       text                COMMENT '备注',
  status       tinyint(1)          NOT NULL DEFAULT 0 COMMENT '发票状态',
  express      text                COMMENT '快递信息',
  created_at   timestamp           DEFAULT CURRENT_TIMESTAMP,
  updated_at   timestamp           DEFAULT CURRENT_TIMESTAMP
  ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
CREATE INDEX bb_invoices on bb_invoices(order_id);