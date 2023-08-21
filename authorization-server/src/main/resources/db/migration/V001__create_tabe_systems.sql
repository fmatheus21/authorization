CREATE TABLE systems (
id binary(16) NOT NULL DEFAULT (UUID_TO_BIN(UUID())),
name varchar(70) NOT NULL,
PRIMARY KEY (id),
UNIQUE KEY id_UNIQUE (id),
UNIQUE KEY name_UNIQUE (name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO systems (id, name) VALUES (UUID_TO_BIN('50b56e7d-2c5e-11ee-a204-581122c7752d'), 'USER_SERVICE');