DROP TABLE IF EXISTS `bb_supports`;
CREATE TABLE bb_supports (
  id           bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  project_id   varchar(32)         NOT NULL COMMENT '所属项目',
  org_id       varchar(32)         NOT NULL COMMENT '所属组织',
  name         varchar(64)         NOT NULL COMMENT '名称',
  cover        varchar(128)        NOT NULL COMMENT '图片',
  unit         varchar(32)         NOT NULL COMMENT '单位',
  created_at   timestamp           DEFAULT CURRENT_TIMESTAMP,
  updated_at   timestamp           DEFAULT CURRENT_TIMESTAMP
  ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;