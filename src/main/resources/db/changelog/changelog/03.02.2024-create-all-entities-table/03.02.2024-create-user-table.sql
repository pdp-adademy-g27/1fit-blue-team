create table users
(
    id              uuid primary key,
    name            varchar   not null,
    surname         varchar   not null,
    phone_number    varchar   not null unique,
    email           varchar unique,
    password        varchar   not null,
    date_of_birth   timestamp,
    gender          varchar   not null,
    created         timestamp not null,
    updated         timestamp not null,
    image_id        uuid references image,
    subscription_id int references subscription,
    activation_date timestamp

)