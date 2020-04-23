drop table IF EXISTS customers;

create TABLE customers (
        id LONG NOT NULL AUTO_INCREMENT,
        name VARCHAR(250) NOT NULL,
        surname VARCHAR(250) NOT NULL,
        company_name VARCHAR(250) DEFAULT NULL,
        company_code LONG DEFAULT NULL,
        personal_code LONG DEFAULT NULL,
        address VARCHAR(250) DEFAULT NULL,
        PRIMARY KEY(id)
);


drop table IF EXISTS offered_services;

create TABLE offered_services (
        id LONG NOT NULL AUTO_INCREMENT,
        name VARCHAR(250) NOT NULL,
        type VARCHAR(250) NOT NULL,
        description VARCHAR(250) DEFAULT NULL,
        PRIMARY KEY(id)
);

drop table IF EXISTS ordered_services;

create TABLE ordered_services (
        id LONG NOT NULL AUTO_INCREMENT,
        active_from DATE DEFAULT NULL,
        active_to DATE DEFAULT NULL,
        offered_service_id LONG,
        customer_id LONG,
        msisdn_id LONG,
        PRIMARY KEY(id),
        foreign KEY(offered_service_id) references offered_services(id) ON DELETE CASCADE,
        foreign KEY(customer_id) references customers(id) ON DELETE CASCADE,
        foreign KEY(msisdn_id) references msisdns(id)  ON DELETE CASCADE
);

drop table IF EXISTS accounts;

create TABLE accounts (
        id LONG NOT NULL AUTO_INCREMENT,
        name VARCHAR(250) NOT NULL,
        address VARCHAR(250) DEFAULT NULL,
        customer_id LONG,
        PRIMARY KEY(id),
        foreign KEY(customer_id) references customers(id) ON DELETE CASCADE

);

drop table IF EXISTS msisdns;

create TABLE msisdns (
        id LONG NOT NULL AUTO_INCREMENT,
        active_from DATE DEFAULT NULL,
        active_to DATE DEFAULT NULL,
                account_id LONG,
        PRIMARY KEY(id),
        foreign KEY(account_id) references accounts(id) ON DELETE CASCADE,
);

drop table IF EXISTS phone_numbers;

create TABLE phone_numbers (
        id LONG NOT NULL AUTO_INCREMENT,
        country_code INTEGER DEFAULT NULL,
        ph_number LONG DEFAULT NULL,
        msisdn_id LONG,
        PRIMARY KEY(id),
        foreign KEY(msisdn_id) references msisdns(id) ON DELETE CASCADE
);