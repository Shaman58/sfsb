alter table companies
    add logo_id bigint;
alter table companies
    add constraint fk_logo_id
        foreign key (logo_id)
            references files;