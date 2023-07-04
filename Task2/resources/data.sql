
INSERT INTO product (name, price, production_date, valid_until)
VALUES
    ('Телефон', 1000, '2023-01-01', '2024-01-01'),
    ('Ноутбук', 2000, '2023-02-01', '2024-02-01'),
    ('Телевизор', 1500, '2023-03-01', '2024-03-01');

INSERT INTO customer (last_name, first_name, company, inn)
VALUES
    ('Иванов', 'Иван', 'ООО Альфа', 123456789),
    ('Петров', 'Петр', 'ЗАО Бета', 987654321),
    ('Сидорова', 'Анна', 'ИП Гамма', 555555555);

INSERT INTO store (name, address)
VALUES
    ('Магазин 1', 'Улица Первая, 1'),
    ('Магазин 2', 'Улица Вторая, 2'),
    ('Магазин 3', 'Улица Третья, 3');

INSERT INTO product_customer (store_id, product_id, customer_id)
VALUES
    (1, 1, 1),
    (1, 2, 1),
    (2, 2, 2),
    (3, 3, 3);

INSERT INTO product_store (store_id, product_id)
VALUES
    (1, 1),
    (1, 2),
    (2, 2),
    (3, 3);