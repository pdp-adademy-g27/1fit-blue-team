create table role
(
    id     uuid primary key,
    name   varchar not null unique,
    gym_id uuid references gym
);
create table permission
(
    id   uuid primary key,
    name varchar not null unique
);

create table role_permission
(
    role_id       uuid references role,
    permission_id uuid references permission,
    primary key (role_id, permission_id)
);
create table user_role
(
    user_id   uuid references users,
    role_id uuid references role,
    primary key (user_id,role_id)
);