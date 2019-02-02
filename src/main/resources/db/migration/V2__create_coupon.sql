DROP TABLE IF EXISTS `bb_coupons`;
CREATE TABLE bb_coupons (
  id              bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  project_id      varchar(32)                  DEFAULT NULL,
  name            varchar(64)                  DEFAULT NULL,
  type            tinyint(1)                   DEFAULT NULL,
  discount        float                        DEFAULT NULL,
  min             float                        DEFAULT NULL,
  resume          text,
  expired_type    tinyint(4)                   DEFAULT NULL,
  start_date      date                         DEFAULT NULL,
  end_date        date                         DEFAULT NULL,
  duration        int(11)                      DEFAULT NULL,
  limit_pro       varchar(32),
  limit_pro_name  varchar(128),
  limit_org       varchar(32),
  limit_org_name  varchar(128),
  limit_room      varchar(32),
  limit_room_name varchar(128),
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;