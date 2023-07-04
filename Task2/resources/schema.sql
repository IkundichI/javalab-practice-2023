create table product
(
    id              serial primary key,
    name            varchar(255),
    price           integer,
    production_date date,
    valid_until     date
);

create table customer
(
    id         serial primary key,
    last_name  varchar(20),
    first_name varchar(20),
    company    varchar(255),
    inn        integer
);

create table store
(
    id      serial primary key,
    name    varchar(255),
    address varchar(255)
);

create table product_customer
(
    store_id    integer,
    product_id  integer,
    customer_id integer,
    foreign key (store_id) references store (id),
    foreign key (product_id) references product (id),
    foreign key (customer_id) references customer (id)

);

create table product_store
(
    store_id   integer,
    product_id integer,
    foreign key (store_id) references store (id),
    foreign key (product_id) references product (id)
);