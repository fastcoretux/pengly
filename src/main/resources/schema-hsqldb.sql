SET DATABASE SQL SYNTAX ORA TRUE;
CREATE SCHEMA pengly AUTHORIZATION DBA;

create table pengly.link_alias
(
  alias       VARCHAR2(128 CHAR) not null,
  url         VARCHAR2(4000 CHAR) not null,
  used_count  NUMBER(22) not null,
  max_count   NUMBER(22),
  status      VARCHAR2(10 CHAR) DEFAULT 'ACTIVE' not null,
  primary key (alias)
);