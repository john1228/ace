DROP TABLE IF EXISTS `bb_member_coupons`;
CREATE TABLE bb_member_coupons (
  id         bigint(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  org_id     varchar(32)         NOT NULL,
  emp_id     varchar(32)         NOT NULL,
  coupon_id  bigint(11) UNSIGNED NOT NULL,
  name       varchar(64)                  DEFAULT NULL,
  type       tinyint(1)                   DEFAULT NULL,
  min        float                        DEFAULT NULL,
  discount   float                        DEFAULT NULL,
  resume     text,
  start_date date                         DEFAULT NULL,
  end_date   date                         DEFAULT NULL,
  limit_wday varchar(120) COMMENT '指定星期',
  limit_room varchar(120) COMMENT '指定房间',
  status     tinyint(1)                   DEFAULT 0,
  order_no   varchar(32)                  DEFAULT NULL,
  created_at timestamp                    DEFAULT CURRENT_TIMESTAMP,
  updated_at timestamp                    DEFAULT CURRENT_TIMESTAMP
  ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
CREATE INDEX bb_member_coupons_org_id
  on bb_member_coupons (org_id);
CREATE INDEX bb_member_coupons_emp_id
  on bb_member_coupons (emp_id);
CREATE INDEX bb_member_coupons_coupon_id
  on bb_member_coupons (coupon_id)