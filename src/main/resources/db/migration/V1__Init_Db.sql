drop table if exists additionals cascade;

drop table if exists areas cascade;

drop table if exists companies cascade;

drop table if exists contacts cascade;

drop table if exists customers cascade;

drop table if exists companies_orders cascade;

drop table if exists cutters cascade;

drop table if exists departments cascade;

drop table if exists dictionary cascade;

drop table if exists employees cascade;

drop table if exists items cascade;

drop table if exists materials cascade;

drop table if exists measurers cascade;

drop table if exists orders cascade;

drop table if exists orders_items cascade;

drop table if exists setups cascade;

drop table if exists setups_additional_tools cascade;

drop table if exists setups_cutter_tools cascade;

drop table if exists setups_measure_tools cascade;

drop table if exists setups_special_tools cascade;

drop table if exists setups_toolings cascade;

drop table if exists specials cascade;

drop table if exists store_cutter_mapping cascade;

drop table if exists store_measurer_mapping cascade;

drop table if exists store_special_mapping cascade;

drop table if exists store_tooling_mapping cascade;

drop table if exists store_workpiece_mapping cascade;

drop table if exists stores cascade;

drop table if exists technologies cascade;

drop table if exists technologies_setups cascade;

drop table if exists toolings cascade;

drop table if exists units cascade;

drop table if exists workpieces cascade;

create table materials
(
    id            bigserial not null,
    created       timestamp(6),
    updated       timestamp(6),
    density       integer,
    material_name varchar(255),
    primary key (id)
);

create table stores
(
    id                 bigserial not null,
    created            timestamp(6),
    updated            timestamp(6),
    store_name         varchar(255),
    production_area_id bigint,
    primary key (id)
);

create table workpieces
(
    id             bigserial not null,
    created        timestamp(6),
    updated        timestamp(6),
    geom1          integer,
    geom2          integer,
    geom3          integer,
    geometry       varchar(255),
    material_id    bigint,
    price_amount   numeric(38, 2),
    price_currency varchar(255),
    primary key (id)
);

