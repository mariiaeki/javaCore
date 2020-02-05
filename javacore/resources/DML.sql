insert cargos(cargo_id, cargo_name, weight, cargo_type, size, gender) 
values(1, 'JEANS', 100, 'OUTFIT', 42, 'FEMALE'),
(2, 'SHIRT', 200, 'OUTFIT', 46, 'MALE'),
(3, 'DRESS', 200, 'OUTFIT', 46, 'FEMALE');
insert cargos(cargo_id, cargo_name, weight, cargo_type, date_of_expire, store_temperature)
values
(4, 'APPLE', 100, 'PERISHABLE', '2020-01-25', -5),
(5, 'ORANGE', 400, 'PERISHABLE', '2020-03-02', -2),
(6, 'APPLE', 300, 'PERISHABLE', '2020-02-13', -5);
insert carriers(carrier_id, carrier_name, addres, carrier_type)
values
(7, 'FAST_DELIVERY', 'Spb, Nevskiy 8 ', 'CAR'),
(8, 'SLOWEST EVER', 'Spb, Lenina 90', 'TRAIN'),
(9, 'ONE WAY', 'Spb, Aviatornaya 1', 'PLANE');
insert transportations(transportation_id, cargo_id, carrier_id, description, billTo, date)
values
(10, 2, 7, 'fragile', 'Kostin Michail', '2020-01-30 12:20:00'),
(11, 1, 9, 'vip_order', 'Petrov Ivan', '2020-01-29 16:03:00'),
(12, 5, 8, 'fragile','Ivanova Kristina', '2020-02-01 08:16:00');

