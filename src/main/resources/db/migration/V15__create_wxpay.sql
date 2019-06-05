DROP TABLE IF EXISTS `bb_wxpay`;
CREATE TABLE bb_wxpay (
  id         bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  project_id varchar(32)         NOT NULL,
  app_id     varchar(32)         NOT NULL,
  mch_id     varchar(32)         NOT NULL,
  secret_key varchar(32)         NOT NULL,
  created_at timestamp                    DEFAULT CURRENT_TIMESTAMP,
  updated_at timestamp                    DEFAULT CURRENT_TIMESTAMP
  ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
CREATE INDEX bb_wxpay
  on bb_wxpay (project_id);