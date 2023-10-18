insert into companies(address, bank, bik, company_name, correspondent_account, email, inn, kpp, ogrn, payment_account,
                      phone_number)
values ('442960, Пензенская область, г. Заречный, ул. Транспортная, д.31, к.2', 'ФИЛИАЛ «НИЖЕГОРОДСКИЙ»
АО «АЛЬФА-БАНК»', '042202824',
        'ООО «Импульс-Атом»', '30101810200000000824', 'epnz@yandex.ru',
        '5838015170', '583801001', '1215800007123', '40702810729170005285', '+78412755055');
insert into departments(department_name, company_id)
values ('Коммерческий отдел', 1);
insert into departments(department_name, company_id)
values ('Технологический отдел', 1);
insert into materials (material_name, geometry, gost, density, price_amount, price_currency)
VALUES ('Сплав Д16Т', 'CYLINDER', 'ГОСТ 21488-97', 2700, 800, 'RUB');
insert into materials (material_name, geometry, gost, density, price_amount, price_currency)
VALUES ('Сплав Д16Т', 'TUBE', 'ГОСТ 18482-79', 2700, 750, 'RUB');
insert into materials (material_name, geometry, gost, density, price_amount, price_currency)
VALUES ('Сплав Д16Т', 'BLANK', 'ГОСТ 17232-99', 2700, 550, 'RUB');
insert into materials (material_name, geometry, gost, density, price_amount, price_currency)
VALUES ('Сталь 40х', 'CYLINDER', 'ГОСТ 1051-73', 7800, 300, 'RUB');
insert into materials (material_name, geometry, gost, density, price_amount, price_currency)
VALUES ('Сталь 40х', 'CYLINDER', 'ГОСТ 10702-2016', 7800, 280, 'RUB');
insert into materials (material_name, geometry, gost, density, price_amount, price_currency)
VALUES ('Сталь 40х', 'CYLINDER', 'ГОСТ 4543-2016', 7800, 320, 'RUB');
insert into tools(description, tool_name, tool_type)
values ('Тиски BISON 250x40', 'Тиски 250', 'Tooling');
insert into tools(description, tool_name, tool_type)
values ('Прижим 25-100', 'Прижим', 'Tooling');
insert into tools(description, tool_name, tool_type)
values ('Набор паралеллек INSIZE', 'Набор паралеллек INSIZE', 'Tooling');
insert into tools(description, tool_name, tool_type)
VALUES ('Фреза ф12', 'DEC1212', 'CutterTool');
insert into tools(description, tool_name, tool_type)
VALUES ('Фреза ф10', 'DEC1010', 'CutterTool');
insert into tools(description, tool_name, tool_type)
VALUES ('Фреза ф8', 'DEC0808', 'CutterTool');
insert into tools(description, tool_name, tool_type)
VALUES ('Гриб ф12д6в2', 'Гриб ф12д6в2', 'SpecialTool');
insert into tools(description, tool_name, tool_type)
VALUES ('Гриб ф16д10в2', 'Гриб ф16д10в2', 'SpecialTool');
insert into tools(description, tool_name, tool_type)
VALUES ('Долбяк 2мм', 'Долбяк 2мм', 'SpecialTool');
insert into material_density_templates(material_type_name, density)
values ('Алюминиевый сплав', 2700);
insert into material_density_templates(material_type_name, density)
values ('Пенопласт', 40);
insert into material_density_templates(material_type_name, density)
values ('Сталь', 7800);
insert into material_density_templates(material_type_name, density)
values ('Медь', 8900);
insert into material_density_templates(material_type_name, density)
values ('Бронза', 8100);

insert into operations (payment_amount, payment_currency, operation_name, operation_time_management)
values (3000, 'RUB', 'Наладка', 'NONE');
insert into operations (payment_amount, payment_currency, operation_name, operation_time_management)
values (4000, 'RUB', 'Технолог', 'NONE');

insert into operations (payment_amount, payment_currency, operation_name, operation_time_management)
values (1000, 'RUB', 'Фрезерная 3ос', 'FULL');
insert into operations (payment_amount, payment_currency, operation_name, operation_time_management)
values (2000, 'RUB', 'Фрезерная 3+2ос', 'FULL');
insert into operations (payment_amount, payment_currency, operation_name, operation_time_management)
values (3000, 'RUB', 'Фрезерная 5ос', 'FULL');
insert into operations (payment_amount, payment_currency, operation_name, operation_time_management)
values (2000, 'RUB', 'Фрезерная 4ос', 'FULL');
insert into operations (payment_amount, payment_currency, operation_name, operation_time_management)
values (1000, 'RUB', 'Токарная', 'FULL');
insert into operations (payment_amount, payment_currency, operation_name, operation_time_management)
values (1000, 'RUB', 'Токарно-фрезерная', 'FULL');
insert into operations (payment_amount, payment_currency, operation_name, operation_time_management)
values (1000, 'RUB', 'Слесарная', 'PROCESS_TIME_ONLY');
insert into operations (payment_amount, payment_currency, operation_name, operation_time_management)
values (1000, 'RUB', 'Термичка', 'COMPUTED');
insert into operations (payment_amount, payment_currency, operation_name, operation_time_management)
values (1000, 'RUB', 'Гальваника', 'NONE');