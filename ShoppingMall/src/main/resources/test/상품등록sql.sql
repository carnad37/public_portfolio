CREATE TABLE product (
	`p_id`       INT(11) auto_increment PRIMARY KEY    NOT NULL, -- 상품번호
	`c_id`       INT(11)      NOT NULL, -- 카테고리번호
	`p_name`     VARCHAR(50)  NOT NULL, -- 상품이름
	`p_text`     VARCHAR(500) NOT NULL, -- 상품내용
	`p_title`    VARCHAR(50)  NOT NULL, -- 상품제목
	`p_price`    INT(10)      NOT NULL, -- 가격
	`p_quantity` INT(10)      NOT NULL  -- 수량
);

INSERT INTO product VALUES (null,1,"name","content","title",10000,100);

select * from product;

create database shopping_mall;