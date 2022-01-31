create table if not exists trainers
(
    id         bigint auto_increment,
    global_id  varchar(50) not null,
    email      varchar(320) not null,
    phone      varchar(50)  not null,
    first_name varchar(255) not null,
    last_name  varchar(255) not null,
    constraint trainers_pk
        primary key (id)
);

create unique index trainers_global_id_uidx on trainers (global_id);