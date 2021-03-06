DROP TABLE IF EXISTS `bb_room_prices`;
CREATE TABLE bb_room_prices (
  id           bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  rental       tinyint(1)          DEFAULT 0 COMMENT '出租方式',
  start_date   date                COMMENT '开始日期',
  end_date     date                COMMENT '结束日期',
  start_time   varchar(5)          COMMENT '开始时间',
  end_time     varchar(5)          COMMENT '结束时间',
  wday         varchar(64)         DEFAULT 0 COMMENT '星期',
  price        decimal(10,2)       DEFAULT 0 COMMENT '价格',
  created_at   timestamp           DEFAULT CURRENT_TIMESTAMP,
  updated_at   timestamp           DEFAULT CURRENT_TIMESTAMP
  ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
CREATE INDEX bb_room_prices_staff_id on bb_room_prices (staff_id);

DROP TABLE IF EXISTS `bb_room_prices_ref`;
CREATE TABLE bb_room_prices_ref (
  id           bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  price_id     bigint(11)          NOT NULL COMMENT '关联价格编号',
  room_id      bigint(11)          NOT NULL COMMENT '关联会议室编号',
  PRIMARY KEY (id),
  UNIQUE KEY (room_id, price_id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
CREATE INDEX bb_room_prices_ref_price_id on bb_room_prices_ref (price_id);
CREATE INDEX bb_room_prices_ref_room_id on bb_room_prices_ref (room_id);