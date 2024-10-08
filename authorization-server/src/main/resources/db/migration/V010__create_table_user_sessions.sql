CREATE TABLE user_sessions (
  id int NOT NULL AUTO_INCREMENT,
  id_user int NOT NULL,
  id_systems int NOT NULL,
  zip_code varchar(20) NOT NULL,
  place varchar(50) NOT NULL,
  district varchar(30) NOT NULL,
  city varchar(30) NOT NULL,
  state varchar(10) NOT NULL,
  status varchar(15) NOT NULL,
  message varchar(100) NOT NULL,
  date datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  UNIQUE KEY id_UNIQUE (id),
  CONSTRAINT fk_user_sessions FOREIGN KEY (id_user) REFERENCES user (id) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT fk_systems_user_sessions FOREIGN KEY (id_systems) REFERENCES systems (id) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
