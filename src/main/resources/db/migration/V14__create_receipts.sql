DROP TABLE IF EXISTS `bb_receipts`;
CREATE TABLE bb_receipts (
  id         bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  no         varchar(32)         NOT NULL,
  order_no   varchar(64)         NOT NULL,
  seller     varchar(32)         NOT NULL,
  buyer      varchar(32)         NOT NULL,
  price      decimal(12, 2)      NOT NULL,
  status     tinyint(1)                   DEFAULT 0,
  created_at timestamp                    DEFAULT CURRENT_TIMESTAMP,
  updated_at timestamp                    DEFAULT CURRENT_TIMESTAMP
  ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;