drop table if exists additionals cascade;

drop table if exists companies cascade;

drop table if exists contacts cascade;

drop table if exists customers cascade;

drop table if exists departments cascade;

drop table if exists employees cascade;

drop table if exists items cascade;

drop table if exists materials cascade;

drop table if exists measurers cascade;

drop table if exists orders cascade;

drop table if exists setups cascade;

drop table if exists setups_additional_tools cascade;

drop table if exists setups_measure_tools cascade;

drop table if exists setups_special_tools cascade;

drop table if exists setups_toolings cascade;

drop table if exists specials cascade;

drop table if exists technologies cascade;

drop table if exists toolings cascade;

drop table if exists units cascade;

drop table if exists workpieces cascade;

drop table if exists operations cascade;

create table material_density_templates
(
    id                 bigserial not null,
    created            timestamp(6),
    updated            timestamp(6),
    material_type_name varchar(255) UNIQUE,
    density            integer,
    primary key (id)
);

create table materials
(
    id             bigserial not null,
    created        timestamp(6),
    updated        timestamp(6),
    gost           varchar(255) UNIQUE,
    geometry       varchar(255),
    material_name  varchar(255),
    price_amount   numeric(38, 2),
    price_currency varchar(255),
    density        integer,
    primary key (id)
);

create table workpieces
(
    id          bigserial not null,
    created     timestamp(6),
    updated     timestamp(6),
    geom1       integer,
    geom2       integer,
    geom3       integer,
    material_id bigint,
    primary key (id)
);

create table additionals
(
    id           bigserial not null,
    created      timestamp(6),
    updated      timestamp(6),
    tool_name    varchar(255),
    workpiece_id bigint,
    primary key (id)
);

create table companies
(
    id                    bigserial not null,
    created               timestamp(6),
    updated               timestamp(6),
    address               varchar(255),
    bank                  varchar(255),
    bik                   varchar(255),
    company_name          varchar(255),
    correspondent_account varchar(255),
    email                 varchar(255),
    inn                   varchar(255),
    kpp                   varchar(255),
    ogrn                  varchar(255),
    payment_account       varchar(255),
    phone_number          varchar(255),
    director_id           bigint,
    primary key (id)
);

create table employees
(
    id            bigserial not null,
    created       timestamp(6),
    updated       timestamp(6),
    first_name    varchar(255),
    last_name     varchar(255),
    phone_number  varchar(255),
    email         varchar(255),
    position      varchar(255),
    department_id bigint,
    primary key (id)
);

create table customers
(
    id                    bigserial not null,
    created               timestamp(6),
    updated               timestamp(6),
    address               varchar(255),
    bank                  varchar(255),
    bik                   varchar(255),
    company_name          varchar(255),
    correspondent_account varchar(255),
    email                 varchar(255),
    inn                   varchar(255),
    kpp                   varchar(255),
    ogrn                  varchar(255),
    payment_account       varchar(255),
    phone_number          varchar(255),
    primary key (id)
);

create table departments
(
    id              bigserial not null,
    created         timestamp(6),
    updated         timestamp(6),
    department_name varchar(255),
    company_id      bigint,
    primary key (id)
);

create table contacts
(
    id           bigserial not null,
    created      timestamp(6),
    updated      timestamp(6),
    first_name   varchar(255),
    last_name    varchar(255),
    phone_number varchar(255),
    email        varchar(255),
    position     varchar(255),
    customer_id  bigint,
    primary key (id)
);

create table items
(
    id                   bigserial not null,
    created              timestamp(6),
    updated              timestamp(6),
    actual_duration      numeric(21, 0),
    estimated_duration   numeric(21, 0),
    is_accepted          boolean   not null,
    is_customer_material boolean   not null,
    quantity             integer,
    technology_id        bigint,
    order_id             bigint,
    price_amount         numeric(38, 2),
    price_currency       varchar(255),
    primary key (id)
);

create table measurers
(
    id             bigserial not null,
    created        timestamp(6),
    updated        timestamp(6),
    description    varchar(255),
    price_amount   numeric(38, 2),
    price_currency varchar(255),
    tool_name      varchar(255),
    amount         integer,
    primary key (id)
);

create table orders
(
    id                 bigserial not null,
    created            timestamp(6),
    updated            timestamp(6),
    application_number integer,
    business_proposal  varchar(2047),
    description        varchar(255),
    customer_id        bigint,
    employee_id        bigint,
    contact_id         bigint,
    company_id         bigint,
    primary key (id)
);

create table setups
(
    id                  bigserial not null,
    created             timestamp(6),
    updated             timestamp(6),
    interoperative_time numeric(21, 0),
    process_time        numeric(21, 0),
    setup_number        integer,
    setup_time          numeric(21, 0),
    production_unit_id  bigint,
    technology_id       bigint,
    operation_id        bigint,
    is_group            boolean   not null,
    per_Time            integer,
    primary key (id)
);

