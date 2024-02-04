create table attendance(
    date timestamp,
    user_id uuid references users,
    course_id uuid references corse
);
