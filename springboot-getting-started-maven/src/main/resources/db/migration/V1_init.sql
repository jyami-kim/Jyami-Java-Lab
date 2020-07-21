drop table if exists hibernate_sequence;
drop table if exists user;
create table hibernate_sequence (next_val bigint) engine=InnoDB;
insert into hibernate_sequence values ( 1 );
create table user (id bigint not null, password varchar(255), username varchar(255), primary key (id)) engine=InnoDB
