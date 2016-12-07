--DROP TABLE IF EXISTS customer; 

--DROP TABLE IF EXISTS customer ;


CREATE SEQUENCE if NOT EXISTS user_id_seq;
CREATE SEQUENCE if NOT EXISTS customer_id_seq;

CREATE TABLE if NOT EXISTS customer (
    id bigserial NOT NULL DEFAULT nextval('customer_id_seq'),
    firstname varchar(100) NOT NULL,
    lastname varchar(100) NOT NULL
);
ALTER SEQUENCE customer_id_seq OWNED BY customer.id;


--DROP SEQUENCE if EXISTS user_id_seq;
--DROP TABLE IF EXISTS users;

 --CREATE TABLE IF NOT EXISTS users (
--     id smallint NOT NULL DEFAULT nextval('user_id_seq'),
 --    name VARCHAR(30)  NULL,
 --- email  VARCHAR(50)  NULL,
 -- address VARCHAR(255)  NULL,
 -- password VARCHAR(20)  NULL,
--  newsletter BOOLEAN  NULL,
--  framework VARCHAR(500)  NULL,
--  sex VARCHAR(1)  NULL,
--  NUMBER INTEGER  NULL,
--  COUNTRY VARCHAR(10)  NULL,
--  SKILL VARCHAR(500)  NULL,
--  role varchar(50)
--);
--ALTER SEQUENCE user_id_seq OWNED BY users.id;



--DROP TABLE IF EXISTS users;
CREATE  TABLE IF NOT EXISTS users(
  username VARCHAR(45) NOT NULL ,
  password VARCHAR(45) NOT NULL ,
  enabled boolean NOT NULL DEFAULT true ,
  PRIMARY KEY (username));
  
--DROP TABLE IF EXISTS user_roles; 

 CREATE SEQUENCE if NOT EXISTS id_seq;
 CREATE TABLE if not EXISTS user_roles (
  user_role_id smallint NOT NULL DEFAULT nextval('id_seq'),
  username varchar(45) NOT NULL,
  role varchar(45) NOT NULL,
  PRIMARY KEY (user_role_id)
 
  );
  ALTER SEQUENCE id_seq OWNED BY user_roles.user_role_id;
  
  
  
--INSERT INTO users(username,password,enabled)
--VALUES ('user','user', true);
--INSERT INTO users(username,password,enabled)
--VALUES ('admin','admin', true);

--INSERT INTO user_roles (username, role)
--VALUES ('admin', 'ROLE_USER');
--INSERT INTO user_roles (username, role)
--VALUES ('admin', 'ROLE_ADMIN');
--INSERT INTO user_roles (username, role)
--VALUES ('user', 'ROLE_USER');


--INSERT INTO user_roles (username, role)
--VALUES ('user', 'ROLE_EMPLOYEE');











