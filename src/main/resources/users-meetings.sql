CREATE TABLE if not exists meetings
(
    id   serial primary key,
    name text
);

CREATE TABLE if not exists users
(
    id   serial primary key,
    name text
);

DO
$$
    BEGIN
        IF NOT EXISTS(SELECT 1 FROM pg_type WHERE typname = 'meeting_status') THEN
            CREATE TYPE meeting_status AS ENUM ('accept', 'reject', 'waiting');
        END IF;
    END
$$;


CREATE TABLE if not exists users_meetings
(
    user_id        int            not NULL REFERENCES users,
    meeting_id     int            not NULL REFERENCES meetings,
    meeting_status meeting_status not NULL,
    PRIMARY key (user_id, meeting_id, meeting_status)
);

insert into users
values (1, 'user1'),
       (2, 'user2'),
       (3, 'user3')
ON CONFLICT DO NOTHING;

insert into meetings
values (1, 'meeting1'),
       (2, 'meeting2'),
       (3, 'meeting3'),
       (4, 'meeting4')
ON CONFLICT DO NOTHING;

insert into users_meetings
values (1, 1, 'accept'),
       (2, 1, 'accept'),
       (3, 1, 'reject'),
       (1, 2, 'reject'),
       (3, 2, 'waiting'),
       (1, 3, 'reject'),
       (2, 3, 'reject'),
       (3, 1, 'waiting')
ON CONFLICT DO NOTHING;

-- 2. Нужно написать запрос, который получит список всех заяков и количество подтвердивших участников.
select m.name, count(u.id)
from users_meetings um
         join meetings m on m.id = um.meeting_id
         join users u on u.id = um.user_id
where meeting_status = 'accept'
group by m.name;

-- 3. Нужно получить все совещания, где не было ни одной заявки на посещения

-- собираем совещания, по которым вообще не было приглашений
select distinct m.name
from meetings m
         left join users_meetings um on m.id = um.meeting_id
where um.meeting_status IS NULL
-- собираем заявки, которым не было подтверждений (но были другие статусы)
union
select m.name
from meetings m
where id not in (select distinct um.meeting_id from users_meetings um where meeting_status = 'accept')
order by 1

