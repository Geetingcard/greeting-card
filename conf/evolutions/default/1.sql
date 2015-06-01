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
  post_date                 timestamp not null,
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
  staff_code                integer not null,
  department_id             integer not null,
  staff_name                varchar(255) not null,
  password                  varchar(255) not null,
  authority                 integer not null,
  constraint pk_staff primary key (staff_id))
;

create sequence card_seq;

create sequence category_seq;

create sequence department_seq;

create sequence staff_seq;

alter table card add constraint fk_card_get_staff_1 foreign key (get_staff_id) references staff (staff_id) on delete restrict on update restrict;
create index ix_card_get_staff_1 on card (get_staff_id);
alter table card add constraint fk_card_send_staff_2 foreign key (send_staff_id) references staff (staff_id) on delete restrict on update restrict;
create index ix_card_send_staff_2 on card (send_staff_id);
alter table card add constraint fk_card_category_id_3 foreign key (category_id) references category (category_id) on delete restrict on update restrict;
create index ix_card_category_id_3 on card (category_id);
alter table staff add constraint fk_staff_department_4 foreign key (department_id) references department (department_id) on delete restrict on update restrict;
create index ix_staff_department_4 on staff (department_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists card;

drop table if exists category;

drop table if exists department;

drop table if exists staff;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists card_seq;

drop sequence if exists category_seq;

drop sequence if exists department_seq;

drop sequence if exists staff_seq;

