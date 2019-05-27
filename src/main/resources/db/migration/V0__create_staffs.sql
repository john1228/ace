DROP TABLE IF EXISTS `bb_staffs`;
CREATE TABLE bb_staffs (
  id           bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  account_id   varchar(32)         NOT NULL COMMENT '账户',
  account_name varchar(64)         NOT NULL COMMENT '账户名',
  project_id   varchar(32)         NOT NULL COMMENT '项目编号',
  project_name varchar(64)         NOT NULL COMMENT '项目名称',
  org_id       varchar(32)         NOT NULL COMMENT '组织编号',
  org_name     varchar(64)         NOT NULL COMMENT '组织名称',
  emp_id       varchar(32)         NOT NULL COMMENT '员工编号',
  emp_name     varchar(64)         COMMENT '员工名字',
  created_at   timestamp           DEFAULT CURRENT_TIMESTAMP,
  updated_at   timestamp           DEFAULT CURRENT_TIMESTAMP
  ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  UNIQUE KEY (project_id,org_id,emp_id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
CREATE INDEX bb_staffs_refs on bb_staffs (project_id,org_id,emp_id)