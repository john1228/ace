DROP TABLE IF EXISTS `bb_invoices`;
CREATE TABLE bb_invoices (
  id           bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  order_no     varchar(32)         NOT NULL,
  type         tinyint(1)          NOT NULL,
  content      tinyint(1)          NOT NULL,

  created_at   timestamp           DEFAULT CURRENT_TIMESTAMP,
  updated_at   timestamp           DEFAULT CURRENT_TIMESTAMP
  ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;