insert into companies(address, bank, bik, company_name, correspondent_account, email, inn, kpp, okpo, payment_account,
                      phone_number)
values ('442960, Пензенская область, г. Заречный, ул. Транспортная, д.31, к.2', 'ФИЛИАЛ «НИЖЕГОРОДСКИЙ»
АО «АЛЬФА-БАНК»', '042202824',
        'Общество с ограниченной ответственностью «Импульс-Атом»', '30101810200000000824', 'epnz@yandex.ru',
        '5838015170', '583801001', '1215800007598', '40702810729170005285', '+7 (8412) 75-50-55');
insert into customers(address, bank, bik, company_name, correspondent_account, email, inn, kpp, okpo, payment_account,
                      phone_number)
values ('442960, Пензенская область, г. Заречный, ул. Транспортная, д.100', 'АО «ВТБ»', '042769107',
        'Общество с ограниченной ответственностью «Ритм»', '30101810200000000976', 'ritm@mail.ru',
        '5838015170', '583801001', '1215800007598', '40702810729170005285', '+7 (8412) 75-99-99');
insert into departments(department_name, company_id)
values ('Отдел продаж', 1);
insert into employees(first_name, last_name, position, department_id)
values ('Иван', 'Иванов', 'Менеджер', 1);
insert into contacts(first_name, last_name, position, customer_id)
values ('Иван', 'Иванов', 'Диреалист', 1);
insert into orders(application_number, business_proposal, description, customer_id, employee_id, contact_id)
values (1, 'В ответ на ваше обращение от 15.03.2023г. о предоставлении ценовой информации сообщаем, что ООО «Импульс-Атом» готово изготовить детали, в требуемом для Вас количестве по цене, которая включает стоимость материала, изготовление деталей механическим путем, согласно КД. Сроки изготовления рассчитываются на момент заключения договора по согласованию с планом производства и требованиями заказчика.
Условия авансирования 70% для обеспечения закупки необходимого материала, проведения технологической подготовки, изготовления специальной оснастки и приспособлений.
Окончательный расчет по факту поставки в течении 5-ти рабочих дней.
Так же просим рассмотреть возможность предоставления давальческого сырья и электронные модели деталей.
Благодарю Вас за обращение в нашу компанию и надеюсь на долгосрочное и взаимовыгодное сотрудничество.', 'description',
        1, 1, 1);
insert into technologies (drawing_name, drawing_number, quantity_of_defective_parts,
                          quantity_of_parts_from_workpiece, quantity_of_set_up_parts)
values ('крышка', 'дкшг.100.200.001', 2, 5, 1);
insert into technologies (drawing_name, drawing_number, quantity_of_defective_parts,
                          quantity_of_parts_from_workpiece, quantity_of_set_up_parts)
values ('корпус', 'дкшг.100.200.002', 3, 6, 2);
insert into technologies (drawing_name, drawing_number, quantity_of_defective_parts,
                          quantity_of_parts_from_workpiece, quantity_of_set_up_parts)
values ('кронштейн', 'дкшг.100.200.003', 4, 7, 3);
insert into items(actual_duration, estimated_duration, is_accepted, is_customer_material, quantity, technology_id,
                  price_amount, price_currency)
values (1000, 2000, true, true, 10, 1, 100, 'RUB');
insert into items(actual_duration, estimated_duration, is_accepted, is_customer_material, quantity, technology_id,
                  price_amount, price_currency)
values (2000, 3000, true, true, 10, 2, 200, 'RUB');
insert into items(actual_duration, estimated_duration, is_accepted, is_customer_material, quantity, technology_id,
                  price_amount, price_currency)
values (3000, 4000, true, true, 10, 3, 300, 'RUB');
insert into orders_items(order_id, items_id)
VALUES (1, 1);
insert into orders_items(order_id, items_id)
VALUES (1, 2);
insert into orders_items(order_id, items_id)
VALUES (1, 3);