drop table if exists additionals cascade;
drop table if exists areas cascade;
drop table if exists areas_production_units cascade;
drop table if exists companies cascade;
drop table if exists companies_departments cascade;
drop table if exists companies_orders cascade;
drop table if exists companies_production_areas cascade;
drop table if exists cutters cascade;
drop table if exists departments cascade;
drop table if exists departments_employees cascade;
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
drop table if exists specials cascade;
drop table if exists store_cutter_mapping cascade;
drop table if exists store_measurer_mapping cascade;
drop table if exists store_workpiece_mapping cascade;
drop table if exists stores cascade;
drop table if exists technologies cascade;
drop table if exists technology_setup_mapping cascade;
drop table if exists units cascade;
drop table if exists workpieces cascade;
create table additionals
(
    id           bigserial not null
        constraint PK_additionals primary key,
    process_time numeric(21, 0),
    tool_name    varchar(255),
    workpiece_id bigint
);
create table areas
(
    id        bigserial not null
        constraint PK_areas primary key,
    area_name varchar(255),
    store_id  bigint
);
create table areas_production_units
(
    production_area_id  bigint not null,
    production_units_id bigint not null
);
create table companies
(
    id                    bigserial not null
        constraint PK_companies primary key,
    address               varchar(255),
    bank                  varchar(255),
    bik                   varchar(255),
    company_name          varchar(255),
    correspondent_account varchar(255),
    email                 varchar(255),
    inn                   varchar(255),
    kpp                   varchar(255),
    okpo                  varchar(255),
    payment_account       varchar(255),
    phone_number          varchar(255),
    director_id           bigint
);
create table companies_departments
(
    company_id     bigint not null,
    departments_id bigint not null
);
create table companies_orders
(
    company_id bigint not null,
    orders_id  bigint not null
);
create table companies_production_areas
(
    company_id          bigint not null,
    production_areas_id bigint not null
);
create table cutters
(
    id             bigserial not null,
    description    varchar(255),
    price_amount   numeric(38, 2),
    price_currency varchar(255),
    tool_name      varchar(255),
    primary key (id)
);
create table departments
(
    id              bigserial not null,
    department_name varchar(255),
    primary key (id)
);
create table departments_employees
(
    department_id bigint not null,
    employees_id  bigint not null
);
create table employees
(
    id            bigserial not null,
    first_name    varchar(255),
    last_name     varchar(255),
    position      varchar(255),
    department_id bigint,
    primary key (id)
);
create table items
(
    id                   bigserial not null,
    actual_duration      numeric(21, 0),
    created_date         timestamp(6),
    estimated_duration   numeric(21, 0),
    is_accepted          boolean   not null,
    is_customer_material boolean   not null,
    quantity             integer,
    technology_id        bigint,
    primary key (id)
);
create table materials
(
    id                bigserial not null,
    date_of_actuality timestamp(6),
    density           integer,
    material_name     varchar(255),
    price_amount      numeric(38, 2),
    price_currency    varchar(255),
    primary key (id)
);
create table measurers
(
    id             bigserial not null,
    description    varchar(255),
    price_amount   numeric(38, 2),
    price_currency varchar(255),
    tool_name      varchar(255),
    primary key (id)
);
create table orders
(
    id                 bigserial not null,
    application_number integer,
    business_proposal  varchar(255),
    create_date        timestamp(6),
    description        varchar(255),
    recipient          varchar(255),
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
    interoperative_time numeric(21, 0),
    process_time        numeric(21, 0),
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
create table specials
(
    id           bigserial not null,
    process_time numeric(21, 0),
    tool_name    varchar(255),
    workpiece_id bigint,
    primary key (id)
);
create table store_cutter_mapping
(
    store_id          bigint not null,
    amount            integer,
    cutting_tools_key bigint not null,
    primary key (store_id, cutting_tools_key)
);
create table store_measurer_mapping
(
    store_id            bigint not null,
    amount              integer,
    measuring_tools_key bigint not null,
    primary key (store_id, measuring_tools_key)
);
create table store_workpiece_mapping
(
    store_id       bigint not null,
    amount         integer,
    workpieces_key bigint not null,
    primary key (store_id, workpieces_key)
);
create table stores
(
    id bigserial not null,
    primary key (id)
);
create table technologies
(
    id                               bigserial not null,
    date_time                        timestamp(6),
    drawing_name                     varchar(255),
    drawing_number                   varchar(255),
    quantity_of_defective_parts      integer,
    quantity_of_parts_from_workpiece integer,
    quantity_of_set_up_parts         integer,
    employee_id                      bigint,
    material_id                      bigint,
    workpiece_id                     bigint,
    primary key (id)
);
create table technology_setup_mapping
(
    technology_id bigint  not null,
    setup_id      bigint  not null,
    setups_key    integer not null,
    primary key (technology_id, setups_key)
);
create table units
(
    id             bigserial not null,
    price_amount   numeric(38, 2),
    price_currency varchar(255),
    unit_name      varchar(255),
    primary key (id)
);
create table workpieces
(
    id          bigserial not null,
    geom1       integer,
    geom2       integer,
    geom3       integer,
    geometry    varchar(255),
    material_id bigint,
    primary key (id)
);
alter table if exists areas_production_units
    add constraint UK_areas_production_units unique (production_units_id);
alter table if exists companies_departments
    add constraint UK_companies_departments unique (departments_id);
alter table if exists companies_orders
    add constraint UK_companies_orders unique (orders_id);
alter table if exists companies_production_areas
    add constraint UK_companies_production_areas unique (production_areas_id);
alter table if exists departments_employees
    add constraint UK_departments_employees unique (employees_id);
alter table if exists technology_setup_mapping
    add constraint UK_technology_setup_mapping unique (setup_id);
alter table if exists additionals
    add constraint FK_additionals_workpieceId
    foreign key (workpiece_id)
    references workpieces;
alter table if exists areas
    add constraint FK_areas_storeId
    foreign key (store_id)
    references stores;
alter table if exists areas_production_units
    add constraint FK_areas_production_units_production_unitsId
    foreign key (production_units_id)
    references units;
alter table if exists areas_production_units
    add constraint FK_areas_production_units_production_areaId
    foreign key (production_area_id)
    references areas;
alter table if exists companies
    add constraint FK_companies_directorId
    foreign key (director_id)
    references employees;
alter table if exists companies_departments
    add constraint FK_companies_departments_departmentsId
    foreign key (departments_id)
    references departments;
alter table if exists companies_departments
    add constraint FK_companies_departments_companyId
    foreign key (company_id)
    references companies;
alter table if exists companies_orders
    add constraint FK_companies_orders_ordersId
    foreign key (orders_id)
    references orders;
alter table if exists companies_orders
    add constraint FK_companies_orders_companyId
    foreign key (company_id)
    references companies;
alter table if exists companies_production_areas
    add constraint FK_companies_production_areas_production_areasId
    foreign key (production_areas_id)
    references areas;
alter table if exists companies_production_areas
    add constraint FK_company_production_areas_companyId
    foreign key (company_id)
    references companies;
alter table if exists departments_employees
    add constraint FK_departments_employees_employeesId
    foreign key (employees_id)
    references employees;
alter table if exists departments_employees
    add constraint FK_departments_employees_departmentId
    foreign key (department_id)
    references departments;
alter table if exists employees
    add constraint FK_employees_departmentId
    foreign key (department_id)
    references departments;
alter table if exists items
    add constraint FK_items_technologiesId
    foreign key (technology_id)
    references technologies;
alter table if exists orders_items
    add constraint FK_order_items_itemsId
    foreign key (items_id)
    references items;
alter table if exists orders_items
    add constraint FK_order_items_orderId
    foreign key (order_id)
    references orders;
alter table if exists setups
    add constraint FK_setups_production_unitId
    foreign key (production_unit_id)
    references units;
alter table if exists setups_additional_tools
    add constraint FK_additional_tools_additional_toolsId
    foreign key (additional_tools_id)
    references additionals;
alter table if exists setups_additional_tools
    add constraint FK_setups_additional_tools_setupId
    foreign key (setup_id)
    references setups;
alter table if exists setups_cutter_tools
    add constraint FK_setups_cutter_tools_cutter_toolsId
    foreign key (cutter_tools_id)
    references cutters;
alter table if exists setups_cutter_tools
    add constraint FK_setups_cutter_tools_setupId
    foreign key (setup_id)
    references setups;
alter table if exists setups_measure_tools
    add constraint FK_setup_measure_tools_measure_toolsId
    foreign key (measure_tools_id)
    references measurers;
alter table if exists setups_measure_tools
    add constraint FK_setups_measure_tools_setupId
    foreign key (setup_id)
    references setups;
alter table if exists setups_special_tools
    add constraint FK_setups_special_tools_special_toolsId
    foreign key (special_tools_id)
    references specials;
alter table if exists setups_special_tools
    add constraint FK_setups_special_tools_setupId
    foreign key (setup_id)
    references setups;
alter table if exists specials
    add constraint FK_specials_workpieceId
    foreign key (workpiece_id)
    references workpieces;
alter table if exists store_cutter_mapping
    add constraint FK_store_cutter_mapping_cutter_toolsKey
    foreign key (cutting_tools_key)
    references cutters;
alter table if exists store_cutter_mapping
    add constraint FK_store_cutter_mapping_storeId
    foreign key (store_id)
    references stores;
alter table if exists store_measurer_mapping
    add constraint FK_store_measurer_mapping_measuring_toolsKey
    foreign key (measuring_tools_key)
    references measurers;
alter table if exists store_measurer_mapping
    add constraint FK_store_measurer_mapping_storeId
    foreign key (store_id)
    references stores;
alter table if exists store_workpiece_mapping
    add constraint FK_store_workpiece_mapping_workpieceKey
    foreign key (workpieces_key)
    references workpieces;
alter table if exists store_workpiece_mapping
    add constraint FK_store_workpiece_mapping_storeId
    foreign key (store_id)
    references stores;
alter table if exists technologies
    add constraint FK_technologies_employeeId
    foreign key (employee_id)
    references employees;
alter table if exists technologies
    add constraint FK_technologies_materialId
    foreign key (material_id)
    references materials;
alter table if exists technologies
    add constraint FK_technologies_workpieceId
    foreign key (workpiece_id)
    references workpieces;
alter table if exists technology_setup_mapping
    add constraint FK_technology_setup_mapping_setupId
    foreign key (setup_id)
    references setups;
alter table if exists technology_setup_mapping
    add constraint FK_technology_setup_mapping_technologyId
    foreign key (technology_id)
    references technologies;
alter table if exists workpieces
    add constraint FK_workpiece_materialId
    foreign key (material_id)
    references materials;