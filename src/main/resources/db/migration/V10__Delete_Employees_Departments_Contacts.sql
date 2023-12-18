alter table companies
    drop constraint fk_director_id;
alter table orders
    drop constraint fk_contact_id;
alter table orders
    drop constraint fk_order_employee_id;
alter table orders
    add user_uuid varchar(255);
alter table technologies
    drop constraint fk_employee_id;
alter table technologies
    add user_uuid varchar(255);
DROP TABLE IF EXISTS employees;
DROP TABLE IF EXISTS departments;
DROP TABLE IF EXISTS contacts;
