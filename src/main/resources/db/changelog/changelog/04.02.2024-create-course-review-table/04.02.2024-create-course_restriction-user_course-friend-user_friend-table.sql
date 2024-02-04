create table course_restrictions
(
    course_id       uuid references course,
    restrictions_id uuid references restrictions,
    primary key (course_id, restrictions_id)
);
create table user_course
(
    user_id   uuid references users,
    course_id uuid references course,
    primary key (user_id,course_id)
);
create table friend
(
    id uuid primary key
);

create table user_friend
(
    user_id   uuid references users,
    friend_id uuid references friend,
    primary key (user_id,friend_id)
);
