CREATE TABLE CUSTOMER (
  ID INT AUTO_INCREMENT  PRIMARY KEY,
  CUSTOMER_FNAME VARCHAR(250) NOT NULL,
  CUSTOMER_LNAME VARCHAR(250) NOT NULL,
  CUSTOMER_AGE VARCHAR(250) DEFAULT NULL,
  CUSTOMER_GENDER VARCHAR(250) DEFAULT NULL,
  CUSTOMER_DESC VARCHAR(250) DEFAULT NULL,
  CREATED_DATE TIMESTAMP  DEFAULT NULL,
  MODIFIED_DATE TIMESTAMP  DEFAULT  NULL
);
