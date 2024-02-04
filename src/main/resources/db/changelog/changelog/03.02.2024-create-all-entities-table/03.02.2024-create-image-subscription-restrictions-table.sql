create table image
(
    id     uuid primary key,
    original_name   varchar not null ,
    name   varchar not null ,
    content_type varchar ,
    size varchar(MAX)
);


create table restrictions
(
    id     uuid primary key,
    name   varchar,
    description varchar,
    image_id uuid references image
);
create table subscription
(
    id     int primary key,
    price double precision,
    duration integer
);
