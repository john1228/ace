DROP TABLE IF EXISTS `bb_member_coupons`;
CREATE TABLE bb_member_coupons (
  id              bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  account_id      varchar(32)         NOT NULL,
  coupon_id       bigint(11) unsigned NOT NULL,
  name            varchar(64)                  DEFAULT NULL,
  type            tinyint(1)                   DEFAULT NULL,
  min             float                        DEFAULT NULL,
  discount        float                        DEFAULT NULL,
  resume          text,
  start_date      date                         DEFAULT NULL,
  end_date        date                         DEFAULT NULL,
  limit_pro    text,
  limit_org    text,
  limit_room   text,
  status          tinyint(1)                   DEFAULT 0,
  order_no        varchar(32)                  DEFAULT NULL,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;