-- Project Name : MAGOYAMESHI
-- Date/Time    : 2024/08/20 23:03:03
-- Author       : yamamoto
-- RDBMS Type   : MySQL
-- Application  : A5:SQL Mk-2
/*
 << 注意！！ >>
 BackupToTempTable, RestoreFromTempTable疑似命令が付加されています。
 これにより、drop table, CREATE TABLE IF NOT EXISTS 後もデータが残ります。
 この機能は一時的に $$TableName のような一時テーブルを作成します。
 この機能は A5:SQL Mk-2でのみ有効であることに注意してください。
 */
-- お気に入り
-- * RestoreFromTempTable
CREATE TABLE IF NOT EXISTS favorite (
    store_id INT NOT NULL COMMENT '店舗id',
    user_id INT NOT NULL COMMENT 'ユーザid',
    delete_flag boolean DEFAULT 0 COMMENT '削除フラグ',
    CONSTRAINT favorite_PKC PRIMARY KEY (store_id, user_id)
) COMMENT 'お気に入り';

-- 料理写真
-- * RestoreFromTempTable
CREATE TABLE IF NOT EXISTS menu_photo (
    store_id INT NOT NULL COMMENT '店舗id',
    menu_id INT NOT NULL COMMENT 'メニューid',
    photo_url VARCHAR(300) NOT NULL COMMENT '写真',
    delete_flag boolean DEFAULT 0 COMMENT '削除フラグ',
    CONSTRAINT menu_photo_PKC PRIMARY KEY (store_id, menu_id)
) COMMENT '料理写真';

-- メニュー
-- * RestoreFromTempTable
CREATE TABLE IF NOT EXISTS menu (
    menu_id INT NOT NULL COMMENT 'メニューid',
    store_id INT NOT NULL COMMENT '店舗id',
    menu VARCHAR(50) NOT NULL COMMENT 'メニュー',
    price INT NOT NULL COMMENT '価格',
    delete_flag boolean DEFAULT 0 COMMENT '削除フラグ',
    CONSTRAINT menu_PKC PRIMARY KEY (menu_id, store_id)
) COMMENT 'メニュー';

-- カテゴリ
-- * RestoreFromTempTable
CREATE TABLE IF NOT EXISTS category (
    category_id INT NOT NULL COMMENT 'カテゴリid',
    category VARCHAR(30) NOT NULL COMMENT 'カテゴリ名',
    delete_flag boolean DEFAULT 0 COMMENT '削除フラグ',
    CONSTRAINT category_PKC PRIMARY KEY (category_id)
) COMMENT 'カテゴリ';

-- レビュー
-- * RestoreFromTempTable
CREATE TABLE IF NOT EXISTS review (
    store_id INT NOT NULL COMMENT '店舗id',
    user_id INT NOT NULL COMMENT 'ユーザid',
    review_star INT NOT NULL COMMENT '評価星',
    review_text TEXT NOT NULL COMMENT '評価文',
    delete_flag boolean DEFAULT 0 COMMENT '削除フラグ',
    CONSTRAINT review_PKC PRIMARY KEY (store_id, user_id)
) COMMENT 'レビュー';

-- 予約情報
-- * RestoreFromTempTable
CREATE TABLE IF NOT EXISTS reservation (
    store_id INT NOT NULL COMMENT '店舗id',
    user_id INT NOT NULL COMMENT 'ユーザid',
    checkin_time TIME NOT NULL COMMENT '開始時間',
    number_of_people INT NOT NULL COMMENT '予約人数',
    remarks TEXT NOT NULL COMMENT '備考欄',
    delete_flag boolean DEFAULT 0 COMMENT '削除フラグ',
    CONSTRAINT reservation_PKC PRIMARY KEY (store_id, user_id)
) COMMENT '予約情報';

-- ユーザ情報
-- * RestoreFromTempTable
CREATE TABLE IF NOT EXISTS user (
    user_id INT NOT NULL COMMENT 'ユーザid',
    name VARCHAR(30) NOT NULL COMMENT '氏名',
    phone_number VARCHAR(15) NOT NULL COMMENT '電話番号',
    post_code VARCHAR(10) NOT NULL COMMENT '郵便番号',
    address VARCHAR(100) NOT NULL COMMENT '住所',
    mail_address VARCHAR(50) NOT NULL COMMENT 'メールアドレス',
    age INT NOT NULL COMMENT '年齢',
    gender VARCHAR(20) NOT NULL COMMENT '性別',
    admin_flag boolean DEFAULT 0 COMMENT '管理者フラグ',
    paid_flag boolean DEFAULT 0 COMMENT '有料会員フラグ',
    password VARCHAR(100) NOT NULL COMMENT 'パスワード',
    delete_flag boolean DEFAULT 0 COMMENT '削除フラグ',
    CONSTRAINT user_PKC PRIMARY KEY (user_id)
) COMMENT 'ユーザ情報';

-- 店舗特別営業時間
-- * RestoreFromTempTable
CREATE TABLE IF NOT EXISTS store_special_business_time (
    store_id INT NOT NULL COMMENT '店舗id',
    special_business_day DATE NOT NULL COMMENT '特別営業日',
    business_start_time TIME NOT NULL COMMENT '営業開始時間',
    business_end_time TIME NOT NULL COMMENT '営業終了時間',
    rest_flag boolean DEFAULT 0 COMMENT '定休日',
    delete_flag boolean DEFAULT 0 COMMENT '削除フラグ',
    CONSTRAINT store_special_business_time_PKC PRIMARY KEY (store_id, special_business_day)
) COMMENT '店舗特別営業時間';

-- 店舗営業時間
-- * RestoreFromTempTable
CREATE TABLE IF NOT EXISTS store_business_time (
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
-- * RestoreFromTempTable
CREATE TABLE IF NOT EXISTS store (
    store_id INT NOT NULL COMMENT '店舗id',
    store_name VARCHAR(30) NOT NULL COMMENT '店舗名',
    post_code VARCHAR(10) NOT NULL COMMENT '郵便番号',
    address VARCHAR(100) NOT NULL COMMENT '住所',
    phone_number VARCHAR(15) NOT NULL COMMENT '電話番号',
    parking_storage INT NOT NULL COMMENT '駐車場台数',
    store_describe TEXT NOT NULL COMMENT '店舗説明',
    delete_flag boolean DEFAULT 0 COMMENT '削除フラグ',
    CONSTRAINT store_PKC PRIMARY KEY (store_id)
) COMMENT '店舗情報:店舗情報';