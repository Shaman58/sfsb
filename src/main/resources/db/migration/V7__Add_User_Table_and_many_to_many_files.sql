CREATE TABLE users
(
    id      bigint       not null,
    uuid    varchar(255) not null,
    created timestamp(6),
    updated timestamp(6),
    file_id bigint       not null,
    primary key (id),
    foreign key (file_id)
        references files
);

ALTER TABLE files
    DROP CONSTRAINT files_order_id_fkey;
ALTER TABLE files
    DROP order_id;

create table order_files
(
    order_id bigint not null,
    file_id  bigint not null
);

alter table if exists order_files
    add constraint FK_order_id
        foreign key (order_id)
            references orders
            on delete cascade;

alter table if exists order_files
    add constraint FK_file_id
        foreign key (file_id)
            references files;