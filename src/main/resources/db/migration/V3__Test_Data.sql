insert into companies(address, bank, bik, company_name, correspondent_account, email, inn, kpp, ogrn, payment_account,
                      phone_number)
values ('442960, Пензенская область, г. Заречный, ул. Транспортная, д.31, к.2', 'ФИЛИАЛ «НИЖЕГОРОДСКИЙ»
АО «АЛЬФА-БАНК»', '042202824',
        'ООО «Импульс-Атом»', '30101810200000000824', 'epnz@yandex.ru',
        '5838015170', '583801001', '1215800007123', '40702810729170005285', '+7 (8412) 75-50-55');
insert into customers(address, bank, bik, company_name, correspondent_account, email, inn, kpp, ogrn, payment_account,
                      phone_number)
values ('442960, Пензенская область, г. Заречный, ул. Транспортная, д.100', 'АО «ВТБ»', '042769107',
        'ООО «Ритм»', '30101810200000000976', 'ritm@mail.ru',
        '5838015170', '583801001', '1215800007123', '40702810729170005285', '+7 (8412) 75-99-99');
insert into customers(address, bank, bik, company_name, correspondent_account, email, inn, kpp, ogrn, payment_account,
                      phone_number)
values ('442960, Пензенская область, г. Заречный, ул. Транспортная, д.100', 'АО «ВТБ»', '042769107',
        'ООО «ХУИТМ»', '30101810200000000976', 'ritm@mail.ru',
        '5838015170', '583801001', '1215800007123', '40702810729170005285', '+7 (8412) 75-99-99');
insert into customers(address, bank, bik, company_name, correspondent_account, email, inn, kpp, ogrn, payment_account,
                      phone_number)
values ('442960, Пензенская область, г. Заречный, ул. Транспортная, д.100', 'АО «ВТБ»', '042769107',
        'ИП «Рогачов»', '30101810200000000976', 'ritm@mail.ru',
        '5838015170', '583801001', '1215800007123', '40702810729170005285', '+7 (8412) 75-99-99');
insert into departments(department_name, company_id)
values ('Отдел продаж', 1);
insert into departments(department_name, company_id)
values ('Отдел маркетинга', 1);
insert into departments(department_name, company_id)
values ('Технологический отдел', 1);
insert into departments(department_name, company_id)
values ('Конструкторский отдел', 1);
insert into departments(department_name, company_id)
values ('Отдел техники безопасности', 1);
insert into departments(department_name, company_id)
values ('Отдел кадров', 1);
insert into departments(department_name, company_id)
values ('Отдел ИТ', 1);
insert into departments(department_name, company_id)
values ('Отдел делопроизводства', 1);
insert into departments(department_name, company_id)
values ('Отдел технического контроля', 1);
insert into departments(department_name, company_id)
values ('Бухгалтерия', 1);
insert into departments(department_name, company_id)
values ('Планово-экономический отдел', 1);
insert into employees(first_name, last_name, position, department_id)
values ('Иван', 'Иванов', 'Менеджер', 1);
insert into contacts(first_name, last_name, position, email, phone_number, customer_id)
values ('Иван', 'Иванов', 'Диреалист', 'email@email.com', '+7 (1234) 56-78-99', 1);
insert into contacts(first_name, last_name, position, email, phone_number, customer_id)
values ('Вася', 'Васильев', 'Похуист', 'email@email.com', '+7 (1234) 56-78-99', 1);
insert into contacts(first_name, last_name, position, email, phone_number, customer_id)
values ('Константин', 'Фамилия', 'Онанист', 'email@email.com', '+7 (1234) 56-78-99', 1);
insert into contacts(first_name, last_name, position, email, phone_number, customer_id)
values ('Иван', 'Иванов', 'Диреалист', 'email@email.com', '+7 (1234) 56-78-99', 1);
insert into contacts(first_name, last_name, position, email, phone_number, customer_id)
values ('Иван', 'Иванов', 'Диреалист', 'email@email.com', '+7 (1234) 56-78-99', 2);
insert into contacts(first_name, last_name, position, email, phone_number, customer_id)
values ('Иван', 'Иванов', 'Диреалист', 'email@email.com', '+7 (1234) 56-78-99', 2);
insert into employees(first_name, last_name, position, department_id)
values ('Николай', 'Меланин', 'Исполнительный директор', 1);
insert into employees(first_name, last_name, position, department_id)
values ('Василий', 'Васильев', 'Управдом', 2);
insert into employees(first_name, last_name, position, department_id)
values ('Егор', 'Егоров', 'Наладчик', 2);
insert into employees(first_name, last_name, position, department_id)
values ('Дмитрий', 'Душный', 'Мастер производства', 2);
insert into employees(first_name, last_name, position, department_id)
values ('Никита', 'Никиткин', 'Программист', 2);
insert into orders(application_number, business_proposal, description, customer_id, employee_id, contact_id)
values (1, 'В ответ на ваше обращение от 15.03.2023г. о предоставлении ценовой информации сообщаем, что ООО «Импульс-Атом» готово изготовить детали, в требуемом для Вас количестве по цене, которая включает стоимость материала, изготовление деталей механическим путем, согласно КД. Сроки изготовления рассчитываются на момент заключения договора по согласованию с планом производства и требованиями заказчика.
Условия авансирования 70% для обеспечения закупки необходимого материала, проведения технологической подготовки, изготовления специальной оснастки и приспособлений.
Окончательный расчет по факту поставки в течении 5-ти рабочих дней.
Так же просим рассмотреть возможность предоставления давальческого сырья и электронные модели деталей.
Благодарю Вас за обращение в нашу компанию и надеюсь на долгосрочное и взаимовыгодное сотрудничество.', 'description',
        1, 1, 1);
