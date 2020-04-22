INSERT INTO customers (id, name, surname, personal_code, address) VALUES
    (1, 'Jonas', 'Jonaitis', '30011223344', 'Vilnius, Vasaros g. 5-1'),
    (2, 'Janina', 'Jonaite', '40011223344', 'Vilnius, Vasaros g. 5-2');


INSERT INTO offered_services (id, name, type, description) VALUES
    (1, 'iPhone SE', 'devices', 'iPhone SE - description'),
    (2, 'Lengviau 1', 'call_plans', 'Lengviau 1 - description'),
    (3, 'Internet', 'internet', 'Internet - description'),
    (4, 'Movies', 'tv', 'Movies - description'),
    (5, 'eSIM', 'smart_services', 'eSIM for watches - description');

INSERT INTO ordered_services (id, active_from, active_to, offered_service_id,customer_id, msisdn_id) VALUES
    (1, parsedatetime('31-12-2021', 'dd-MM-yyyy'), null, 1, 1, 1),
    (2, parsedatetime('31-12-2021', 'dd-MM-yyyy'), null, 2, 1, 1),
    (3, parsedatetime('01-01-2020', 'dd-MM-yyyy'), parsedatetime('31-12-2021', 'dd-MM-yyyy'), 3, 2, 2),
    (4, parsedatetime('01-01-2020', 'dd-MM-yyyy'), parsedatetime('31-12-2021', 'dd-MM-yyyy'), 4, 2, 2),
    (5, parsedatetime('01-01-2020', 'dd-MM-yyyy'), parsedatetime('31-12-2021', 'dd-MM-yyyy'), 4, 1, 4);

INSERT INTO accounts (id, name, address, customer_id) VALUES
    (1, 'MyHomeIpTv', 'Vilnius, Vasaros g. 5-1', 1),
    (2, 'MyWorkInternet', 'Vilnius, Vasaros g. 5-2', 2),
    (3, 'Internet', 'Vilnius, Vasaros g. 5-1', 1);

INSERT INTO msisdns (id, active_from, active_to, account_id) VALUES
    (1, parsedatetime('01-01-2020', 'dd-MM-yyyy'), parsedatetime('31-12-2021', 'dd-MM-yyyy'), 1),
    (2, parsedatetime('01-01-2020', 'dd-MM-yyyy'), parsedatetime('31-12-2021', 'dd-MM-yyyy'), 2),
    (3, parsedatetime('01-01-2020', 'dd-MM-yyyy'), parsedatetime('31-12-2021', 'dd-MM-yyyy'), 1),
    (4, parsedatetime('01-01-2020', 'dd-MM-yyyy'), parsedatetime('31-12-2021', 'dd-MM-yyyy'), 3);

INSERT INTO phone_numbers (id, country_code, ph_number, msisdn_id) VALUES
    (1, 370, 69912345, 1),
    (2, 370, 68754321, 1),
    (3, 370, 52320004, 2);