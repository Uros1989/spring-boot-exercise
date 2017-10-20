insert into SENSOR (ID, SENSOR_PUBLIC_ID) values (1, 'sensor-longstreet-45');
insert into SENSOR (ID, SENSOR_PUBLIC_ID) values (2, 'sensor-bertastreet-21');
insert into SENSOR (ID, SENSOR_PUBLIC_ID) values (3, 'sensor-werdstreet-2');


insert into MEASUREMENT (ID, SENSOR_ID, VALUE, TIMESTAMP) values (1, 3, 2.2235, '2005-05-13 07:15:31.123456789');
insert into MEASUREMENT (ID, SENSOR_ID, VALUE, TIMESTAMP) values (2, 3, 5.435, '2005-05-13 07:15:31.123456789');
insert into MEASUREMENT (ID, SENSOR_ID, VALUE, TIMESTAMP) values (3, 3, 7.435, '2005-05-13 07:15:31.123456789');
insert into MEASUREMENT (ID, SENSOR_ID, VALUE, TIMESTAMP) values (4, 2, 22.435, '2005-05-13 07:15:31.123456789');

insert into MEDIAN (ID, SENSOR_ID, VALUE, TIMESTAMP) values (1, 3, 2.2235, '2005-05-13 07:15:31.123456789');

insert into MEASUREMENT (ID, SENSOR_ID, VALUE, TIMESTAMP) values (6, 2, 2.435, '1990-05-13 07:15:31.123456789');

insert into MEASUREMENT (ID, SENSOR_ID, VALUE, TIMESTAMP) values (5, 2, 1.435, '1990-04-13 07:10:31.123456789');

insert into MEASUREMENT (ID, SENSOR_ID, VALUE, TIMESTAMP) values (7, 2, 3.435, '1990-06-13 07:20:31.123456789');