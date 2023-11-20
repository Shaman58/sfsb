ALTER TABLE additionals
    ADD process_time numeric(21, 0);
ALTER TABLE setups
    ADD additional_comments varchar(255);