# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table account (
  id                        bigint not null,
  name                      varchar(255) not null,
  create_date               timestamp not null,
  update_date               timestamp not null,
  constraint pk_account primary key (id))
;

create table sentence (
  id                        bigint not null,
  name                      varchar(255) not null,
  create_date               timestamp not null,
  update_date               timestamp not null,
  constraint pk_sentence primary key (id))
;

create sequence account_seq;

create sequence sentence_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists account;

drop table if exists sentence;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists account_seq;

drop sequence if exists sentence_seq;

