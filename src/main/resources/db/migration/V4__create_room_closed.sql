DROP TABLE IF EXISTS `bb_room_closed`;
CREATE TABLE bb_room_closed (
  id           bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  room_id      bigint(32)         NOT NULL COMMENT '账户',
  start_date   date                COMMENT '指定日期的开始日期',
  end_date     date                COMMENT '指定日期的结束日期',
  start_time   varchar(5)          COMMENT '开始时间',
  end_time     varchar(5)          COMMENT '结束时间',
  remark       text                COMMENT '备注',
  created_at   timestamp           DEFAULT CURRENT_TIMESTAMP,
  updated_at   timestamp           DEFAULT CURRENT_TIMESTAMP
  ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
CREATE INDEX bb_room_closed_room_id on bb_room_closed (room_id)