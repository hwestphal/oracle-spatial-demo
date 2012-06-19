insert into country select s.na2, s.na2_descri, s.geometry from shapefile s where s.na2 != 'NULL';
drop table shapefile;
