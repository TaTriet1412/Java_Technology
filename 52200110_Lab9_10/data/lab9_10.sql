-- Tạo bảng cho model 'User'
CREATE TABLE user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);

-- Tạo bảng cho model 'Product'
CREATE TABLE product (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    productName VARCHAR(255) NOT NULL,
    price DOUBLE NOT NULL,
    description TEXT NOT NULL,
    image LONGBLOB
);

-- Tạo bảng cho model 'Order'
CREATE TABLE `orders` (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    total_price DOUBLE NOT NULL
);

-- Tạo bảng liên kết giữa 'Order' và 'Product' (Many-to-Many)
CREATE TABLE order_product (
    order_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    PRIMARY KEY (order_id, product_id),
    FOREIGN KEY (order_id) REFERENCES `orders`(id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES product(id) ON DELETE CASCADE
);

-- Chèn dữ liệu vào bảng User
INSERT INTO user (email, username, password) 
VALUES 
('user1@example.com', 'user1', 'password1'),
('user2@example.com', 'user2', 'password2'),
('user3@example.com', 'user3', 'password3');

-- Chèn dữ liệu vào bảng Product (giả sử không có hình ảnh, để NULL cho cột image)
INSERT INTO product (productName, price, description, image)
VALUES 
('Product 1', 100.0, 'Description for Product 1', NULL),
('Product 2', 150.0, 'Description for Product 2', NULL),
('Product 3', 200.0, 'Description for Product 3', NULL);

-- Chèn dữ liệu vào bảng Order
INSERT INTO `orders` (total_price) 
VALUES 
(250.0),
(450.0),
(350.0);

-- Chèn dữ liệu vào bảng order_product để kết nối Order và Product
-- Giả sử Order ID và Product ID đã được tạo tự động (AUTO_INCREMENT) và bạn biết các ID này.
-- Ví dụ, chúng tôi sẽ sử dụng các ID sau:
-- Order 1, Order 2, Order 3
-- Product 1, Product 2, Product 3

-- Chèn dữ liệu vào bảng order_product (mối quan hệ giữa Order và Product)
INSERT INTO order_product (order_id, product_id)
VALUES 
(1, 1), -- Order 1 chứa Product 1
(1, 2), -- Order 1 chứa Product 2
(2, 2), -- Order 2 chứa Product 2
(2, 3), -- Order 2 chứa Product 3
(3, 1), -- Order 3 chứa Product 1
(3, 3); -- Order 3 chứa Product 3
