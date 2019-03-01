DROP TABLE IF EXISTS `bb_devices`;
CREATE TABLE bb_devices (
  id           bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  staff_id     varchar(32)         NOT NULL COMMENT '管理人员',
  name         varchar(64)         NOT NULL COMMENT '设备名称',
  price        decimal             NOT NULL COMMENT '设备价格',
  unit         varchar(32)         NOT NULL COMMENT '设备单位'  ,
  status       tinyint(1)          DEFAULT 0 COMMENT '设备状态 0-失效 1-启用',
  created_at   timestamp           DEFAULT CURRENT_TIMESTAMP,
  updated_at   timestamp           DEFAULT CURRENT_TIMESTAMP
  ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
CREATE INDEX bb_devices_staff_id on bb_devices (staff_id)