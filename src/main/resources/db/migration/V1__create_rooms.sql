DROP TABLE IF EXISTS `bb_rooms`;
CREATE TABLE bb_rooms
(
  id                 bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  staff_id           bigint(11)          NOT NULL COMMENT '管理员工',
  name               varchar(64)         NOT NULL COMMENT '场地名字',
  cover              varchar(64)         NOT NULL COMMENT '场地封面图',
  image              varchar(640)        NOT NULL COMMENT '场地详情图',
  serial_no          varchar(32)         NOT NULL COMMENT '场地编号',
  building_no        varchar(16)         NOT NULL COMMENT '栋号',
  floor_no           varchar(16)         NOT NULL COMMENT '楼层编号',
  room_no            varchar(16)         NOT NULL COMMENT '房间编号',
  layer_height       double              NOT NULL COMMENT '楼层高度',
  type               tinyint(1)          NOT NULL COMMENT '场地类型',
  publish            tinyint(1)          NOT NULL COMMENT '发布类型',
  layer_area         double              NOT NULL COMMENT '房间面积',
  quota              integer             NOT NULL COMMENT '容纳人数',
  price              integer             COMMENT '参考价格',
  free               boolean             NOT NULL COMMENT '是否收费',
  free_org           text                NOT NULL COMMENT '免费组织',
  open_date          date                NOT NULL COMMENT '开放开始日期',
  close_date         date                NOT NULL COMMENT '开放结束日期',
  unit               integer             NOT NULL COMMENT '起租时间',
  renew              integer             NOT NULL COMMENT '续租时间',
  rental             tinyint(1)          NOT NULL COMMENT '出租方式',
  supervisor         varchar(64)         NOT NULL COMMENT '负责人',
  supervisor_mobile  varchar(12)         NOT NULL COMMENT '负责人电话',
  supervisor_email   varchar(64)         COMMENT '负责人邮箱',
  payable            boolean             NOT NULL COMMENT '支付方式',
  cfm                tinyint(1)          NOT NULL COMMENT '订单确认方式: 0-先付款后确认 1-确认后付款 2-无需确认',
  refund_limit_time  integer             NOT NULL COMMENT '退款时间限制',
  free_service       varchar(640)        COMMENT '免费服务',
  charging_service   varchar(640)        COMMENT '收费服务',
  resume             text                NOT NULL,
  created_at         timestamp           DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  updated_at         timestamp           DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE INDEX bb_rooms_staff_id on bb_rooms (staff_id)

