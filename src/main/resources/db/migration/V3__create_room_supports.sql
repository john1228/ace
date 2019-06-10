DROP TABLE IF EXISTS `bb_rooms_supports`;
CREATE TABLE bb_rooms_supports (
  id           bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  room_id      bigint(11)          NOT NULL COMMENT '会议室',
  support_id   bigint(11)          NOT NULL COMMENT '服务',
  remark       VARCHAR(1024)       COMMENT '备注',
  price        DECIMAL             NOT NULL COMMENT '价格',
  created_at   timestamp           DEFAULT CURRENT_TIMESTAMP,
  updated_at   timestamp           DEFAULT CURRENT_TIMESTAMP
  ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
CREATE INDEX bb_rooms_supports_staff_id on bb_rooms_supports(staff_id);
CREATE INDEX bb_rooms_supports_room_id on bb_rooms_supports(room_id);
CREATE INDEX bb_rooms_supports_support_id on bb_rooms_supports(support_id);