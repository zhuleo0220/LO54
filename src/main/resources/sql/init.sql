CREATE TABLE Location (
                          Id INTEGER GENERATED ALWAYS AS IDENTITY NOT NULL,
                          City VARCHAR(45) NOT NULL,
                          PRIMARY KEY (Id))
;


-- -----------------------------------------------------
-- Table 'mydb'.'Course'
-- -----------------------------------------------------
CREATE Table Course(
                       Id INTEGER GENERATED ALWAYS AS IDENTITY NOT NULL,
                       Code VARCHAR(6) NOT NULL,
                       Title VARCHAR(45) NOT NULL,
                       PRIMARY KEY (Id));


-- -----------------------------------------------------
-- Table 'mydb'.'Course_Session'
-- -----------------------------------------------------
CREATE TABLE Course_Session (
                                Id INTEGER GENERATED ALWAYS AS IDENTITY NOT NULL,
                                Start_date TIMESTAMP NOT NULL,
                                End_date TIMESTAMP NOT NULL,
                                Max_Number INT ,
                                Course_Id INTEGER NOT NULL,
                                Location_Id INTEGER NOT NULL,
                                PRIMARY KEY (Id)
                                );


-- -----------------------------------------------------
-- Table 'mydb'.'Client'
-- -----------------------------------------------------
CREATE TABLE Client (
                        Id INTEGER GENERATED ALWAYS AS IDENTITY NOT NULL,
                        LastName VARCHAR(45) NOT NULL,
                        FirstName VARCHAR(45) NOT NULL,
                        Address VARCHAR(128) NOT NULL,
                        Phone VARCHAR(12) NOT NULL,
                        Email VARCHAR(254) NOT NULL,
                        Course_Session_Id INT NOT NULL,
                        PRIMARY KEY (Id));

ALTER TABLE COURSE_SESSION
    ADD FOREIGN KEY (Course_Id)
        REFERENCES Course (Id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION;

ALTER TABLE COURSE_SESSION
    ADD FOREIGN KEY (Location_Id)
        REFERENCES Location (Id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION;

ALTER TABLE Client
    ADD FOREIGN KEY (Course_Session_Id)
        REFERENCES Course_Session (Id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION;

-- -------------------------------------------------------
-- Insert data
-- -------------------------------------------------------

INSERT INTO Location (City)
VALUES ('Belfort');

INSERT INTO Location (City)
VALUES ('Besancon');

INSERT INTO Location (City)
VALUES ('Montbeliard');

INSERT INTO Location (City)
VALUES ('Dijon');

INSERT INTO Location (City)
VALUES ('Lyon');

INSERT INTO Location (City)
VALUES ('Paris');

INSERT INTO Location (City)
VALUES ('Sevenans');

INSERT INTO Course (Code, Title)
VALUES ('LOGI54', 'Java Enterprise Applications Architectures');

INSERT INTO Course (Code, Title)
VALUES ('IMNU54', 'Reconnaissance des formes');

INSERT INTO Course (Code, Title)
VALUES ('IMNU54', 'Reconnaissance des formes');

INSERT INTO Course_Session (Start_date, End_date, Max_Number, Course_Id, Location_Id)
VALUES ('2021-06-21 15:30:00', '2021-06-21 18:00:00', 32, 1, 1);

INSERT INTO Course_Session (Start_date, End_date, Max_Number, Course_Id, Location_Id)
VALUES ('2021-06-28 15:30:00', '2021-06-28 18:00:00', 32, 2, 1);

INSERT INTO Client (LastName, FirstName, Address, Phone, Email, Course_Session_Id)
VALUES ('Eric', 'Dampierre', '3 Boulevard Pasteur', '0698488126', 'Eric.dampiere978@gmail.com', 1);


