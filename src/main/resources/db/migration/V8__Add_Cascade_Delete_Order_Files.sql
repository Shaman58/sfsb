alter table order_files
    drop constraint fk_file_id;
alter table order_files
    add constraint fk_file_id
        foreign key (file_id)
            references files
            on delete cascade;