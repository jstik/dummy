INSERT INTO "CATALOG_ITEM"( id, NAME, PARENT_ID) VALUES (1, 'Root', null);
INSERT INTO "CATALOG_ITEM"( id, NAME, PARENT_ID) VALUES (2,'Ch1', 1);
INSERT INTO "CATALOG_ITEM"(id, NAME, PARENT_ID) VALUES (3,'Ch1-Ch1', 2);
INSERT INTO "CATALOG_ITEM"(id, NAME, PARENT_ID) VALUES (4,'Ch1-Ch2', 2);
INSERT INTO "CATALOG_ITEM"(id, NAME, PARENT_ID) VALUES (5,'Child', 3);
INSERT INTO "CUSTOMER" ( NAME, EMAIL, CREATED_DATE) VALUES( 'mkyong','111@yahoo.com', TO_DATE('2017-02-11', 'yyyy-mm-dd'));
INSERT INTO "CUSTOMER" ( NAME, EMAIL, CREATED_DATE) VALUES( 'имя','222@yahoo.com', TO_DATE('2017-02-12', 'yyyy-mm-dd'));
INSERT INTO "CUSTOMER" ( NAME, EMAIL, CREATED_DATE) VALUES( 'zilap','333@yahoo.com', TO_DATE('2017-02-13', 'yyyy-mm-dd'));