create table setups_additional_tools
(
    setup_id            bigint not null,
    additional_tools_id bigint not null
);

create table setups_measure_tools
(
    setup_id         bigint not null,
    measure_tools_id bigint not null
);

create table setups_special_tools
(
    setup_id         bigint not null,
    special_tools_id bigint not null
);

create table setups_toolings
(
    setup_id    bigint not null,
    toolings_id bigint not null
);

create table specials
(
    id             bigserial not null,
    created        timestamp(6),
    updated        timestamp(6),
    description    varchar(255),
    price_amount   numeric(38, 2),
    price_currency varchar(255),
    tool_name      varchar(255),
    amount         integer,
    primary key (id)
);

create table technologies
(
    id                               bigserial not null,
    created                          timestamp(6),
    updated                          timestamp(6),
    drawing_name                     varchar(255),
    drawing_number                   varchar(255),
    quantity_of_defective_parts      integer,
    quantity_of_parts_from_workpiece integer,
    quantity_of_set_up_parts         integer,
    employee_id                      bigint,
    workpiece_id                     bigint,
    is_computed                      boolean,
    primary key (id)
);

create table toolings
(
    id             bigserial not null,
    created        timestamp(6),
    updated        timestamp(6),
    description    varchar(255),
    price_amount   numeric(38, 2),
    price_currency varchar(255),
    tool_name      varchar(255),
    amount         integer,
    primary key (id)
);

create table units
(
    id               bigserial not null,
    created          timestamp(6),
    updated          timestamp(6),
    payment_amount   numeric(38, 2),
    payment_currency varchar(255),
    unit_name        varchar(255),
    company_id       bigint,
    unit_number      integer,
    primary key (id)
);

create table operations
(
    id               bigserial not null,
    created          timestamp(6),
    updated          timestamp(6),
    payment_amount   numeric(38, 2),
    payment_currency varchar(255),
    operation_name   varchar(255),
    operation_type   varchar(255),
    primary key (id)
);

alter table if exists employees
    add constraint FK_department_id
        foreign key (department_id)
            references departments;

alter table if exists companies
    add constraint FK_director_id
        foreign key (director_id)
            references employees;

alter table if exists setups
    add constraint FK_technology_id
        foreign key (technology_id)
            references technologies;

alter table if exists setups
    add constraint FK_operation_id
        foreign key (operation_id)
            references operations;

alter table if exists orders
    add constraint FK_company_id
        foreign key (company_id)
            references companies;

alter table if exists items
    add constraint FK_technology_id
        foreign key (technology_id)
            references technologies;

alter table if exists items
    add constraint FK_order_id
        foreign key (order_id)
            references orders;

alter table if exists workpieces
    add constraint FK_material_id
        foreign key (material_id)
            references materials;

alter table if exists departments
    add constraint FK_company_id
        foreign key (company_id)
            references companies;

alter table if exists orders
    add constraint FK_customer_id
        foreign key (customer_id)
            references customers;

alter table if exists orders
    add constraint FK_order_employee_id
        foreign key (employee_id)
            references employees;

alter table if exists orders
    add constraint FK_contact_id
        foreign key (contact_id)
            references contacts;

alter table if exists units
    add constraint FK_company_id
        foreign key (company_id)
            references companies;

alter table if exists setups
    add constraint FK_production_unit_id
        foreign key (production_unit_id)
            references units;

alter table if exists setups_toolings
    add constraint FK_toolings_id
        foreign key (toolings_id)
            references toolings;

alter table if exists setups_toolings
    add constraint FK_setup_id
        foreign key (setup_id)
            references setups;

alter table if exists setups_measure_tools
    add constraint FK_measure_tools_id
        foreign key (measure_tools_id)
            references measurers;

alter table if exists setups_measure_tools
    add constraint FK_setup_id
        foreign key (setup_id)
            references setups;

alter table if exists setups_special_tools
    add constraint FK_special_tools_id
        foreign key (special_tools_id)
            references specials;

alter table if exists setups_special_tools
    add constraint FK_setup_id
        foreign key (setup_id)
            references setups;

alter table if exists technologies
    add constraint FK_employee_id
        foreign key (employee_id)
            references employees;

alter table if exists technologies
    add constraint FK_workpiece_id
        foreign key (workpiece_id)
            references workpieces;

alter table if exists setups_additional_tools
    add constraint FK_additional_tools_id
        foreign key (additional_tools_id)
            references additionals;

alter table if exists setups_additional_tools
    add constraint FK_setup_id
        foreign key (setup_id)
            references setups;

alter table if exists additionals
    add constraint FK_workpiece_id
        foreign key (workpiece_id)
            references workpieces;

alter table if exists contacts
    add constraint FK_contact_customer_id
        foreign key (customer_id)
            references customers;