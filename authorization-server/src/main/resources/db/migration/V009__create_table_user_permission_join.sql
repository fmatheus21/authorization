CREATE TABLE user_permission_join (
  id_user INT NOT NULL,
  id_permission INT NOT NULL,
  PRIMARY KEY (id_user, id_permission),
  CONSTRAINT fk_user_join FOREIGN KEY (id_user) REFERENCES user (id) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT fk_permission_join FOREIGN KEY (id_permission) REFERENCES permission (id) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO user_permission_join (id_user, id_permission) VALUES (1, 1);
