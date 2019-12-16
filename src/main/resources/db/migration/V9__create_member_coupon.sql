DROP TABLE IF EXISTS `bb_member_coupons`;
CREATE TABLE bb_member_coupons (
  id         BIGINT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  org_id     VARCHAR(32)         NOT NULL
  COMMENT '所属组织',
  emp_id     VARCHAR(32)         NOT NULL
  COMMENT '所属雇员',
  coupon_id  BIGINT(11) UNSIGNED NOT NULL
  COMMENT '关联发放的券',
  name       VARCHAR(64)                  DEFAULT NULL
  COMMENT '名字',
  type       TINYINT(1)                   DEFAULT NULL
  COMMENT '类型',
  min        DECIMAL                      DEFAULT NULL
  COMMENT '订单最小金额',
  discount   DECIMAL                      DEFAULT NULL
  COMMENT '抵用金额',
  resume     TEXT COMMENT '说明',
  start_date DATE                         DEFAULT NULL
  COMMENT '开始使用日期',
  end_date   DATE                         DEFAULT NULL
  COMMENT '使用结束日期',
  limit_wday VARCHAR(120) COMMENT '指定星期',
  limit_room VARCHAR(120) COMMENT '指定房间',
  status     TINYINT(1)                   DEFAULT 0
  COMMENT '优惠券状态',
  order_no   VARCHAR(32)                  DEFAULT NULL
  COMMENT '使用此优惠券的关联的订单号',
  created_at TIMESTAMP                    DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP                    DEFAULT CURRENT_TIMESTAMP
  ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
CREATE INDEX bb_member_coupons_org_id ON bb_member_coupons (org_id);
CREATE INDEX bb_member_coupons_emp_id ON bb_member_coupons (emp_id);
CREATE INDEX bb_member_coupons_coupon_id ON bb_member_coupons (coupon_id)