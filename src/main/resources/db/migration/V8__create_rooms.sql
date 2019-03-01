DROP TABLE IF EXISTS `bb_rooms`;
CREATE TABLE bb_rooms
(
  id                 bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  staff_id           bigint(11)          NOT NULL COMMENT '管理员工',
  name               varchar(64)         NOT NULL COMMENT '场地名字',
  cover              varchar(64)         NOT NULL COMMENT '场地封面图',
  image              varchar(640)        NOT NULL COMMENT '场地详情图',
  serial_no          varchar(32)         NOT NULL COMMENT '场地编号',
  address            varchar(256)        NOT NULL COMMENT '场地地址',
  building_no        varchar(16)         NOT NULL COMMENT '栋号',
  floor_no           varchar(16)         NOT NULL COMMENT '楼层编号',
  room_no            varchar(16)         NOT NULL COMMENT '房间编号',
  layer_height       double              NOT NULL COMMENT '楼层高度',
  layer_area         double              NOT NULL COMMENT '房间面积',
  quota              integer             NOT NULL COMMENT '容纳人数',
  rental             tinyint(1)          NOT NULL DEFAULT 0 COMMENT '出租方式',
  supervisor         varchar(64)         COMMENT '负责人',
  supervisor_mobile  varchar(12)         COMMENT '负责人电话',
  supervisor_email   varchar(64)         COMMENT '负责人邮箱',
  confirmation       tinyint(1)          NOT NULL COMMENT '订单确认方式',
  payment            tinyint(1)          NOT NULL COMMENT '支付方式',
  layout             tinyint(1)          NOT NULL COMMENT '布局方式',
  resume             text                NOT NULL,
  created_at         timestamp           DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  updated_at         timestamp           DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE INDEX bb_rooms_staff_id on bb_rooms (staff_id)

