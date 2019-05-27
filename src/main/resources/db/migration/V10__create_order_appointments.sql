DROP TABLE IF EXISTS `bb_order_appointments`;
CREATE TABLE bb_order_appointments (
  id             bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  order_id       bigint(11) unsigned NOT NULL comment '订单编号',
  room_id        bigint(11) unsigned NOT NULL comment '会议室编号',
  meeting_name   varchar(32)         NOT NULL comment '自定义会议室名',
  start_time     datetime            NOT NULL comment '预约的开始时间',
  end_time       datetime            NOT NULL comment '预约的结束时间',
  contact_name   varchar(32)         NOT NULL comment '联系人名字',
  contact_mobile varchar(11)         NOT NULL comment '联系人电话',
  created_at     timestamp           DEFAULT CURRENT_TIMESTAMP,
  updated_at     timestamp           DEFAULT CURRENT_TIMESTAMP
  ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
CREATE INDEX bb_order_appointments_order_id on bb_order_appointments(order_id);
CREATE INDEX bb_order_appointments_room_id on bb_order_appointments(room_id);
CREATE INDEX bb_order_appointments_mobile on bb_order_appointments(contact_mobile)