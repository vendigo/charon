DROP TABLE SAMPLE_RAW;

CREATE TABLE SAMPLE_RAW (
  fileId bigint not null,
  lineNumber bigint not null,
  id varchar(255),
  name varchar(255),
  age varchar(255),
  PRIMARY KEY (fileId, lineNumber)
);