insert into technologies (drawing_name, drawing_number, quantity_of_defective_parts,
                          quantity_of_parts_from_workpiece, quantity_of_set_up_parts, employee_id)
values ('крышка', 'дкшг.100.200.001', 2, 5, 1, 1);
insert into technologies (drawing_name, drawing_number, quantity_of_defective_parts,
                          quantity_of_parts_from_workpiece, quantity_of_set_up_parts, employee_id)
values ('корпус', 'дкшг.100.200.002', 3, 6, 2, 2);
insert into technologies (drawing_name, drawing_number, quantity_of_defective_parts,
                          quantity_of_parts_from_workpiece, quantity_of_set_up_parts, employee_id)
values ('кронштейн', 'дкшг.100.200.003', 4, 7, 3, 2);
insert into items(actual_duration, estimated_duration, is_accepted, is_customer_material, quantity, technology_id,
                  price_amount, price_currency, order_id)
values (1000, 2000, true, true, 10, 1, 100, 'RUB', 1);
insert into items(actual_duration, estimated_duration, is_accepted, is_customer_material, quantity, technology_id,
                  price_amount, price_currency, order_id)
values (2000, 3000, true, true, 10, 2, 200, 'RUB', 1);
insert into items(actual_duration, estimated_duration, is_accepted, is_customer_material, quantity, technology_id,
                  price_amount, price_currency, order_id)
