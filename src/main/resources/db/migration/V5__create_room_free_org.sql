DROP TABLE IF EXISTS `bb_room_free_org`;
CREATE TABLE bb_room_free_org (
  id           bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  org_id       varchar(32)         NOT NULL COMMENT '免费组织',
  org_name     varchar(64)         NOT NULL COMMENT '免费组织名',
  created_at   timestamp           DEFAULT CURRENT_TIMESTAMP,
  updated_at   timestamp           DEFAULT CURRENT_TIMESTAMP
  ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  UNIQUE KEY (project_id,org_id,emp_id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
CREATE INDEX bb_room_free_org_id on bb_room_free_org (org_id)