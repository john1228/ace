DROP TABLE IF EXISTS `bb_orders`;
CREATE TABLE bb_orders (
  id           bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  account_id   varchar(32)         NOT NULL,
  account_name varchar(32),
  order_no     varchar(64)                  DEFAULT NULL,
  total        tinyint(1)                   DEFAULT NULL,
  pay_amount   float                        DEFAULT NULL,
  coupon       float                        DEFAULT NULL,
  status       tinyint(1)                   DEFAULT 0,
  created_at   timestamp                    DEFAULT CURRENT_TIMESTAMP,
  updated_at   timestamp                    DEFAULT CURRENT_TIMESTAMP
  ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;