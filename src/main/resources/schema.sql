DROP TABLE IF EXISTS TBL_EMPLOYEES;
  
CREATE TABLE TBL_EMPLOYEES (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  email VARCHAR(250) DEFAULT NULL
);

DROP TABLE IF EXISTS PRODUCT;
CREATE TABLE PRODUCT(ID LONG AUTO_INCREMENT PRIMARY KEY,
                               PRODUCT_TYPE  VARCHAR(50) NOT NULL,
                               SUPPLIER_ID VARCHAR(200));
                               
DROP TABLE IF EXISTS PRODUCT_DETAILS;
CREATE TABLE PRODUCT_DETAILS (ID LONG AUTO_INCREMENT PRIMARY KEY,
                              COLOR  VARCHAR(50) NOT NULL,
                              SIZE   VARCHAR(20),
                              PRICE  Double               NOT NULL,
                              CURRENCY VARCHAR(50) NOT NULL, 
                              BRAND    VARCHAR(100) NOT NULL,
                              SKU_CODE      VARCHAR(100),
                              PRODUCT_ID LONG); 
                              
alter table PRODUCT_DETAILS add constraint FKcaf6ht0hfw93lwc13ny0sdmvo3 foreign key (PRODUCT_ID) references PRODUCT(id);
  DROP TABLE IF EXISTS INVENTORY;                           
 CREATE TABLE INVENTORY (id LONG AUTO_INCREMENT PRIMARY KEY,
                         TOTAL_COUNT  INT,
                         SOLD_COUNT INT,
                         AVAILABLE  INT,
                         PRODUCT_ID LONG
                         );
                         
 alter table INVENTORY add constraint FKcaf6ht0hfw93lwc13ny0sdmvo12 foreign key (PRODUCT_ID) references PRODUCT(id);
 DROP TABLE IF EXISTS SUPPLIER;
 CREATE TABLE SUPPLIER(id LONG AUTO_INCREMENT PRIMARY KEY,
                       NAME VARCHAR(100) NOT NULL,
                       CONTACT_NO  LONG,
                       ACTIVE    CHAR(1),
                       UNIQUE_ID VARCHAR(200)
                       );
                       
CREATE INDEX INDEX_UNIQUE_ID on SUPPLIER(UNIQUE_ID);   
CREATE SEQUENCE HIBERNATE_SEQUENCE START WITH 1 INCREMENT BY 1;                                                                                                