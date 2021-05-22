CREATE TABLE company
(
    id   integer NOT NULL,
    name character varying,
    CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE TABLE person
(
    id         integer NOT NULL,
    name       character varying,
    company_id integer,
    CONSTRAINT person_pkey PRIMARY KEY (id)
);


-- 1. В одном запросе получить
-- - имена всех person, которые не состоят в компании с id = 5;
-- - название компании для каждого человека.
select p.name, c.name
from person p
         inner join company c on c.id = p.company_id
    and c.id <> 5
;

-- 2. Необходимо выбрать название компании с максимальным количеством человек
-- + количество человек в этой компании.
select c.name, count(p.id)
from company c
         join person p on c.id = p.company_id
group by c.name
order by count(p.id) desc
limit 1