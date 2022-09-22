
create table post
(
    id                      bigint auto_increment primary key,

    user                    bigint not null,
    title                   varchar(30) not null,
    description             varchar(250) not null,
    geolocation             json ,
    tags                    varchar(30),
    upvote                  int ,
    downvote                int

)   ;