update companies
set company_type='Company'
where id = 1;
delete
from materials
where id > 0;
insert into materials (material_name, geometry, gost_1, gost_2, density)
VALUES ('Сплав Д16Т', 'CYLINDER', 'ГОСТ 21488-97', 'ГОСТ 21488-97', 2700);
insert into materials (material_name, geometry, gost_1, gost_2, density)
VALUES ('Сплав Д16Т', 'TUBE', 'ГОСТ 18482-79', 'ГОСТ 18482-79', 2700);
insert into materials (material_name, geometry, gost_1, gost_2, density)
VALUES ('Сплав Д16Т', 'BLANK', 'ГОСТ 17232-99', 'ГОСТ 17232-99', 2700);
insert into materials (material_name, geometry, gost_1, gost_2, density)
VALUES ('Сталь 40х', 'CYLINDER', 'ГОСТ 1051-73', 'ГОСТ 1051-73', 7800);
insert into materials (material_name, geometry, gost_1, gost_2, density)
VALUES ('Сталь 40х', 'CYLINDER', 'ГОСТ 10702-2016', 'ГОСТ 10702-2016', 7800);
insert into materials (material_name, geometry, gost_1, gost_2, density)
VALUES ('Сталь 40х', 'CYLINDER', 'ГОСТ 4543-2016', 'ГОСТ 4543-2016', 7800);