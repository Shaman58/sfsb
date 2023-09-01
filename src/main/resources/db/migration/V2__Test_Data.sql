insert into companies(address, bank, bik, company_name, correspondent_account, email, inn, kpp, ogrn, payment_account,
                      phone_number)
values ('442960, Пензенская область, г. Заречный, ул. Транспортная, д.31, к.2', 'ФИЛИАЛ «НИЖЕГОРОДСКИЙ»
АО «АЛЬФА-БАНК»', '042202824',
        'ООО «Импульс-Атом»', '30101810200000000824', 'epnz@yandex.ru',
        '5838015170', '583801001', '1215800007123', '40702810729170005285', '+78412755055');
insert into customers(address, bank, bik, company_name, correspondent_account, email, inn, kpp, ogrn, payment_account,
                      phone_number)
values ('442960, Пензенская область, г. Заречный, ул. Транспортная, д.100', 'АО «ВТБ»', '042769107',
        'ООО «Ритм»', '30101810200000000976', 'ritm@mail.ru',
        '5838015170', '583801001', '1215800007123', '40702810729170005285', '+78412759999');
insert into customers(address, bank, bik, company_name, correspondent_account, email, inn, kpp, ogrn, payment_account,
                      phone_number)
values ('442960, Пензенская область, г. Заречный, ул. Транспортная, д.100', 'АО «ВТБ»', '042769107',
        'ООО «ХУИТМ»', '30101810200000000976', 'ritm@mail.ru',
        '5838015170', '583801001', '1215800007123', '40702810729170005285', '+78412759999');
insert into customers(address, bank, bik, company_name, correspondent_account, email, inn, kpp, ogrn, payment_account,
                      phone_number)
values ('442960, Пензенская область, г. Заречный, ул. Транспортная, д.100', 'АО «ВТБ»', '042769107',
        'ИП «Рогачов»', '30101810200000000976', 'ritm@mail.ru',
        '5838015170', '583801001', '1215800007123', '40702810729170005285', '+78412759999');
insert into departments(department_name, company_id)
values ('Коммерческий отдел', 1);
insert into departments(department_name, company_id)
values ('Технологический отдел', 1);
insert into employees(first_name, last_name, position, department_id)
values ('Иван', 'Иванов', 'Менеджер', 1);
insert into employees(first_name, last_name, position, department_id)
values ('Николай', 'Меланин', 'Управдом', 1);
insert into employees(first_name, last_name, position, department_id)
values ('Егор', 'Егоров', 'Технолог', 2);
insert into employees(first_name, last_name, position, department_id)
values ('Дмитрий', 'Душный', 'Технолог', 2);
insert into contacts(first_name, last_name, position, email, phone_number, customer_id)
values ('Иван', 'Иванов', 'Диреалист', 'email@email.com', '+71234567899', 1);
insert into contacts(first_name, last_name, position, email, phone_number, customer_id)
values ('Вася', 'Васильев', 'Похуист', 'email@email.com', '+71234567899', 1);
insert into contacts(first_name, last_name, position, email, phone_number, customer_id)
values ('Иван', 'Иванов', 'Диреалист', 'email@email.com', '+71234567899', 2);
insert into orders(application_number, business_proposal, description, customer_id, employee_id, contact_id)
values (1, 'В ответ на ваше обращение от 15.03.2023г. о предоставлении ценовой информации сообщаем, что ООО «Импульс-Атом» готово изготовить детали, в требуемом для Вас количестве по цене, которая включает стоимость материала, изготовление деталей механическим путем, согласно КД. Сроки изготовления рассчитываются на момент заключения договора по согласованию с планом производства и требованиями заказчика.
Условия авансирования 70% для обеспечения закупки необходимого материала, проведения технологической подготовки, изготовления специальной оснастки и приспособлений.
Окончательный расчет по факту поставки в течении 5-ти рабочих дней.
Так же просим рассмотреть возможность предоставления давальческого сырья и электронные модели деталей.
Благодарю Вас за обращение в нашу компанию и надеюсь на долгосрочное и взаимовыгодное сотрудничество.', 'description',
        1, 1, 1);
insert into operations (payment_amount, payment_currency, operation_name, operation_type)
values (1000, 'RUB', 'Фрезерная', 'FROM_PROCESS_UNIT');
insert into operations (payment_amount, payment_currency, operation_name, operation_type)
values (900, 'RUB', 'Токарная', 'FROM_PROCESS_UNIT');
insert into operations (payment_amount, payment_currency, operation_name, operation_type)
values (800, 'RUB', 'Слесарная', 'FROM_OPERATION');
insert into operations (payment_amount, payment_currency, operation_name, operation_type)
values (1200, 'RUB', 'Термичка', 'FROM_OPERATION_WITH_CALC');
insert into operations (payment_amount, payment_currency, operation_name, operation_type)
values (0, 'RUB', 'Кооперация', 'NONE');
insert into technologies (drawing_name, drawing_number, quantity_of_defective_parts,
                          quantity_of_parts_from_workpiece, quantity_of_set_up_parts, employee_id, is_computed)
values ('крышка', 'дкшг.100.200.001', 2, 5, 1, 1, false);
insert into technologies (drawing_name, drawing_number, quantity_of_defective_parts,
                          quantity_of_parts_from_workpiece, quantity_of_set_up_parts, employee_id, is_computed)
