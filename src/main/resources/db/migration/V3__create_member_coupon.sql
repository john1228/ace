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
  limit_pro       varchar(32),
  limit_pro_name  varchar(128),
  limit_org       varchar(32),
  limit_org_name  varchar(128),
  limit_room      varchar(32),
  limit_room_name varchar(128),
  order_no        varchar(32)                  DEFAULT NULL,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;