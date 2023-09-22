CREATE TABLE person_type (
  id INT NOT NULL AUTO_INCREMENT,
  uuid binary(16) NOT NULL DEFAULT (UUID_TO_BIN(UUID())),
  name varchar(15) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY id_UNIQUE (id),
  UNIQUE KEY uuid_UNIQUE (uuid),
  UNIQUE KEY name_UNIQUE (name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO person_type (id, uuid, name) VALUES (1, UUID_TO_BIN('0015dc51-05d5-11ee-900d-7085c2be6d69'), 'PESSOA FISICA');
INSERT INTO person_type (id, uuid, name) VALUES (2, UUID_TO_BIN('032086be-05d5-11ee-900d-7085c2be6d69'), 'PESSOA JURIDICA');