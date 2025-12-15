CREATE TABLE sku
(
    id                   BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    code                 VARCHAR(64)  NOT NULL UNIQUE,
    name                 VARCHAR(128) NOT NULL,
    description          VARCHAR(256),
    maximum_on_shelf     INTEGER      NOT NULL DEFAULT 0,
    minimum_on_shelf     INTEGER      NOT NULL DEFAULT 0,
    maximum_in_warehouse INTEGER      NOT NULL DEFAULT 0,
    minimum_in_warehouse INTEGER      NOT NULL DEFAULT 0,
    created_at           TIMESTAMP             DEFAULT CURRENT_TIMESTAMP,
    updated_at           TIMESTAMP             DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE inventory
(
    id         BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    sku_id     BIGINT      NOT NULL,
    type       VARCHAR(20) NOT NULL CHECK (type IN ('SHELF', 'WAREHOUSE')),
    quantity   INTEGER     NOT NULL DEFAULT 0,
    created_at TIMESTAMP            DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP            DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT uk_inventory_sku_type UNIQUE (sku_id, type)
);

CREATE TABLE inventory_change_record
(
    id                BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    inventory_id      BIGINT      NOT NULL,
    operation_type    VARCHAR(20) NOT NULL CHECK (operation_type IN ('INITIALIZATION', 'INBOUND', 'OUTBOUND')),
    previous_quantity INTEGER,
    new_quantity      INTEGER,
    created_at        TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    notes             VARCHAR(512)
);