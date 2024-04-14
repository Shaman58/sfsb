ALTER TABLE materials DROP CONSTRAINT materials_gost_key;
ALTER TABLE materials ADD CONSTRAINT materials_gost_unique UNIQUE (gost_1, gost_2);