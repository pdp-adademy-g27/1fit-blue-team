create table course
(
    id     uuid primary key,
    total_visits   Integer ,
    name varchar not null ,
    description varchar,
    start_date timestamp,
    duration_time int,
    course_type varchar,
    contact_phone varchar,
    created timestamp,
    trainer_id uuid references users,
    image_id uuid references image,
    category_id uuid references category,
    gym_id uuid references gym,
);
 create table review
(
    id     uuid primary key,
    cleanliness   double precision,
    staff   double precision,
    equipment   double precision,
    service   double precision,
    commit varchar,
    is_changed BOOLEAN,
    publishedAt timestamp,
    author_id uuid references users,
    course_id uuid references course,
);



