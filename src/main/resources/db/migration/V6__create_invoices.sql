DROP TABLE IF EXISTS `bb_invoices`;
CREATE TABLE bb_invoices (
  id           bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  order_no     varchar(32)         NOT NULL,
  type         tinyint(1)          NOT NULL,
  content      text                NOT NULL COMMENT '发票内容',

  name         varchar(32)         NOT NULL COMMENT '联系人',
  mobile       varchar(32)         NOT NULL COMMENT '联系电话',
  email        varchar(64)         NOT NULL COMMENT '联系邮箱',
  address      varchar(256)        NOT NULL COMMENT '联系地址',
  remark       text                COMMENT '备注',
  status       tinyint(1)          NOT NULL DEFAULT 0 COMMENT '发票状态',
  created_at   timestamp           DEFAULT CURRENT_TIMESTAMP,
  updated_at   timestamp           DEFAULT CURRENT_TIMESTAMP
  ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;