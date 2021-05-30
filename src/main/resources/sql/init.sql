-- -----------------------------------------------------
-- Table 'Location'
-- -----------------------------------------------------
CREATE TABLE Location (
  Id INTEGER GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  City VARCHAR(45) NOT NULL,
  PRIMARY KEY (Id))
;


-- -----------------------------------------------------
-- Table 'Course'
-- -----------------------------------------------------
CREATE Table Course(
  Code char(4) NOT NULL,
  Title VARCHAR(45) NOT NULL,
  PRIMARY KEY (Code));


-- -----------------------------------------------------
-- Table 'Course_Session'
-- -----------------------------------------------------
CREATE TABLE Course_Session (
  Id INTEGER GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) NOT NULL,
  Start_date TIMESTAMP NOT NULL,
  End_date TIMESTAMP NOT NULL,
  Max_Student INT,
  Course_Code char(4) NOT NULL,
  Location_Id INT NOT NULL,
  PRIMARY KEY (Id),
  CONSTRAINT fk_Course_Session_Course
    FOREIGN KEY (Course_Code)
    REFERENCES Course (Code)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_Course_Session_Location1
    FOREIGN KEY (Location_Id)
    REFERENCES Location (Id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;


-- -----------------------------------------------------
-- Table 'Client'
-- -----------------------------------------------------
CREATE TABLE Client (
  Id INTEGER GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) NOT NULL,
  LastName VARCHAR(45) NOT NULL,
  FirstName VARCHAR(45) NOT NULL,
  Address VARCHAR(128) NOT NULL,
  Phone VARCHAR(12) NOT NULL,
  Email VARCHAR(254) NOT NULL,
  Course_Session_Id INT NOT NULL,
  PRIMARY KEY (Id),
  CONSTRAINT fk_Client_Course_Session1
    FOREIGN KEY (Course_Session_Id)
    REFERENCES Course_Session (Id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;


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
VALUES ('LO54', 'Java Enterprise Applications Architectures');

INSERT INTO Course (Code, Title)
VALUES ('IN54', 'Reconnaissance des formes');

INSERT INTO Course_Session (Start_date, End_date, Max_Student, Course_Code, Location_Id)
VALUES ('2021-06-21 15:30:00', '2021-06-21 18:00:00', 32, 'LO54', 1);

INSERT INTO Course_Session (Start_date, End_date, Max_Student, Course_Code, Location_Id)
VALUES ('2021-06-28 15:30:00', '2021-06-28 18:00:00', 32, 'LO54', 1);

INSERT INTO Client (LastName, FirstName, Address, Phone, Email, Course_Session_Id)
VALUES ('Eric', 'Dampierre', '3 Boulevard Pasteur', '0698488126', 'Eric.dampiere978@gmail.com', 1);

