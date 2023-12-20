alter table companies
    add company_type varchar(255);
insert into companies(created, updated, address, bank, bik, company_name, correspondent_account, email, inn, kpp, ogrn,
                      payment_account, phone_number, company_type)
select created,
       updated,
       address,
       bank,
       bik,
       company_name,
       correspondent_account,
       email,
       inn,
       kpp,
       ogrn,
       payment_account,
       phone_number,
       'customer'
from customers;
alter table orders
    drop constraint fk_customer_id;
alter table orders
    add constraint fk_customer_id
        foreign key (customer_id)
            references companies;
drop table customers;