DROP TABLE IF EXISTS public.users CASCADE;
CREATE TABLE public.users
(
  id    serial UNIQUE,
  username text,
  hashed_pw varchar(255),
  email varchar(255)
);

DROP TABLE IF EXISTS public.product_categories CASCADE ;
CREATE TABLE public.product_categories
(
  id    serial NOT NULL,
  name varchar(255) UNIQUE,
  department varchar(255),
  description varchar(255)
);

DROP TABLE IF EXISTS public.products CASCADE;
CREATE TABLE public.products
(
  id    serial NOT NULL,
  name text UNIQUE,
  category_id int,
  supplier_id int,
  price varchar(255)
);

DROP TABLE IF EXISTS public.suppliers CASCADE;
CREATE TABLE public.suppliers
(
  id    serial NOT NULL,
  name text UNIQUE,
  description varchar(255)
);

DROP TABLE IF EXISTS public.carts CASCADE;
CREATE TABLE public.carts
(
  id serial NOT NULL,
  quantity int,
  user_id int NOT NULL,
  product_id int
);


ALTER TABLE ONLY public.users
    ADD CONSTRAINT pk_user_id PRIMARY KEY (id);

ALTER TABLE ONLY public.product_categories
    ADD CONSTRAINT pk_productCategory_id PRIMARY KEY (id);

ALTER TABLE ONLY public.products
    ADD CONSTRAINT pk_product_id PRIMARY KEY (id);

ALTER TABLE ONLY public.suppliers
    ADD CONSTRAINT pk_supplier_id PRIMARY KEY (id);

ALTER TABLE ONLY public.carts
    ADD CONSTRAINT pk_cart_id PRIMARY KEY (id);


ALTER TABLE ONLY public.products
    ADD CONSTRAINT fk_category_id FOREIGN KEY (category_id) REFERENCES product_categories (id) ON DELETE CASCADE;


ALTER TABLE ONLY public.products
    ADD CONSTRAINT fk_supplier_id FOREIGN KEY (supplier_id) REFERENCES suppliers (id) ON DELETE CASCADE;


ALTER TABLE ONLY public.carts
    ADD CONSTRAINT fk_product_id FOREIGN KEY (product_id) REFERENCES products (id) ON DELETE CASCADE;


ALTER TABLE ONLY public.carts
    ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE;


-- INSERT INTO users
-- VALUES (1, 'kiscsavo', 'lofaszjozsi', 'nagya@om.hu');
--
-- INSERT INTO users
-- VALUES (2, 'nagycsavo', 'beluka', 'hozentroger@gmail.com');
--
-- INSERT INTO users
-- VALUES (3, 'adsasd', 'nemasd', 'igen@nem.hu');
--
--
-- INSERT INTO suppliers (name, description)
-- VALUES ('huavej', 'electronics');
--
-- INSERT INTO suppliers (name, description)
-- VALUES ('simisami', 'something');
--
-- INSERT INTO product_categories
-- VALUES (1, 'laptop', 'first dep', 'this is something');
--
-- INSERT INTO product_categories
-- VALUES (2, 'tablet', 'second dep', 'livejasmin is not a porn site');
--
-- INSERT INTO products
-- VALUES (1, 'asuslaptop', 1, 2);
--
-- INSERT INTO products
-- VALUES (2, 'hiroshimanagasaki', 2, 1);
--
-- INSERT INTO carts
-- VALUES (1, 2, 1, 2);
--
-- INSERT INTO carts
-- VALUES (3, 1, 1, 2);