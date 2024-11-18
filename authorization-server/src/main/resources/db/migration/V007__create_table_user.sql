CREATE TABLE user (
  id INT NOT NULL AUTO_INCREMENT,
  uuid binary(16) NOT NULL DEFAULT (UUID_TO_BIN(UUID())),
  id_person INT NOT NULL,
  id_profile INT NOT NULL,
  username varchar(50) NOT NULL,
  password varchar(200) NOT NULL,
  active tinyint(1) NOT NULL DEFAULT '1',
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  UNIQUE KEY id_UNIQUE (id),
  UNIQUE KEY uuid_UNIQUE (uuid),
  UNIQUE KEY id_person_UNIQUE (id_person),
  UNIQUE KEY username_UNIQUE (username),
  CONSTRAINT fk_person_user FOREIGN KEY (id_person) REFERENCES person (id) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT fk_profile_user FOREIGN KEY (id_profile) REFERENCES profile (id) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO user (id, uuid, id_person, id_profile, username, password, active, created_at, updated_at)
VALUES (1, UUID_TO_BIN('ae46dc08-2c64-11ee-a204-581122c7752d'), 1, 1, '67780886050', '$2a$10$04MQf8gTMmbC1aosZajxeO.r1fCtjLbvpVfbZQJD7XbR0oFQ9X1AC', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
