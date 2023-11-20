ALTER TABLE materials
    RENAME COLUMN gost TO gost_1;
ALTER TABLE materials
    ADD gost_2 varchar(255);