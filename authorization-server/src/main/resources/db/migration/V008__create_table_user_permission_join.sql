CREATE TABLE user_permission_join (
  id_user INT NOT NULL,
  id_permission INT NOT NULL,
  PRIMARY KEY (id_user,id_permission)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO user_permission_join (id_user, id_permission) VALUES (1, 1);
