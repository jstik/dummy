--create table customer (id number(19,0) generated as identity, created_date timestamp, email varchar2(255 char), name varchar2(255 char), primary key (id))

/*BEGIN
  EXECUTE IMMEDIATE 'DROP TABLE DATABASECHANGELOGLOCK';
  EXCEPTION
  WHEN OTHERS THEN
  IF SQLCODE != -942 THEN
    RAISE;
  END IF;
END;

BEGIN
  EXECUTE IMMEDIATE 'DROP TABLE DATABASECHANGELOG;';
  EXCEPTION
  WHEN OTHERS THEN
  IF SQLCODE != -942 THEN
    RAISE;
  END IF;
END;*/
INSERT INTO "CUSTOMER" ( NAME, EMAIL, CREATED_DATE) VALUES( 'mkyong','111@yahoo.com', TO_DATE('2017-02-11', 'yyyy-mm-dd'));
INSERT INTO "CUSTOMER" ( NAME, EMAIL, CREATED_DATE) VALUES( 'имя','222@yahoo.com', TO_DATE('2017-02-12', 'yyyy-mm-dd'));
INSERT INTO "CUSTOMER" ( NAME, EMAIL, CREATED_DATE) VALUES( 'zilap','333@yahoo.com', TO_DATE('2017-02-13', 'yyyy-mm-dd'));

INSERT INTO "TENANT"(NAME, url, dbUser, db_password) VALUES ('tenant1', 'jdbc:oracle:thin:@localhost:1521/pdborcl', 't1', 'sa');
INSERT INTO "TENANT"(NAME, url, dbUser, db_password) VALUES ('tenant2', 'jdbc:oracle:thin:@localhost:1521/pdborcl', 't1', 'sa');