create database db_msig_backoffice;
create table tbl_roles(
    id varchar(100) not null unique,
    name varchar(100) not null unique,
    deleted int,
    primary key(id)
);

insert into tbl_roles(id, name, deleted) values ('R001', 'Admin',0);
insert into tbl_roles(id, name, deleted) values ('R002', 'Finance', 0);
insert into tbl_roles(id, name, deleted) values ('R003', 'Siswa', 0);


create table tbl_accounts(
    id varchar(100) not null unique,
    fullname varchar(255),
    roles_id varchar(100),
    email varchar(200),
    password varchar(255),
    dob timestamp,
    deleted int,
    primary key (id)
);
