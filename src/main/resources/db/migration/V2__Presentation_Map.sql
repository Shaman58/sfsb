create table dictionary
(
    id           bigserial not null,
    db_name      varchar(255),
    presentation varchar(255)
);
insert into dictionary (db_name, presentation)
VALUES ('materialName', 'Материал');
insert into dictionary (db_name, presentation)
VALUES ('density', 'Плотность');
insert into dictionary (db_name, presentation)
VALUES ('priceAmount', 'Цена');
insert into dictionary (db_name, presentation)
VALUES ('priceCurrency', 'Валюта');
insert into dictionary (db_name, presentation)
VALUES ('dateOfActuality', 'Дата актуализации');
insert into materials (date_of_actuality, density, material_name, price_amount, price_currency)
VALUES ('2023-04-13T15:50:01', 1234, 'material1', 10000, 'RUB');
insert into materials (date_of_actuality, density, material_name, price_amount, price_currency)
VALUES ('2023-04-13T15:50:01', 2345, 'material2', 20000, 'RUB');
insert into materials (date_of_actuality, density, material_name, price_amount, price_currency)
VALUES ('2023-04-13T15:50:01', 3456, 'material3', 30000, 'RUB');