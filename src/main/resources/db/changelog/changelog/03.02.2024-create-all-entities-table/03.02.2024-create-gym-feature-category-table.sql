create table gym
(
    id     uuid primary key,
    name   varchar not null ,
    address varchar not null ,
    description varchar,
    image_id uuid references image
);

create table feature
(
    id     uuid primary key,
    name   varchar not null ,
    description varchar,
    image_id uuid references image
);
create table category
(
    id     uuid primary key,
    name   varchar not null ,
    parent_id uuid references category,
    image_id uuid references image
);

create table gym_features
(
    gym_id       uuid references gym,
    feature_id uuid references feature,
    primary key (gym_id, feature_id)
);
create table gym_category
(
    gym_id   uuid references gym,
    category_id uuid references category,
    primary key (gym_id,category_id)
);