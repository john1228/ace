DROP TABLE IF EXISTS `bb_protocol`;
CREATE TABLE bb_protocol (
  id         bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  project_id varchar(32)         NOT NULL,
  content    TEXT                NOT NULL,
  created_at timestamp                    DEFAULT CURRENT_TIMESTAMP,
  updated_at timestamp                    DEFAULT CURRENT_TIMESTAMP
  ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
CREATE INDEX bb_protocol
  on bb_protocol (project_id);