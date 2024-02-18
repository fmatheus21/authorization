CREATE TABLE permission (
  id INT NOT NULL AUTO_INCREMENT,
  uuid binary(16) NOT NULL DEFAULT (UUID_TO_BIN(UUID())),
  id_system INT NOT NULL,
  name varchar(50) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY id_UNIQUE (id),
  UNIQUE KEY uuid_UNIQUE (uuid),
  UNIQUE KEY name_UNIQUE (name),
  KEY fk_systems (id_system),
  CONSTRAINT fk_systems FOREIGN KEY (id_system) REFERENCES systems (id) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO permission (id, uuid, id_system, name) VALUES (1, UUID_TO_BIN('f8ed48e0-5879-11ee-94e1-581122c7752d'), 1, 'USER_SERVICE/ALL_AUTHORIZE');
INSERT INTO permission (id, uuid, id_system, name) VALUES (2, UUID_TO_BIN('898f2f67-587a-11ee-94e1-581122c7752d'), 1, 'USER_SERVICE/USER_CREATE');
INSERT INTO permission (id, uuid, id_system, name) VALUES (3, UUID_TO_BIN('8c6ce255-587a-11ee-94e1-581122c7752d'), 1, 'USER_SERVICE/USER_READ');
INSERT INTO permission (id, uuid, id_system, name) VALUES (4, UUID_TO_BIN('8f43c7bc-587a-11ee-94e1-581122c7752d'), 1, 'USER_SERVICE/USER_UPDATE');
INSERT INTO permission (id, uuid, id_system, name) VALUES (5, UUID_TO_BIN('920a3688-587a-11ee-94e1-581122c7752d'), 1, 'USER_SERVICE/USER_DELETE');
INSERT INTO permission (id, uuid, id_system, name) VALUES (6, UUID_TO_BIN('06af1603-7e57-11ee-ae63-581122c7752d'), 1, 'USER_SERVICE/SYSTEMS_CREATE');
INSERT INTO permission (id, uuid, id_system, name) VALUES (7, UUID_TO_BIN('3126696e-7e57-11ee-ae63-581122c7752d'), 1, 'USER_SERVICE/SYSTEMS_READ');
INSERT INTO permission (id, uuid, id_system, name) VALUES (8, UUID_TO_BIN('348d08c6-7e57-11ee-ae63-581122c7752d'), 1, 'USER_SERVICE/SYSTEMS_UPDATE');
INSERT INTO permission (id, uuid, id_system, name) VALUES (9, UUID_TO_BIN('388f8d7d-7e57-11ee-ae63-581122c7752d'), 1, 'USER_SERVICE/SYSTEMS_DELETE');
INSERT INTO permission (id, uuid, id_system, name) VALUES (10, UUID_TO_BIN('7d929f95-cdf3-11ee-8e34-581122c7752d'), 2, 'CATALOG_SERVICE/ALL_AUTHORIZE');
INSERT INTO permission (id, uuid, id_system, name) VALUES (11, UUID_TO_BIN('d070c57b-cdf4-11ee-8e34-581122c7752d'), 2, 'CATALOG_SERVICE/PRODUCT_CREATE');
INSERT INTO permission (id, uuid, id_system, name) VALUES (12, UUID_TO_BIN('dbb72e44-cdf4-11ee-8e34-581122c7752d'), 2, 'CATALOG_SERVICE/PRODUCT_READ');
INSERT INTO permission (id, uuid, id_system, name) VALUES (13, UUID_TO_BIN('e070d441-cdf4-11ee-8e34-581122c7752d'), 2, 'CATALOG_SERVICE/PRODUCT_UPDATE');
INSERT INTO permission (id, uuid, id_system, name) VALUES (14, UUID_TO_BIN('e4c0636f-cdf4-11ee-8e34-581122c7752d'), 2, 'CATALOG_SERVICE/PRODUCT_DELETE');