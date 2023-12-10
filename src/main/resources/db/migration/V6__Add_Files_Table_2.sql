ALTER TABLE files DROP CONSTRAINT unique_order_filename;
ALTER TABLE files
    ADD link    varchar(255),
    ADD id      bigserial not null,
    ADD created timestamp(6),
    ADD updated timestamp(6);
ALTER TABLE files ADD CONSTRAINT pk_files_id PRIMARY KEY (id);
