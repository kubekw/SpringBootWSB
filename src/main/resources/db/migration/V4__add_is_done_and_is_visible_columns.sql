alter table task add column is_done boolean not null;
alter table task add column is_visible boolean not null default true;