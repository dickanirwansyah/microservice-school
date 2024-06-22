create database db_msig_student;
create table tbl_student(
    id varchar not null unique,
    fullname varchar(255) not null,
    email varchar(100) not null unique,
    phone_no varchar(20) not null unique,
    address varchar(100) not null,
    dob timestamp without zone,
    deleted int,
    avatar text,
    gender varchar(20) not null,
    primary key (id)
);

alter table tbl_student add roles_id varchar(100);
alter table tbl_student add roles_name varchar(100);