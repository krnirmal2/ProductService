/*CREATE TABLE category
(
    id   BINARY(16)   NOT NULL,
    name VARCHAR(255) NOT NULL,
    CONSTRAINT pk_category PRIMARY KEY (id)
);

CREATE TABLE join_mentor
(
    `user id`  BIGINT NOT NULL,
    avg_rating DOUBLE NOT NULL,
    CONSTRAINT pk_join_mentor PRIMARY KEY (`user id`)
);

CREATE TABLE join_student
(
    `user id` BIGINT NOT NULL,
    psp       DOUBLE NOT NULL,
    CONSTRAINT pk_join_student PRIMARY KEY (`user id`)
);

CREATE TABLE join_ta
(
    `user id`   BIGINT       NOT NULL,
    ta_sessions VARCHAR(255) NULL,
    CONSTRAINT pk_join_ta PRIMARY KEY (`user id`)
);

CREATE TABLE join_user
(
    id    BIGINT       NOT NULL,
    email VARCHAR(255) NULL,
    name  VARCHAR(255) NULL,
    CONSTRAINT pk_join_user PRIMARY KEY (id)
);

CREATE TABLE ms_mentor
(
    id         BIGINT       NOT NULL,
    email      VARCHAR(255) NULL,
    name       VARCHAR(255) NULL,
    avg_rating DOUBLE       NOT NULL,
    CONSTRAINT pk_ms_mentor PRIMARY KEY (id)
);

CREATE TABLE ms_student
(
    id    BIGINT       NOT NULL,
    email VARCHAR(255) NULL,
    name  VARCHAR(255) NULL,
    psp   DOUBLE       NOT NULL,
    CONSTRAINT pk_ms_student PRIMARY KEY (id)
);

CREATE TABLE ms_ta
(
    id          BIGINT       NOT NULL,
    email       VARCHAR(255) NULL,
    name        VARCHAR(255) NULL,
    ta_sessions VARCHAR(255) NULL,
    CONSTRAINT pk_ms_ta PRIMARY KEY (id)
);

CREATE TABLE orders
(
    id BINARY(16) NOT NULL,
    CONSTRAINT pk_orders PRIMARY KEY (id)
);

CREATE TABLE price
(
    id       BINARY(16)   NOT NULL,
    currency VARCHAR(255) NULL,
    value    DOUBLE       NOT NULL,
    CONSTRAINT pk_price PRIMARY KEY (id)
);

CREATE TABLE product
(
    id              BINARY(16)   NOT NULL,
    title           VARCHAR(255) NULL,
    `description`   VARCHAR(255) NULL,
    prices          INT          NOT NULL,
    image           VARCHAR(255) NULL,
    category_id     BINARY(16)   NULL,
    price_id        BINARY(16)   NULL,
    inventory_count INT          NOT NULL,
    CONSTRAINT pk_product PRIMARY KEY (id)
);

CREATE TABLE products_orders
(
    order_id   BINARY(16) NOT NULL,
    product_id BINARY(16) NOT NULL
);

CREATE TABLE st_user
(
    id          BIGINT       NOT NULL,
    dtype       VARCHAR(31)  NULL,
    email       VARCHAR(255) NULL,
    name        VARCHAR(255) NULL,
    avg_rating  DOUBLE       NOT NULL,
    ta_sessions VARCHAR(255) NULL,
    CONSTRAINT pk_st_user PRIMARY KEY (id)
);

CREATE TABLE tpc_mentor
(
    id         BIGINT       NOT NULL,
    email      VARCHAR(255) NULL,
    name       VARCHAR(255) NULL,
    avg_rating DOUBLE       NOT NULL,
    CONSTRAINT pk_tpc_mentor PRIMARY KEY (id)
);

CREATE TABLE tpc_student
(
    id    BIGINT       NOT NULL,
    email VARCHAR(255) NULL,
    name  VARCHAR(255) NULL,
    psp   DOUBLE       NOT NULL,
    CONSTRAINT pk_tpc_student PRIMARY KEY (id)
);

CREATE TABLE tpc_ta
(
    id          BIGINT       NOT NULL,
    email       VARCHAR(255) NULL,
    name        VARCHAR(255) NULL,
    ta_sessions VARCHAR(255) NULL,
    CONSTRAINT pk_tpc_ta PRIMARY KEY (id)
);

CREATE TABLE tpc_user
(
    id    BIGINT       NOT NULL,
    email VARCHAR(255) NULL,
    name  VARCHAR(255) NULL,
    CONSTRAINT pk_tpc_user PRIMARY KEY (id)
);

ALTER TABLE category
    ADD CONSTRAINT uc_category_name UNIQUE (name);

ALTER TABLE join_mentor
    ADD CONSTRAINT FK_JOIN_MENTOR_ON_USER FOREIGN KEY (`user id`) REFERENCES join_user (id);

ALTER TABLE join_student
    ADD CONSTRAINT FK_JOIN_STUDENT_ON_USER FOREIGN KEY (`user id`) REFERENCES join_user (id);

ALTER TABLE join_ta
    ADD CONSTRAINT FK_JOIN_TA_ON_USER FOREIGN KEY (`user id`) REFERENCES join_user (id);

ALTER TABLE product
    ADD CONSTRAINT FK_PRODUCT_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES category (id);

ALTER TABLE product
    ADD CONSTRAINT FK_PRODUCT_ON_PRICE FOREIGN KEY (price_id) REFERENCES price (id);

ALTER TABLE products_orders
    ADD CONSTRAINT fk_proord_on_order FOREIGN KEY (order_id) REFERENCES orders (id);

ALTER TABLE products_orders
    ADD CONSTRAINT fk_proord_on_product FOREIGN KEY (product_id) REFERENCES product (id);*/