CREATE TABLE user_profile_join (
  id_user INT NOT NULL,
  id_profile INT NOT NULL,
  PRIMARY KEY (id_user, id_profile),
  CONSTRAINT fk_user_profile_join FOREIGN KEY (id_user) REFERENCES user (id) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT fk_profile_join FOREIGN KEY (id_profile) REFERENCES profile (id) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO user_profile_join (id_user, id_profile) VALUES (1, 1);