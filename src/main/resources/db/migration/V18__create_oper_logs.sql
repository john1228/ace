DROP TABLE IF EXISTS `bb_oper_logs`;
CREATE TABLE bb_oper_logs (
  id         bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  emp_id     varchar(32)         NOT NULL,
  emp_name   varchar(128)        NOT NULL,
  controller varchar(64)         NOT NULL,
  operation  varchar(64)         NOT NULL,
  ip         varchar(24)         NOT NULL,
  created_at timestamp                    DEFAULT CURRENT_TIMESTAMP,
  updated_at timestamp                    DEFAULT CURRENT_TIMESTAMP
  ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
CREATE INDEX bb_oper_logs_emp_id
  on bb_oper_logs (emp_id);
CREATE INDEX bb_oper_logs_emp_name
  on bb_oper_logs (emp_id);