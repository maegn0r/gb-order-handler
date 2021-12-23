create table orders (
                        id bigserial primary key,
                        address varchar(255) not null
);

create table order_items(
                            id bigserial primary key,
                            product_id bigint not null,
                            product_name varchar(255) not null,
                            order_id bigint references orders(id),
                            count int
);