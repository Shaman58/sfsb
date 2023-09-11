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
INSERT INTO workpieces (geom1, geom2, geom3, material_id)
VALUES (234, 123, 23, 3);
INSERT INTO workpieces (geom1, geom2, geom3, material_id)
VALUES (234, 234, 40, 3);
INSERT INTO workpieces (geom1, geom2, geom3, material_id)
VALUES (120, 55, null, 1);
insert into technologies (drawing_name, drawing_number, quantity_of_defective_parts,
                          quantity_of_parts_from_workpiece, quantity_of_set_up_parts, employee_id, is_computed)
values ('крышка', 'дкшг.100.200.001', 2, 5, 1, 1, false);
insert into technologies (drawing_name, drawing_number, quantity_of_defective_parts,
                          quantity_of_parts_from_workpiece, quantity_of_set_up_parts, employee_id, is_computed)
values ('корпус', 'дкшг.100.200.002', 3, 6, 2, 2, false);
insert into technologies (drawing_name, drawing_number, quantity_of_defective_parts,
                          quantity_of_parts_from_workpiece, quantity_of_set_up_parts, employee_id, is_computed,
                          workpiece_id)
values ('кронштейн', 'дкшг.100.200.003', 4, 7, 3, 2, false, 1);
INSERT INTO public.items (actual_duration, estimated_duration, is_accepted, is_customer_material, quantity,
                          technology_id, order_id, price_amount, price_currency)
VALUES (1000, 2000, true, true, 10, 1, 1, 100.00, 'RUB');
INSERT INTO public.items (actual_duration, estimated_duration, is_accepted, is_customer_material, quantity,
                          technology_id, order_id, price_amount, price_currency)
VALUES (2000, 3000, true, true, 10, 2, 1, 200.00, 'RUB');
INSERT INTO public.items (actual_duration, estimated_duration, is_accepted, is_customer_material, quantity,
                          technology_id, order_id, price_amount, price_currency)
VALUES (3000, 4000, true, true, 10, 3, 1, 300.00, 'RUB');
insert into tools(description, price_amount, price_currency, tool_name, tool_type)
values ('Штангель MAHR 250', 50000, 'RUB', 'MAHR 250 digasinm', 'MeasureTool');
insert into tools(description, price_amount, price_currency, tool_name, tool_type)
values ('Штангель Insize 250', 15000, 'RUB', 'Insise 250', 'MeasureTool');
insert into tools(description, price_amount, price_currency, tool_name, tool_type)
values ('Микрометр MAHR 250', 22300, 'RUB', 'MAHR 25 mcrdig', 'MeasureTool');
insert into tools(description, price_amount, price_currency, tool_name, tool_type)
values ('Тиски BISON 250x40', 40000, 'RUB', 'Тиски 250', 'Tooling');
insert into tools(description, price_amount, price_currency, tool_name, tool_type)
values ('Прижим 25-100', 1000, 'RUB', 'Прижим', 'Tooling');
insert into tools(description, price_amount, price_currency, tool_name, tool_type)
values ('Набор паралеллек INSIZE', 50000, 'RUB', 'Набор паралеллек INSIZE', 'Tooling');
insert into tools(description, price_amount, price_currency, tool_name, tool_type)
VALUES ('Фреза ф12', 1000, 'RUB', 'DEC1212', 'CutterTool');
insert into tools(description, price_amount, price_currency, tool_name, tool_type)
VALUES ('Фреза ф10', 2000, 'RUB', 'DEC1010', 'CutterTool');
insert into tools(description, price_amount, price_currency, tool_name, tool_type)
VALUES ('Фреза ф8', 3000, 'RUB', 'DEC0808', 'CutterTool');
insert into tools(description, price_amount, price_currency, tool_name, tool_type)
VALUES ('Гриб ф12д6в2', 1000, 'RUB', 'Гриб ф12д6в2', 'SpecialTool');
insert into tools(description, price_amount, price_currency, tool_name, tool_type)
VALUES ('Гриб ф16д10в2', 2000, 'RUB', 'Гриб ф16д10в2', 'SpecialTool');
insert into tools(description, price_amount, price_currency, tool_name, tool_type)
VALUES ('Долбяк 2мм', 3000, 'RUB', 'Долбяк 2мм', 'SpecialTool');
INSERT INTO public.tools (description, price_amount, price_currency, tool_name, tool_type)
VALUES ('М32х1', null, null, 'пробка в жопу', 'MeasureTool');
INSERT INTO public.tools (description, price_amount, price_currency, tool_name, tool_type)
VALUES ('M40X2', null, null, 'Пробка в жопу', 'MeasureTool');
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
INSERT INTO setups (interoperative_time, process_time, setup_number, setup_time, technology_id, operation_id, is_group,
                    per_time, is_cooperate)
VALUES (60000000000, 60000000000, 45, 60000000000, 3, 1, false, null, false);
INSERT INTO setups (interoperative_time, process_time, setup_number, setup_time, technology_id, operation_id, is_group,
                    per_time, is_cooperate)
VALUES (120000000000, 120000000000, 30, 120000000000, 3, 4, false, null, false);
INSERT INTO setups (interoperative_time, process_time, setup_number, setup_time, technology_id, operation_id, is_group,
                    per_time, is_cooperate)
VALUES (0, 7200000000000, 15, 0, 3, 8, true, 5, true);
INSERT INTO setups (interoperative_time, process_time, setup_number, setup_time, technology_id, operation_id, is_group,
                    per_time, is_cooperate)
VALUES (0, 0, 50, 0, 3, 9, false, null, true);
INSERT INTO public.tool_items (tool_id, setup_id, amount, tool_type)
VALUES (7, 1, 12, 'CutterToolItem');
INSERT INTO public.tool_items (tool_id, setup_id, amount, tool_type)
VALUES (10, 1, 12, 'SpecialToolItem');
INSERT INTO public.tool_items (tool_id, setup_id, amount, tool_type)
VALUES (7, 4, 12, 'CutterToolItem');
INSERT INTO public.tool_items (tool_id, setup_id, amount, tool_type)
VALUES (10, 4, 3, 'SpecialToolItem');
INSERT INTO setups_toolings (setup_id, tooling_id)
VALUES (1, 5);
INSERT INTO setups_toolings (setup_id, tooling_id)
VALUES (1, 6);
INSERT INTO setups_toolings (setup_id, tooling_id)
VALUES (4, 4);
INSERT INTO setups_measure_tools (setup_id, measure_tool_id)
VALUES (1, 13);
INSERT INTO setups_measure_tools (setup_id, measure_tool_id)
VALUES (4, 14);
INSERT INTO additionals (tool_name, workpiece_id, setup_id)
VALUES ('Люлька', 3, 1);