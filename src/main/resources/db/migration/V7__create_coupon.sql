DROP TABLE IF EXISTS `bb_coupons`;
CREATE TABLE bb_coupons (
  id           bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  project_id   varchar(32)         NOT NULL COMMENT '管理员工',
  name         varchar(64)         NOT NULL COMMENT '名字',
  type         tinyint(1)          NOT NULL COMMENT '类型',
  discount     float               NOT NULL COMMENT '优惠金额',
  min          float               NOT NULL COMMENT '满足订单金额',
  amount       integer             NOT NULL COMMENT '数量',
  resume       text                COMMENT '说明',
  expired_type tinyint(4)          NOT NULL COMMENT '有效期类型 0-指定日期 1-发放指定',
  start_date   date                COMMENT '指定日期的开始日期',
  end_date     date                COMMENT '指定日期的结束日期',
  duration     int(11)             COMMENT '发放指定的有效天数',
  limit_wday   varchar(120)        COMMENT '指定星期',
  limit_room   varchar(120)        COMMENT '指定房间',
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
CREATE INDEX bb_coupons_staff_id on bb_coupons (project_id)