


create table user
(
    id                      bigint auto_increment primary key,

    username                varchar(36) not null,
    handle                  varchar(30) not null,
    email                   varchar(50) not null,

    surname                 varchar(30) not null,
    country                 varchar(30),
    city                    varchar(30)

)   ;