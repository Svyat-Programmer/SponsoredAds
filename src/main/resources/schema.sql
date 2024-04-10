CREATE TABLE IF NOT EXISTS product (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255),
    category VARCHAR(255),
    price DECIMAL(10, 2),
    serial_number VARCHAR(255)
);