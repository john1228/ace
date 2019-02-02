DROP TABLE IF EXISTS `bb_devices`;
CREATE TABLE bb_devices (
  id           bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  account_id   varchar(32)         NOT NULL,
  name         varchar(64)         NOT NULL ,
  price        decimal             NOT NULL ,
  unit         varchar(32)         NOT NULL    ,
  status       tinyint(1)          DEFAULT 0,
  created_at   timestamp           DEFAULT CURRENT_TIMESTAMP,
  updated_at   timestamp           DEFAULT CURRENT_TIMESTAMP
  ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;