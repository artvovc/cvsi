CALL PROC_DROP_FOREIGN_KEY('conversation', 'FK_product_id_conversation');
CALL PROC_DROP_FOREIGN_KEY('conversation', 'FK_user_id_conversation');
CALL PROC_DROP_FOREIGN_KEY('image', 'FK_product_id_image');
CALL PROC_DROP_FOREIGN_KEY('message', 'FK_conversation_id');
CALL PROC_DROP_FOREIGN_KEY('product', 'FK_user_id_product');
CALL PROC_DROP_FOREIGN_KEY('product_category', 'FK_product_id_category');
CALL PROC_DROP_FOREIGN_KEY('user_role', 'FK_user_id_role');

DROP TABLE IF EXISTS a_table;
DROP TABLE IF EXISTS conversation;
DROP TABLE IF EXISTS forgot_password;
DROP TABLE IF EXISTS image;
DROP TABLE IF EXISTS message;
DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS product_category;
DROP TABLE IF EXISTS registration;
DROP TABLE IF EXISTS user_information;
DROP TABLE IF EXISTS user_role;

CREATE TABLE a_table (
  id          BIGINT       NOT NULL AUTO_INCREMENT,
  age         INTEGER      NOT NULL,
  create_time DATETIME     NOT NULL,
  first_name  VARCHAR(255) NOT NULL,
  last_name   VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
);
CREATE TABLE conversation (
  id                      BIGINT   NOT NULL AUTO_INCREMENT,
  created_date            DATETIME NOT NULL,
  product_id_conversation BIGINT,
  user_id_conversation    BIGINT,
  PRIMARY KEY (id)
);
CREATE TABLE forgot_password (
  id                   BIGINT       NOT NULL AUTO_INCREMENT,
  email                VARCHAR(255) NOT NULL,
  hash                 VARCHAR(255) NOT NULL,
  request_created_date DATETIME     NOT NULL,
  PRIMARY KEY (id)
);
CREATE TABLE image (
  id               BIGINT       NOT NULL AUTO_INCREMENT,
  created_date     DATETIME     NOT NULL,
  image            VARCHAR(255) NOT NULL,
  image_type       VARCHAR(255) NOT NULL,
  product_id_image BIGINT,
  PRIMARY KEY (id)
);
CREATE TABLE message (
  id              BIGINT       NOT NULL AUTO_INCREMENT,
  created_date    DATETIME     NOT NULL,
  is_Read         BIT,
  message         VARCHAR(255) NOT NULL,
  updated_date    DATETIME,
  conversation_id BIGINT,
  PRIMARY KEY (id)
);
CREATE TABLE product (
  id              BIGINT       NOT NULL AUTO_INCREMENT,
  created_date    DATETIME     NOT NULL,
  currency        VARCHAR(255) NOT NULL,
  description     VARCHAR(255),
  is_archived     BIT,
  borrow_or_lend  BIT,
  limit_date      DATETIME,
  price           BIGINT,
  title           VARCHAR(100) NOT NULL,
  updated_date    DATETIME,
  user_id_product BIGINT,
  PRIMARY KEY (id)
);
CREATE TABLE product_category (
  product_id        BIGINT NOT NULL,
  category_enum_set VARCHAR(255)
);
CREATE TABLE registration (
  id                   BIGINT       NOT NULL AUTO_INCREMENT,
  email                VARCHAR(255) NOT NULL,
  hash                 VARCHAR(255) NOT NULL,
  name                 VARCHAR(30),
  password             VARCHAR(255) NOT NULL,
  phone                VARCHAR(25)  NOT NULL,
  request_created_date DATETIME     NOT NULL,
  surname              VARCHAR(30),
  username             VARCHAR(30)  NOT NULL,
  PRIMARY KEY (id)
);
CREATE TABLE user_information (
  id           BIGINT       NOT NULL AUTO_INCREMENT,
  created_date DATETIME     NOT NULL,
  email        VARCHAR(255) NOT NULL,
  is_archived  BIT,
  is_online    BIT,
  name         VARCHAR(30),
  password     VARCHAR(255) NOT NULL,
  phone        VARCHAR(25)  NOT NULL,
  surname      VARCHAR(30),
  image        VARCHAR(255),
  updated_date DATETIME,
  username     VARCHAR(30)  NOT NULL,
  PRIMARY KEY (id)
);
CREATE TABLE user_role (
  user_id       BIGINT NOT NULL,
  role_enum_set VARCHAR(255)
);

ALTER TABLE registration
  ADD CONSTRAINT UK_email_registration UNIQUE (email);
ALTER TABLE registration
  ADD CONSTRAINT UK_username_registration UNIQUE (username);
ALTER TABLE user_information
  ADD CONSTRAINT UK_email UNIQUE (email);
ALTER TABLE user_information
  ADD CONSTRAINT UK_username UNIQUE (username);
ALTER TABLE conversation
  ADD CONSTRAINT FK_product_id_conversation FOREIGN KEY (product_id_conversation) REFERENCES product (id);
ALTER TABLE conversation
  ADD CONSTRAINT FK_user_id_conversation FOREIGN KEY (user_id_conversation) REFERENCES user_information (id);
ALTER TABLE image
  ADD CONSTRAINT FK_product_id_image FOREIGN KEY (product_id_image) REFERENCES product (id);
ALTER TABLE message
  ADD CONSTRAINT FK_conversation_id FOREIGN KEY (conversation_id) REFERENCES conversation (id);
ALTER TABLE product
  ADD CONSTRAINT FK_user_id_product FOREIGN KEY (user_id_product) REFERENCES user_information (id);
ALTER TABLE product_category
  ADD CONSTRAINT FK_product_id_category FOREIGN KEY (product_id) REFERENCES product (id);
ALTER TABLE user_role
  ADD CONSTRAINT FK_user_id_role FOREIGN KEY (user_id) REFERENCES user_information (id);