-- Drop statements
DROP TABLE INFILE;
DROP TABLE INFILESTATUS;
DROP TABLE SAMPLE_RAW;
DROP TABLE SAMPLE;
DROP TABLE SAMPLE_HIST;
-- Truncate statements
TRUNCATE TABLE INFILE;
TRUNCATE TABLE INFILESTATUS;
TRUNCATE TABLE SAMPLE_RAW;
TRUNCATE TABLE SAMPLE;
TRUNCATE TABLE SAMPLE_HIST;

-- Create statements
-- Utility tables
CREATE TABLE INFILE
(
  FILEID BIGINT PRIMARY KEY NOT NULL IDENTITY,
  ABSOLUTEFILEPATH VARCHAR(255),
  FILELENGTH BIGINT,
  FILECONFIGNAME VARCHAR(255),
  FILENAME VARCHAR(255)
);

CREATE TABLE INFILESTATUS
(
  FILESTATUSID BIGINT PRIMARY KEY NOT NULL IDENTITY,
  CREATIONTIME TIMESTAMP,
  FILEID BIGINT,
  PROCESSED BIT,
  STATE VARCHAR(255)
);

-- Simple sample tables
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

-- Real test tables
CREATE TABLE TEST_RAW (
  fileId bigint not null,
  lineNumber bigint not null,
  columnType varchar(255),
  businessDate varchar(255),
  account varchar(255),
  accountName varchar(255),
  someSecurity varchar(255),
  quantity varchar(255),
  shouldReportToAldebaran varchar(255),
  PRIMARY KEY (fileId, lineNumber)
);

CREATE TABLE TEST (
  fileId bigint not null,
  lineNumber bigint not null,
  columnType varchar(255),
  businessDate date,
  account varchar(255),
  accountName varchar(255),
  someSecurity varchar(255),
  quantity decimal,
  shouldReportToAldebaran bit,
  PRIMARY KEY (fileId, lineNumber)
);

CREATE TABLE TEST_HIST (
  fileId bigint not null,
  lineNumber bigint not null,
  columnType varchar(255),
  businessDate date,
  account varchar(255),
  accountName varchar(255),
  someSecurity varchar(255),
  quantity decimal,
  shouldReportToAldebaran bit,
  PRIMARY KEY (fileId, lineNumber)
);
