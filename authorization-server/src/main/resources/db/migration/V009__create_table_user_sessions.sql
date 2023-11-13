CREATE TABLE user_sessions (
  id int NOT NULL AUTO_INCREMENT,
  id_user int NOT NULL,
  ip_address varchar(20) NOT NULL,
  city varchar(50) NOT NULL,
  country varchar(50) NOT NULL,
  state varchar(50) NOT NULL,
  latitude varchar(30) NOT NULL,
  longitude varchar(30) NOT NULL,
  date datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  UNIQUE KEY id_UNIQUE (id),
  CONSTRAINT fk_user_sessions FOREIGN KEY (id_user) REFERENCES user (id) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
