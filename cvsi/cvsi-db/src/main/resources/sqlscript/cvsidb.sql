call PROC_DROP_FOREIGN_KEY('conversation','FK_product_id_conversation');
call PROC_DROP_FOREIGN_KEY('conversation','FK_user_id_conversation');
call PROC_DROP_FOREIGN_KEY('image','FK_product_id_image');
call PROC_DROP_FOREIGN_KEY('message','FK_conversation_id');
call PROC_DROP_FOREIGN_KEY('product','FK_user_id_product');
call PROC_DROP_FOREIGN_KEY('product_category','FK_product_id_category');
call PROC_DROP_FOREIGN_KEY('user_role','FK_user_id_role');

drop table if exists a_table;
drop table if exists conversation;
drop table if exists forgot_password;
drop table if exists image;
drop table if exists message;
drop table if exists product;
drop table if exists product_category;
drop table if exists registration;
drop table if exists user_information;
drop table if exists user_role;

create table a_table (id bigint not null auto_increment, age integer not null, create_time datetime not null, first_name varchar(255) not null, last_name varchar(255) not null, primary key (id));
create table conversation (id bigint not null auto_increment, created_date datetime not null, product_id_conversation bigint, user_id_conversation bigint, primary key (id));
create table forgot_password (id bigint not null auto_increment, email varchar(255) not null, hash varchar(255) not null, request_created_date datetime not null, primary key (id));
create table image (id bigint not null auto_increment, created_date datetime not null, image longblob, image_type varchar(20) not null, product_id_image bigint, primary key (id));
create table message (id bigint not null auto_increment, created_date datetime not null, is_Read bit, message varchar(255) not null, updated_date datetime, conversation_id bigint, primary key (id));
create table product (id bigint not null auto_increment, created_date datetime not null, currency integer, description varchar(255), is_archived bit, borrow_or_lend bit, limit_date datetime, price bigint, title varchar(100) not null, updated_date datetime, user_id_product bigint, primary key (id));
create table product_category (product_id bigint not null, category_enum_list varchar(255));
create table registration (id bigint not null auto_increment, email varchar(255) not null, hash varchar(255) not null, name varchar(30), password varchar(255) not null, phone varchar(25) not null, request_created_date datetime not null, surname varchar(30), username varchar(30) not null, primary key (id));
create table user_information (id bigint not null auto_increment, created_date datetime not null, email varchar(255) not null, is_online bit, name varchar(30), password varchar(255) not null, phone varchar(25) not null, surname varchar(30), updated_date datetime, username varchar(30) not null, primary key (id));
create table user_role (user_id bigint not null, role_enum_list varchar(255));

alter table registration add constraint UK_email_registration unique (email);
alter table registration add constraint UK_username_registration unique (username);
alter table user_information add constraint UK_email unique (email);
alter table user_information add constraint UK_username unique (username);
alter table conversation add constraint FK_product_id_conversation foreign key (product_id_conversation) references product (id);
alter table conversation add constraint FK_user_id_conversation foreign key (user_id_conversation) references user_information (id);
alter table image add constraint FK_product_id_image foreign key (product_id_image) references product (id);
alter table message add constraint FK_conversation_id foreign key (conversation_id) references conversation (id);
alter table product add constraint FK_user_id_product foreign key (user_id_product) references user_information (id);
alter table product_category add constraint FK_product_id_category foreign key (product_id) references product (id);
alter table user_role add constraint FK_user_id_role foreign key (user_id) references user_information (id);