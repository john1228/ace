DROP TABLE IF EXISTS `bb_alipay`;
CREATE TABLE bb_alipay (
  id          bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  project_id  varchar(32)         NOT NULL,
  seller      varchar(32)         NOT NULL,
  primary_key text                NOT NULL,
  public_key  text                NOT NULL,
  created_at  timestamp                    DEFAULT CURRENT_TIMESTAMP,
  updated_at  timestamp                    DEFAULT CURRENT_TIMESTAMP
  ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
CREATE INDEX bb_alipay
  on bb_alipay (project_id);