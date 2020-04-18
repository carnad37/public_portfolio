-- 회원정보
DROP TABLE IF EXISTS `user_table` RESTRICT;
CREATE TABLE `user_table` (
	`u_id`          INT(11)      primary key auto_increment, -- 유저번호
	`u_account`     VARCHAR(15)  UNIQUE, -- 아이디계정
	`u_nick`        VARCHAR(15)  UNIQUE, -- 닉네임
	`u_pw`          VARCHAR(100)  NOT NULL, -- 비밀번호
	`u_zipcode`     CHAR(5)      NOT NULL, -- 우편번호
	`u_addr`        VARCHAR(100) NOT NULL, -- 주소
	`u_addr_detail` VARCHAR(50)  NOT NULL, -- 상세주소
	`u_mail`        VARCHAR(30)  NOT NULL, -- 메일
	`u_phone`       char(13)     NOT NULL,  -- 전화번호
    `u_date` 		TIMESTAMP	 NOT NULL
);

DROP TABLE IF EXISTS `qna_question_table` RESTRICT;
-- 상담 게시판
CREATE TABLE `qna_question_table` (
	`que_id`     INT(11)      primary key auto_increment, -- 게시물번호
	`u_id`       INT(11)      NULL,     -- 유저번호(상담자)
	`que_secret` INT(1)       NOT NULL, -- 비밀글설정여부
	`que_title`  VARCHAR(50)  NOT NULL, -- 질문명
	`que_text`   VARCHAR(500) NOT NULL, -- 질문내용
	`que_date`   DATE         NOT NULL, -- 등록일
	`que_check`  INT(1)       NOT NULL, -- 답변여부
	`que_exist`  INT(1)       NOT NULL  -- 삭제여부
);

DROP TABLE IF EXISTS `user_role_table` RESTRICT;
CREATE TABLE `user_role_table` (
	`u_id`     INT(11)     NOT NULL, -- 유저번호(Parents)
	`u_role`   VARCHAR(40) NOT NULL, -- 유저권한
	`u_enable` VARCHAR(40) NOT NULL  -- 유저허가
);

DROP TABLE IF EXISTS `product_table` RESTRICT;
CREATE TABLE `product_table` (
	`p_id`       INT(11)      PRIMARY KEY AUTO_INCREMENT, -- 상품번호
	`u_id`       INT(11)      NULL,     -- 유저번호(판매자)
	`c_id`       INT(11)      NOT NULL, -- 카테고리번호
	`p_name`     VARCHAR(50)  NOT NULL, -- 상품이름
	`p_text`     VARCHAR(500) NOT NULL, -- 상품내용
	`p_title`    VARCHAR(50)  NOT NULL, -- 상품제목
	`p_price`    INT(10)      NOT NULL, -- 가격
	`p_quantity` INT(10)      NOT NULL  -- 수량
);

DROP TABLE IF EXISTS `category_table` RESTRICT;
CREATE TABLE `category_table` (
	`c_id`   INT(11)     PRIMARY KEY AUTO_INCREMENT, -- 카테고리번호
	`c_name` VARCHAR(50) NOT NULL  -- 카테고리이름
);

CREATE TABLE `banner_images_table` (
	`rb_id`    INT(11)      PRIMARY KEY AUTO_INCREMENT, -- id
	`rb_name`  VARCHAR(50)  NOT NULL, -- 표시명
	`rb_path`  VARCHAR(100) NOT NULL, -- 실제파일명
	`rb_date`  DATE         NOT NULL, -- 업로드일자
	`rb_exist` INT(1)       NOT NULL  -- 삭제여부
);

-- 사이트 설정 테이블
CREATE TABLE `site_config_table` (
	`con_id`        INT(11) PRIMARY KEY AUTO_INCREMENT, -- 식별_ID
	`con_banner_number` INT(2)  NOT NULL  -- 배너 갯수
);

CREATE TABLE persistent_logins (
username varchar(64) not null,
series varchar(64) primary key,
token varchar(64) not null,
last_used timestamp not null
);