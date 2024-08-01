alter table if exists orders
    add constraint FK_app_number unique (application_number);