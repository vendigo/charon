DROP TABLE INFILE;
DROP TABLE INFILESTATUS;
DROP TABLE SAMPLE_RAW;
DROP TABLE SAMPLE;
DROP TABLE SAMPLE_HIST;

CREATE TABLE INFILE
(
  FILEID BIGINT PRIMARY KEY NOT NULL IDENTITY,
  ABSOLUTEFILEPATH VARCHAR(255),
  FILELENGTH BIGINT,
  FILENAME VARCHAR(255)
);

CREATE TABLE INFILESTATUS
(
  FILESTATUSID BIGINT PRIMARY KEY NOT NULL IDENTITY,
  CREATIONTIME TIMESTAMP,
  FILEID BIGINT,
  STATE VARCHAR(255)
);

CREATE TABLE SAMPLE_RAW (
  fileId bigint not null,
  lineNumber bigint not null,
  id varchar(255),
  name varchar(255),
  age varchar(255),
  PRIMARY KEY (fileId, lineNumber)
);

CREATE TABLE SAMPLE (
  fileId bigint not null,
  lineNumber bigint not null,
  id bigint,
  name varchar(255),
  age varchar(255),
  PRIMARY KEY (fileId, lineNumber)
);

CREATE TABLE SAMPLE_HIST (
  fileId bigint not null,
  lineNumber bigint not null,
  id bigint,
  name varchar(255),
  age varchar(255),
  PRIMARY KEY (fileId, lineNumber)
);