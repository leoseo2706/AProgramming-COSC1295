--
-- File generated with SQLiteStudio v3.2.1 on Mon Sep 28 22:59:53 2020
--
-- Text encoding used: UTF-8
--
PRAGMA foreign_keys = off;
BEGIN TRANSACTION;

-- Table: Company
DROP TABLE IF EXISTS Company;

CREATE TABLE Company (
    ID      STRING PRIMARY KEY
                   NOT NULL
                   UNIQUE,
    Name    STRING,
    ABN     STRING,
    URL     STRING,
    Address STRING
)
WITHOUT ROWID;


-- Table: Project
DROP TABLE IF EXISTS Project;

CREATE TABLE Project (
    ID          STRING NOT NULL
                       UNIQUE,
    Title       STRING,
    Description STRING,
    OwnerID     STRING,
    SkillRankP  INT,
    SkillRankN  INT,
    SkillRankA  INT,
    SkillRankW  INT,
    Active      INT
);


-- Table: ProjectOwner
DROP TABLE IF EXISTS ProjectOwner;

CREATE TABLE ProjectOwner (
    ID        STRING PRIMARY KEY
                     NOT NULL
                     UNIQUE,
    FirstName STRING,
    Surname   STRING,
    Email     STRING,
    CompanyID STRING,
    Role      STRING
);


-- Table: Student
DROP TABLE IF EXISTS Student;

CREATE TABLE Student (
    ID                 STRING PRIMARY KEY
                              NOT NULL
                              UNIQUE,
    GradeP             INT,
    GradeN             INT,
    GradeA             INT,
    GradeW             INT,
    Personality        STRING,
    ConflictedStudent1 STRING,
    ConflictedStudent2 STRING
)
WITHOUT ROWID;


-- Table: StudentPreference
DROP TABLE IF EXISTS StudentPreference;

CREATE TABLE StudentPreference (
    StudentID       STRING NOT NULL,
    ProjectID       STRING NOT NULL,
    PreferenceScore INT    NOT NULL,
    Active          INT
);


COMMIT TRANSACTION;
PRAGMA foreign_keys = on;
