CREATE TABLE sku
(
    id                          BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    code                        VARCHAR(65)  NOT NULL UNIQUE,
    name                        VARCHAR(255) NOT NULL,
    description                 VARCHAR(1000),
    maximum_shelf_inventory     INTEGER      NOT NULL DEFAULT 0,
    minimum_shelf_inventory     INTEGER      NOT NULL DEFAULT 0,
    maximum_warehouse_inventory INTEGER      NOT NULL DEFAULT 0,
    minimum_warehouse_inventory INTEGER      NOT NULL DEFAULT 0,
    created_at                  TIMESTAMP             DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE inventory
(
    id       BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    sku_id   BIGINT      NOT NULL,
    type     VARCHAR(20) NOT NULL CHECK (type IN ('SHELF', 'WAREHOUSE')),
    quantity INTEGER     NOT NULL DEFAULT 0
);

CREATE TABLE inventory_change_record
(
    id                BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    inventory_id      BIGINT      NOT NULL,
    inventory_type    VARCHAR(20) NOT NULL CHECK (inventory_type IN ('SHELF', 'WAREHOUSE')),
    operation_type    VARCHAR(20) NOT NULL CHECK (operation_type IN ('INBOUND', 'OUTBOUND')),
    quantity          INTEGER     NOT NULL,
    previous_quantity INTEGER,
    new_quantity      INTEGER,
    operation_time    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    notes             VARCHAR(500)
);