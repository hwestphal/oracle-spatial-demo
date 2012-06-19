connect sys/system@xe as sysdba;

drop user spatialdemo cascade;
create user spatialdemo default tablespace users identified by spatialdemo;
grant all privilege to spatialdemo;

DISCONNECT;

connect spatialdemo/spatialdemo@xe;

CREATE TABLE city (
	id NUMBER(10,0), 
	name VARCHAR2(100) NOT NULL ENABLE, 
	country VARCHAR2(2) NOT NULL ENABLE, 
	population NUMBER(9,0) NOT NULL ENABLE, 
	geometry SDO_GEOMETRY NOT NULL ENABLE, 
	PRIMARY KEY (id)
);

CREATE TABLE country (
	code VARCHAR2(2),
	name VARCHAR2(100) NOT NULL ENABLE, 
	geometry SDO_GEOMETRY NOT NULL ENABLE, 
	PRIMARY KEY (code)
);

INSERT INTO user_sdo_geom_metadata
  (table_name, column_name, diminfo, srid)
VALUES
  ('city',
   'geometry',
   sdo_dim_array(sdo_dim_element('X', -180, 180, 0.005),
                 sdo_dim_element('Y', -90, 90, 0.005)),
   8307);

CREATE INDEX city_geometry_idx
ON city(geometry)
INDEXTYPE IS MDSYS.SPATIAL_INDEX;

INSERT INTO user_sdo_geom_metadata
  (table_name, column_name, diminfo, srid)
VALUES
  ('country',
   'geometry',
   sdo_dim_array(sdo_dim_element('X', -180, 180, 0.005),
                 sdo_dim_element('Y', -90, 90, 0.005)),
   8307);

CREATE INDEX country_geometry_idx
ON country(geometry)
INDEXTYPE IS MDSYS.SPATIAL_INDEX;

exit;
