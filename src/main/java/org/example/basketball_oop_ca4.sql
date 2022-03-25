DROP DATABASE IF EXISTS basketball_oop_ca4;
CREATE DATABASE basketball_oop_ca4;
DROP DATABASE IF EXISTS player;

CREATE TABLE player(id int NOT NULL AUTO_INCREMENT,
                    name VARCHAR(50),
                    age int,
                    height double,
                    PRIMARY KEY (id));

INSERT INTO player VALUES (1, "Vincent", 18, 1.9);
INSERT INTO player VALUES (2, "Paul", 21, 2.1);
INSERT INTO player VALUES (3, "John", 23, 2.2);
INSERT INTO player VALUES (4, "Larry", 25, 1.6);
INSERT INTO player VALUES (5, "Christian", 25, 1.7);
INSERT INTO player VALUES (6, "Andre", 24, 1.68);
INSERT INTO player VALUES (7, "Larry", 21, 1.9);
INSERT INTO player VALUES (8, "James", 27, 1.75);
INSERT INTO player VALUES (9, "Stephan", 30, 1.88);
INSERT INTO player VALUES (10, "Jack", 19, 1.92);