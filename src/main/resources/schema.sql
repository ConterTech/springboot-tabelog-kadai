-- Project Name : MAGOYAMESHI
-- Date/Time    : 2024/09/14 22:42:05
-- Author       : yamamoto
-- RDBMS Type   : MySQL
-- Application  : A5:SQL Mk-2

-- Stripe情報
DROP TABLE if exists stripe CASCADE;



CREATE TABLE stripe (
    user_id INT NOT NULL COMMENT 'ユーザid',
    customer_id VARCHAR(50) NOT NULL COMMENT '顧客id',
    delete_flag boolean DEFAULT 0 COMMENT '削除フラグ',
    CONSTRAINT stripe_PKC PRIMARY KEY (user_id)
) COMMENT 'Stripe情報';



-- パスワードリセットトークン
DROP TABLE if exists password_reset_tokens CASCADE;



CREATE TABLE password_reset_tokens (
    id INT NOT NULL AUTO_INCREMENT COMMENT 'トークンid',
    user_id INT NOT NULL COMMENT 'ユーザid',
    token VARCHAR(255) NOT NULL COMMENT 'トークン',
    delete_flag boolean DEFAULT 0 COMMENT '削除フラグ',
    CONSTRAINT password_reset_tokens_PKC PRIMARY KEY (id)
) COMMENT 'パスワードリセットトークン';



-- 検証トークン
DROP TABLE if exists verification_tokens CASCADE;



CREATE TABLE verification_tokens (
    id INT NOT NULL AUTO_INCREMENT COMMENT 'トークンid',
    user_id INT NOT NULL COMMENT 'ユーザid',
    token VARCHAR(255) NOT NULL COMMENT 'トークン',
    delete_flag boolean DEFAULT 0 COMMENT '削除フラグ',
    CONSTRAINT verificationToken_PKC PRIMARY KEY (id)
) COMMENT '検証トークン';



-- ユーザ属性
DROP TABLE if exists role CASCADE;



CREATE TABLE role (
    id INT NOT NULL COMMENT '属性id',
    name VARCHAR(20) NOT NULL COMMENT '属性',
    delete_flag boolean DEFAULT 0 COMMENT '削除フラグ',
    CONSTRAINT role_PKC PRIMARY KEY (id)
) COMMENT 'ユーザ属性';



-- お気に入り
DROP TABLE if exists favorite CASCADE;



CREATE TABLE favorite (
    store_id INT NOT NULL COMMENT '店舗id',
    user_id INT NOT NULL COMMENT 'ユーザid',
    delete_flag boolean DEFAULT 0 COMMENT '削除フラグ',
    CONSTRAINT favorite_PKC PRIMARY KEY (store_id, user_id)
) COMMENT 'お気に入り';



-- カテゴリ
DROP TABLE if exists category CASCADE;



CREATE TABLE category (
    category_id INT NOT NULL AUTO_INCREMENT COMMENT 'カテゴリid',
    category VARCHAR(30) NOT NULL COMMENT 'カテゴリ名',
    delete_flag boolean DEFAULT 0 COMMENT '削除フラグ',
    CONSTRAINT category_PKC PRIMARY KEY (category_id)
) COMMENT 'カテゴリ';



-- レビュー
DROP TABLE if exists review CASCADE;



CREATE TABLE review (
    store_id INT NOT NULL COMMENT '店舗id',
    user_id INT NOT NULL COMMENT 'ユーザid',
    review_star INT NOT NULL COMMENT '評価星',
    review_text TEXT NOT NULL COMMENT '評価文',
    delete_flag boolean DEFAULT 0 COMMENT '削除フラグ',
    CONSTRAINT review_PKC PRIMARY KEY (store_id, user_id)
) COMMENT 'レビュー';



-- 予約情報
DROP TABLE if exists reservation CASCADE;



CREATE TABLE reservation (
    reservation_id INT NOT NULL AUTO_INCREMENT COMMENT '予約id',
    store_id INT NOT NULL COMMENT '店舗id',
    user_id INT NOT NULL COMMENT 'ユーザid',
    checkin_time DATETIME NOT NULL COMMENT '開始時間',
    number_of_people INT NOT NULL COMMENT '予約人数',
    remarks TEXT COMMENT '備考欄',
    delete_flag boolean DEFAULT 0 COMMENT '削除フラグ',
    CONSTRAINT reservation_PKC PRIMARY KEY (reservation_id)
) COMMENT '予約情報';



-- ユーザ情報
DROP TABLE if exists user CASCADE;



CREATE TABLE user (
    user_id INT NOT NULL AUTO_INCREMENT COMMENT 'ユーザid',
    name VARCHAR(30) NOT NULL COMMENT '氏名',
    phone_number VARCHAR(15) NOT NULL COMMENT '電話番号',
    post_code VARCHAR(10) NOT NULL COMMENT '郵便番号',
    address VARCHAR(100) NOT NULL COMMENT '住所',
    email VARCHAR(50) NOT NULL COMMENT 'メールアドレス',
    age INT NOT NULL COMMENT '年齢',
    gender VARCHAR(20) NOT NULL COMMENT '性別',
    password VARCHAR(100) NOT NULL COMMENT 'パスワード',
    role_id INT NOT NULL COMMENT '属性',
    enabled boolean NOT NULL COMMENT '有効可否',
    paid_flag boolean DEFAULT 0 COMMENT '有料会員フラグ',
    delete_flag boolean DEFAULT 0 COMMENT '削除フラグ',
    CONSTRAINT user_PKC PRIMARY KEY (user_id)
) COMMENT 'ユーザ情報';



-- 店舗営業時間
DROP TABLE if exists store_business_time CASCADE;



CREATE TABLE store_business_time (
    store_id INT NOT NULL COMMENT '店舗id',
    weekday INT NOT NULL COMMENT '曜日:0：月曜日
1：火曜日
2：水曜日
3：木曜日
4：金曜日
5：土曜日
6：日曜日
7：祝日',
    business_start_time TIME NOT NULL COMMENT '営業開始時間',
    business_end_time TIME NOT NULL COMMENT '営業終了時間',
    rest_flag boolean DEFAULT 0 COMMENT '定休日',
    delete_flag boolean DEFAULT 0 COMMENT '削除フラグ',
    CONSTRAINT store_business_time_PKC PRIMARY KEY (store_id, weekday)
) COMMENT '店舗営業時間';



-- 店舗情報
DROP TABLE if exists store CASCADE;



CREATE TABLE store (
    store_id INT NOT NULL AUTO_INCREMENT COMMENT '店舗id',
    store_name VARCHAR(30) NOT NULL COMMENT '店舗名',
    image_name VARCHAR(300) COMMENT '店舗写真',
    post_code VARCHAR(10) NOT NULL COMMENT '郵便番号',
    address VARCHAR(100) NOT NULL COMMENT '住所',
    phone_number VARCHAR(15) NOT NULL COMMENT '電話番号',
    parking_storage INT NOT NULL COMMENT '駐車場台数',
    store_describe TEXT NOT NULL COMMENT '店舗説明',
    start_time TIME NOT NULL COMMENT '営業開始時間',
    close_time TIME NOT NULL COMMENT '営業終了時間',
    rest VARCHAR(20) COMMENT '定休日',
    category_id INT NOT NULL COMMENT 'カテゴリid',
    delete_flag boolean DEFAULT 0 COMMENT '削除フラグ',
    CONSTRAINT store_PKC PRIMARY KEY (store_id)
) COMMENT '店舗情報:店舗情報';