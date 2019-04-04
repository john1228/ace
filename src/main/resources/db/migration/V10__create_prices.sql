DROP TABLE IF EXISTS `bb_prices`;
CREATE TABLE bb_prices (
  id           bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  staff_id     bigint(11)          NOT NULL COMMENT '管理员工',
  room_id      varchar(32)         NOT NULL COMMENT '会议室编号',
  room_name    varchar(64)         NOT NULL COMMENT '会议室名字',
  rental       tinyint(1)          DEFAULT 0 COMMENT '出租方式',
  start_date   date                COMMENT '开始日期',
  end_date     date                COMMENT '结束日期',
  start_time   varchar(5)          COMMENT '开始时间',
  end_time     varchar(5)          COMMENT '结束时间',
  wday         varchar(64)         DEFAULT 0 COMMENT '星期',
  price        decimal             DEFAULT 0 COMMENT '价格',
  created_at   timestamp           DEFAULT CURRENT_TIMESTAMP,
  updated_at   timestamp           DEFAULT CURRENT_TIMESTAMP
  ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
CREATE INDEX bb_prices_staff_id on bb_prices (staff_id)