values (3000, 4000, true, true, 10, 3, 300, 'RUB', 1);
insert into setups(interoperative_time, process_time, setup_name, setup_number, setup_time, technology_id)
values (1000, 2000, 'Стандарт', '1', 1000, 1);
insert into setups(interoperative_time, process_time, setup_name, setup_number, setup_time, technology_id)
values (2000, 3000, 'Стандарт', '2', 2000, 1);
insert into setups(interoperative_time, process_time, setup_name, setup_number, setup_time, technology_id)
values (3000, 4000, 'Стандарт', '3', 3000, 1);
insert into setups(interoperative_time, process_time, setup_name, setup_number, setup_time, technology_id)
values (1001, 2001, 'Стандарт', '1', 1001, 2);
insert into setups(interoperative_time, process_time, setup_name, setup_number, setup_time, technology_id)
values (2002, 3002, 'Стандарт', '2', 2002, 2);
insert into setups(interoperative_time, process_time, setup_name, setup_number, setup_time, technology_id)
values (3003, 4003, 'Стандарт', '3', 3003, 2);
insert into cutters(description, price_amount, price_currency, tool_name)
values ('метчик M3 р6м5', 123, 'RUB', 'fanar m3 qtgy5r');
insert into cutters(description, price_amount, price_currency, tool_name)
values ('фреза ф12 по цветным металлам', 1232, 'RUB', 'HET DEC1212');
insert into cutters(description, price_amount, price_currency, tool_name)
values ('фреза ф10 по цветным металлам', 1232, 'RUB', 'HET DEC1010');
insert into cutters(description, price_amount, price_currency, tool_name)
values ('центровка ф8х90', 1023, 'RUB', 'HET ESDC0808');
insert into setups_cutter_tools(setup_id, cutter_tools_id)
values (1, 1);
insert into setups_cutter_tools(setup_id, cutter_tools_id)
values (1, 2);
insert into setups_cutter_tools(setup_id, cutter_tools_id)
values (1, 4);
insert into setups_cutter_tools(setup_id, cutter_tools_id)
values (2, 1);
insert into setups_cutter_tools(setup_id, cutter_tools_id)
values (2, 3);
insert into setups_cutter_tools(setup_id, cutter_tools_id)
values (2, 4);
insert into setups_cutter_tools(setup_id, cutter_tools_id)
values (3, 2);
insert into setups_cutter_tools(setup_id, cutter_tools_id)
values (3, 4);
insert into setups_cutter_tools(setup_id, cutter_tools_id)
values (4, 1);
insert into setups_cutter_tools(setup_id, cutter_tools_id)
values (4, 2);
insert into setups_cutter_tools(setup_id, cutter_tools_id)
values (5, 2);
insert into setups_cutter_tools(setup_id, cutter_tools_id)
values (6, 2);
insert into setups_cutter_tools(setup_id, cutter_tools_id)
values (6, 4);
insert into materials (density, material_name)
VALUES (2670, 'Сплав АМг3');
insert into materials (density, material_name)
VALUES (7820, '40х');
insert into materials (density, material_name)
VALUES (2770, 'Сплав Д16');
insert into areas(area_name, company_id)
values ('Площадка на Литвинова 10', 1);
insert into areas(area_name, company_id)
values ('Площадка на Литвинова 20', 1);
insert into stores(store_name, production_area_id)
values ('Склад МЦ', 1);
insert into stores(store_name, production_area_id)
values ('Склад инструмента', 1);
insert into stores(store_name, production_area_id)
values ('Склад МЦ 2', 2);
insert into stores(store_name, production_area_id)
values ('Склад инструмента 2', 2);
insert into store_cutter_mapping(store_id, amount, cutter_tools_key)
VALUES (1, 10, 1);
insert into store_cutter_mapping(store_id, amount, cutter_tools_key)
VALUES (1, 2, 2);
insert into store_cutter_mapping(store_id, amount, cutter_tools_key)
VALUES (1, 5, 3);
insert into store_cutter_mapping(store_id, amount, cutter_tools_key)
VALUES (1, 15, 4);
insert into store_cutter_mapping(store_id, amount, cutter_tools_key)
VALUES (2, 10, 1);
insert into store_cutter_mapping(store_id, amount, cutter_tools_key)
VALUES (2, 2, 2);
insert into store_cutter_mapping(store_id, amount, cutter_tools_key)
VALUES (2, 5, 3);
insert into store_cutter_mapping(store_id, amount, cutter_tools_key)
VALUES (2, 15, 4);
insert into measurers(description, price_amount, price_currency, tool_name)
values ('Штангель MAHR 250', 50000, 'RUB', 'MAHR 250 digasinm');
insert into measurers(description, price_amount, price_currency, tool_name)
values ('Штангель Insize 250', 15000, 'RUB', 'Insise 250');
insert into measurers(description, price_amount, price_currency, tool_name)
values ('Микрометр MAHR 250', 22300, 'RUB', 'MAHR 25 mcrdig');
insert into store_measurer_mapping(store_id, amount, measure_tools_key)
VALUES (1, 2, 1);
insert into store_measurer_mapping(store_id, amount, measure_tools_key)
VALUES (1, 1, 2);
insert into store_measurer_mapping(store_id, amount, measure_tools_key)
VALUES (1, 1, 3);
insert into store_measurer_mapping(store_id, amount, measure_tools_key)
VALUES (2, 2, 1);
insert into store_measurer_mapping(store_id, amount, measure_tools_key)
VALUES (2, 1, 2);
insert into store_measurer_mapping(store_id, amount, measure_tools_key)
VALUES (2, 1, 3);
insert into workpieces(geom1, geom2, geom3, geometry, material_id, price_amount, price_currency)
values (100, 50, 0, 'CYLINDER', 1, 500, 'RUB');
insert into workpieces(geom1, geom2, geom3, geometry, material_id, price_amount, price_currency)
values (100, 50, 20, 'BLANK', 2, 600, 'RUB');
insert into workpieces(geom1, geom2, geom3, geometry, material_id, price_amount, price_currency)
values (100, 60, 0, 'CYLINDER', 3, 200, 'RUB');
insert into additionals(process_time, tool_name, workpiece_id)
values (20000, 'Плита для изготовления ДКШГ.001.535.001 Корпус-хуерпус', 1);
insert into additionals(process_time, tool_name, workpiece_id)
values (30000, 'Плита для изготовления ДКШГ.001.535.003 Корпус-датчиков', 2);
insert into additionals(process_time, tool_name, workpiece_id)
values (40000, 'Губки для тисков для изготовления РНБЦ-0011 планка', 2);
insert into store_workpiece_mapping(store_id, amount, workpieces_key)
VALUES (1, 50, 1);
insert into store_workpiece_mapping(store_id, amount, workpieces_key)
VALUES (1, 500, 2);
insert into store_workpiece_mapping(store_id, amount, workpieces_key)
VALUES (1, 55, 3);
insert into store_workpiece_mapping(store_id, amount, workpieces_key)
VALUES (2, 30, 1);
insert into toolings(description, price_amount, price_currency, tooling_name)
values ('Тиски BISON 250x40', 40000, 'RUB', 'Тиски 250');
insert into toolings(description, price_amount, price_currency, tooling_name)
values ('Прижим 25-100', 1000, 'RUB', 'Прижим');
insert into toolings(description, price_amount, price_currency, tooling_name)
values ('Набор паралеллек INSIZE', 50000, 'RUB', 'Набор паралеллек INSIZE');
insert into store_tooling_mapping(store_id, amount, toolings_key)
VALUES (1, 2, 1);
insert into store_tooling_mapping(store_id, amount, toolings_key)
VALUES (1, 10, 2);
insert into store_tooling_mapping(store_id, amount, toolings_key)
VALUES (2, 2, 3);
insert into store_tooling_mapping(store_id, amount, toolings_key)
VALUES (2, 1, 1);
insert into specials(process_time, tool_name, workpiece_id)
VALUES (10000, 'Гриб 12h2d6l20', 1);
insert into specials(process_time, tool_name, workpiece_id)
VALUES (11000, 'Гриб 22h2d12l20', 1);
insert into specials(process_time, tool_name, workpiece_id)
VALUES (12000, 'Гриб 20h2d6l20', 1);
insert into store_special_mapping(store_id, amount, special_tools_key)
values (1, 1, 1);
insert into store_special_mapping(store_id, amount, special_tools_key)
values (1, 1, 2);
insert into store_special_mapping(store_id, amount, special_tools_key)
values (2, 1, 3);
insert into units(price_amount, price_currency, payment_amount, payment_currency, unit_name, unit_number,
                  production_area_id)
values (5000000, 'RUB', 1000, 'RUB', 'DMU 50', 1, 1);
insert into units(price_amount, price_currency, payment_amount, payment_currency, unit_name, unit_number,
                  production_area_id)
values (3000000, 'RUB', 900, 'RUB', 'Тверь 800', 2, 1);
insert into units(price_amount, price_currency, payment_amount, payment_currency, unit_name, unit_number,
                  production_area_id)
values (5000000, 'RUB', 1100, 'RUB', 'DMU 70', 3, 2);
insert into units(price_amount, price_currency, payment_amount, payment_currency, unit_name, unit_number,
                  production_area_id)
values (3000000, 'RUB', 800, 'RUB', 'Тверь 600', 3, 2);