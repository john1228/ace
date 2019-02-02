CREATE TABLE p_attributes (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  name varchar(100) NOT NULL,
  type varchar(50) NOT NULL,
  value varchar(50) DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY PAK_name (name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;