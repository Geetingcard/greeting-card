# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table card (
  card_id                   integer not null,
  get_staff_id              integer not null,
  send_staff_id             integer not null,
  point                     integer not null,
  help_detail               varchar(255),
  thanks_word               varchar(255),
  helped_date               timestamp not null,
  category_id               integer not null,
  constraint pk_card primary key (card_id))
;

create table category (
  category_id               integer not null,
  category_name             varchar(255) not null,
  constraint pk_category primary key (category_id))
;

create table department (
  department_id             integer not null,
  department_name           varchar(255) not null,
  constraint pk_department primary key (department_id))
;

create table staff (
  staff_id                  integer not null,
  department_id             integer not null,
  staff_name                varchar(255) not null,
  password                  varchar(255) not null,
  authority                 integer not null,
  constraint pk_staff primary key (staff_id))
;

create table user (
  id                        bigint not null,
  name                      varchar(255),
  password                  varchar(255),
  constraint pk_user primary key (id))
;

create sequence card_seq;

create sequence category_seq;

create sequence department_seq;

create sequence staff_seq;

create sequence user_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists card;

drop table if exists category;

drop table if exists department;

drop table if exists staff;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists card_seq;

drop sequence if exists category_seq;

drop sequence if exists department_seq;

drop sequence if exists staff_seq;

drop sequence if exists user_seq;

