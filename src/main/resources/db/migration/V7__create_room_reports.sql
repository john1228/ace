DROP TABLE IF EXISTS `bb_room_reports`;
CREATE TABLE bb_room_reports (
  id            BIGINT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  report_date   DATE COMMENT '报表时间',
  room_id       BIGINT(11)  DEFAULT 0 COMMENT '房间编号',
  room_name     VARCHAR(128) COMMENT '房间名字',
  online        DECIMAL(11) COMMENT '线上订单收入',
  offline       DECIMAL(11) COMMENT '线下订单收入',
  order_amount  BIGINT(11) COMMENT '订单总数',
  room_amount   BIGINT(11) DEFAULT 1 COMMENT '会议室数量',
  rented_amount DECIMAL(11) DEFAULT 0 COMMENT '出租时间',
  idle_amount   DECIMAL(11) DEFAULT 0 COMMENT '闲置时间',
  created_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
CREATE INDEX bb_room_reports_room_id ON bb_room_reports (room_id);