values ('корпус', 'дкшг.100.200.002', 3, 6, 2, 2, false);
insert into technologies (drawing_name, drawing_number, quantity_of_defective_parts,
                          quantity_of_parts_from_workpiece, quantity_of_set_up_parts, employee_id, is_computed)
values ('кронштейн', 'дкшг.100.200.003', 4, 7, 3, 2, false);
insert into items(actual_duration, estimated_duration, is_accepted, is_customer_material, quantity, technology_id,
                  price_amount, price_currency, order_id)
values (1000, 2000, true, true, 10, 1, 100, 'RUB', 1);
insert into items(actual_duration, estimated_duration, is_accepted, is_customer_material, quantity, technology_id,
                  price_amount, price_currency, order_id)
values (2000, 3000, true, true, 10, 2, 200, 'RUB', 1);
insert into items(actual_duration, estimated_duration, is_accepted, is_customer_material, quantity, technology_id,
                  price_amount, price_currency, order_id)
values (3000, 4000, true, true, 10, 3, 300, 'RUB', 1);
insert into setups(interoperative_time, process_time, operation_id, setup_number, setup_time, technology_id, per_time,
                   is_group)
values (1000, 2000, 1, '1', 1000, 1, 2, true);
insert into setups(interoperative_time, process_time, operation_id, setup_number, setup_time, technology_id, per_time,
                   is_group)
values (2000, 3000, 2, '2', 2000, 1, 4, true);
insert into setups(interoperative_time, process_time, operation_id, setup_number, setup_time, technology_id, per_time,
                   is_group)
values (3000, 4000, 2, '3', 3000, 1, null, false);
insert into setups(interoperative_time, process_time, operation_id, setup_number, setup_time, technology_id, per_time,
                   is_group)
values (1001, 2001, 4, '1', 1001, 2, null, false);
insert into setups(interoperative_time, process_time, operation_id, setup_number, setup_time, technology_id, per_time,
                   is_group)
values (2002, 3002, 1, '2', 2002, 2, 5, true);
insert into setups(interoperative_time, process_time, operation_id, setup_number, setup_time, technology_id, per_time,
                   is_group)
values (3003, 4003, 2, '3', 3003, 2, 5, true);
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
insert into measurers(description, price_amount, price_currency, tool_name, amount)
values ('Штангель MAHR 250', 50000, 'RUB', 'MAHR 250 digasinm', 2);
insert into measurers(description, price_amount, price_currency, tool_name, amount)
values ('Штангель Insize 250', 15000, 'RUB', 'Insise 250', 1);
insert into measurers(description, price_amount, price_currency, tool_name, amount)
values ('Микрометр MAHR 250', 22300, 'RUB', 'MAHR 25 mcrdig', 1);
insert into workpieces(geom1, geom2, geom3, material_id)
values (100, 50, 0, 1);
insert into workpieces(geom1, geom2, geom3, material_id)
values (100, 50, 20, 2);
insert into workpieces(geom1, geom2, geom3, material_id)
values (100, 60, 0, 3);
insert into additionals(tool_name, workpiece_id)
values ('Плита для изготовления ДКШГ.001.535.001 Корпус-хуерпус', 1);
insert into additionals(tool_name, workpiece_id)
values ('Плита для изготовления ДКШГ.001.535.003 Корпус-датчиков', 2);
insert into additionals(tool_name, workpiece_id)
values ('Губки для тисков для изготовления РНБЦ-0011 планка', 2);
insert into toolings(description, price_amount, price_currency, tool_name, amount)
values ('Тиски BISON 250x40', 40000, 'RUB', 'Тиски 250', 1);
insert into toolings(description, price_amount, price_currency, tool_name, amount)
values ('Прижим 25-100', 1000, 'RUB', 'Прижим', 1);
insert into toolings(description, price_amount, price_currency, tool_name, amount)
values ('Набор паралеллек INSIZE', 50000, 'RUB', 'Набор паралеллек INSIZE', 1);
insert into specials(description, price_amount, price_currency, tool_name, amount)
VALUES ('Гриб 12h2d6l20', 1000, 'RUB', 'Гриб 12h2d6l20', 1);
insert into specials(description, price_amount, price_currency, tool_name, amount)
VALUES ('Гриб 22h2d12l20', 2000, 'RUB', 'Гриб 22h2d12l20', 1);
insert into specials(description, price_amount, price_currency, tool_name, amount)
VALUES ('Гриб 20h2d6l20', 3000, 'RUB', 'Гриб 20h2d6l20', 1);
insert into units(payment_amount, payment_currency, unit_name, unit_number,
                  company_id)
values (1000, 'RUB', 'DMU 50', 1, 1);
insert into units(payment_amount, payment_currency, unit_name, unit_number,
                  company_id)
values (1000, 'RUB', 'Тверь 800', 2, 1);
insert into units(payment_amount, payment_currency, unit_name, unit_number,
                  company_id)
values (1100, 'RUB', 'DMU 70', 3, 1);
insert into units(payment_amount, payment_currency, unit_name, unit_number,
                  company_id)
values (800, 'RUB', 'Тверь 600', 3, 1);
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