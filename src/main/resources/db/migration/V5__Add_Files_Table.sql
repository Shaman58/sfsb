CREATE TABLE files
(
    order_id bigint       not null,
    filename varchar(255) not null,
    constraint unique_order_filename unique (order_id, filename),
    foreign key (order_id) references orders (id)
);