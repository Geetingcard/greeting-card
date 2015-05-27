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
  staff_staff_id            integer,
  category_category_id      integer,
  post_date                 timestamp not null,
  constraint pk_card primary key (card_id))
;

create table category (
  category_id               integer not null,
  category_name             varchar(255) not null,
  constraint pk_category primary key (category_id))
;

create table departmant (
  departent_id              integer not null,
  department_name           varchar(255) not null,
  constraint pk_departmant primary key (departent_id))
;

create table staff (
  staff_id                  integer not null,
  department_id             integer not null,
  staff_name                varchar(255) not null,
  password                  varchar(255) not null,
  authority                 integer not null,
  depretment_departent_id   integer,
  constraint pk_staff primary key (staff_id))
;

create sequence card_seq;

create sequence category_seq;

create sequence departmant_seq;

create sequence staff_seq;

alter table card add constraint fk_card_staff_1 foreign key (staff_staff_id) references staff (staff_id) on delete restrict on update restrict;
create index ix_card_staff_1 on card (staff_staff_id);
alter table card add constraint fk_card_category_2 foreign key (category_category_id) references category (category_id) on delete restrict on update restrict;
create index ix_card_category_2 on card (category_category_id);
alter table staff add constraint fk_staff_depretment_3 foreign key (depretment_departent_id) references departmant (departent_id) on delete restrict on update restrict;
create index ix_staff_depretment_3 on staff (depretment_departent_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists card;

drop table if exists category;

drop table if exists departmant;

drop table if exists staff;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists card_seq;

drop sequence if exists category_seq;

drop sequence if exists departmant_seq;

drop sequence if exists staff_seq;