create table additionals
(
    id           bigserial not null,
    created      timestamp(6),
    updated      timestamp(6),
    process_time numeric(21, 0),
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

create table areas
(
    id         bigserial not null,
    created    timestamp(6),
    updated    timestamp(6),
    area_name  varchar(255),
    company_id bigint,
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

create table companies_orders
(
    company_id bigint not null,
    orders_id  bigint not null
);

create table cutters
(
    id             bigserial not null,
    created        timestamp(6),
    updated        timestamp(6),
    description    varchar(255),
    price_amount   numeric(38, 2),
    price_currency varchar(255),
    tool_name      varchar(255),
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

create table dictionary
(
    id           bigserial not null,
    db_name      varchar(255),
    presentation varchar(255),
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
    primary key (id)
);

create table orders_items
(
    order_id bigint not null,
    items_id bigint not null
);

create table setups
(
    id                  bigserial not null,
    created             timestamp(6),
    updated             timestamp(6),
    interoperative_time numeric(21, 0),
    process_time        numeric(21, 0),
    setup_name          varchar(255),
    setup_number        integer,
    setup_time          numeric(21, 0),
    production_unit_id  bigint,
    primary key (id)
);

create table setups_additional_tools
(
    setup_id            bigint not null,
    additional_tools_id bigint not null
);

create table setups_cutter_tools
(
    setup_id        bigint not null,
    cutter_tools_id bigint not null
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
    id           bigserial not null,
    created      timestamp(6),
    updated      timestamp(6),
    process_time numeric(21, 0),
    tool_name    varchar(255),
    workpiece_id bigint,
    primary key (id)
);

create table store_cutter_mapping
(
    store_id         bigint not null,
    amount           integer,
    cutter_tools_key bigint not null,
    primary key (store_id, cutter_tools_key)
);

create table store_measurer_mapping
(
    store_id          bigint not null,
    amount            integer,
    measure_tools_key bigint not null,
    primary key (store_id, measure_tools_key)
);

create table store_special_mapping
(
    store_id          bigint not null,
    amount            integer,
    special_tools_key bigint not null,
    primary key (store_id, special_tools_key)
);

create table store_tooling_mapping
(
    store_id     bigint not null,
    amount       integer,
    toolings_key bigint not null,
    primary key (store_id, toolings_key)
);

create table store_workpiece_mapping
(
    store_id       bigint not null,
    amount         integer,
    workpieces_key bigint not null,
    primary key (store_id, workpieces_key)
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
    primary key (id)
);

create table technologies_setups
(
    technology_id bigint not null,
    setups_id     bigint not null
);

create table toolings
(
    id             bigserial not null,
    created        timestamp(6),
    updated        timestamp(6),
    description    varchar(255),
    price_amount   numeric(38, 2),
    price_currency varchar(255),
    tooling_name   varchar(255),
    primary key (id)
);

create table units
(
    id                 bigserial not null,
    created            timestamp(6),
    updated            timestamp(6),
    price_amount       numeric(38, 2),
    price_currency     varchar(255),
    payment_amount     numeric(38, 2),
    payment_currency   varchar(255),
    unit_name          varchar(255),
    unit_number        integer,
    production_area_id bigint,
    primary key (id)
);

alter table if exists units
    add constraint FK_production_area_id
        foreign key (production_area_id)
            references areas;

alter table if exists employees
    add constraint FK_department_id
        foreign key (department_id)
            references departments;

alter table if exists companies
    add constraint FK_director_id
        foreign key (director_id)
            references employees;

alter table if exists technologies_setups
    add constraint UK_technologies_setups unique (setups_id);

alter table if exists technologies_setups
    add constraint FK_setups_id
        foreign key (setups_id)
            references setups;

alter table if exists technologies_setups
    add constraint FK_technology_id
        foreign key (technology_id)
            references technologies;

alter table if exists companies_orders
    add constraint UK_companies_orders unique (orders_id);

alter table if exists companies_orders
    add constraint FK_orders_id
        foreign key (orders_id)
            references orders;

alter table if exists companies_orders
    add constraint FK_company_id
        foreign key (company_id)
            references companies;

alter table if exists items
    add constraint FK_technology_id
        foreign key (technology_id)
            references technologies;

alter table if exists workpieces
    add constraint FK_material_id
        foreign key (material_id)
            references materials;

alter table if exists stores
    add constraint FK_production_area_id
        foreign key (production_area_id)
            references areas;

alter table if exists areas
    add constraint FK_company_id
        foreign key (company_id)
            references companies;

alter table if exists orders_items
    add constraint FK_items_id
        foreign key (items_id)
            references items;

alter table if exists orders_items
    add constraint FK_order_id
        foreign key (order_id)
            references orders;

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

alter table if exists setups_cutter_tools
    add constraint FK_cutter_tools_id
        foreign key (cutter_tools_id)
            references cutters;

alter table if exists setups_cutter_tools
    add constraint FK_setup_id
        foreign key (setup_id)
            references setups;

alter table if exists specials
    add constraint FK_workpiece_id
        foreign key (workpiece_id)
            references workpieces;

alter table if exists contacts
    add constraint FK_contact_customer_id
        foreign key (customer_id)
            references customers;

alter table if exists store_tooling_mapping
    add constraint FK_toolings_key
        foreign key (toolings_key)
            references toolings;

alter table if exists store_tooling_mapping
    add constraint FK_store_id
        foreign key (store_id)
            references stores;

alter table if exists store_cutter_mapping
    add constraint FK_cutter_tools_key
        foreign key (cutter_tools_key)
            references cutters;

alter table if exists store_cutter_mapping
    add constraint FK_store_id
        foreign key (store_id)
            references stores;

alter table if exists store_workpiece_mapping
    add constraint FK_workpieces_key
        foreign key (workpieces_key)
            references workpieces;

alter table if exists store_workpiece_mapping
    add constraint FK_store_id
        foreign key (store_id)
            references stores;

alter table if exists store_special_mapping
    add constraint FK_special_tools_key
        foreign key (special_tools_key)
            references specials;

alter table if exists store_special_mapping
    add constraint FK_store_id
        foreign key (store_id)
            references stores;

alter table if exists store_measurer_mapping
    add constraint FK_measure_tools_key
        foreign key (measure_tools_key)
            references measurers;

alter table if exists store_measurer_mapping
    add constraint FK_store_id
        foreign key (store_id)
            references stores;