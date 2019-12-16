DROP TABLE IF EXISTS `bb_coupons`;
CREATE TABLE bb_coupons (
  id           BIGINT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  project_id   VARCHAR(32)         NOT NULL
  COMMENT '所属项目',
  org_id       VARCHAR(32)         NOT NULL
  COMMENT '所属机构',
  name         VARCHAR(64)         NOT NULL
  COMMENT '名字',
  type         TINYINT(1)          NOT NULL
  COMMENT '类型',
  discount     DECIMAL             NOT NULL
  COMMENT '优惠金额',
  min          DECIMAL             NOT NULL
  COMMENT '满足订单金额',
  amount       INTEGER             NOT NULL
  COMMENT '数量',
  resume       TEXT COMMENT '说明',
  expired_type TINYINT(4)          NOT NULL
  COMMENT '有效期类型 0-指定日期 1-发放指定',
  start_date   DATE COMMENT '指定日期的开始日期',
  end_date     DATE COMMENT '指定日期的结束日期',
  duration     BIGINT(11) COMMENT '发放指定的有效天数',
  limit_wday   VARCHAR(120) COMMENT '指定星期',
  limit_room   VARCHAR(240) COMMENT '指定房间',
  created_at   TIMESTAMP                    DEFAULT CURRENT_TIMESTAMP,
  updated_at   TIMESTAMP                    DEFAULT CURRENT_TIMESTAMP
  ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
CREATE INDEX bb_coupons_project_id
  on bb_coupons (project_id)