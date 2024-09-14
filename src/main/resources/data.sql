-- storeテーブル
INSERT IGNORE INTO store (store_id, store_name, image_name, post_code, address, phone_number, parking_storage, store_describe, category_id, delete_flag) VALUES (1, '焼き鳥ドン', 'store1.jpg', '111-2222', '神奈川県厚木市XXXX', '123-4567-8901', 15, '焼き鳥がおいしいお店です。', 1, 0);
INSERT IGNORE INTO store (store_id, store_name, image_name, post_code, address, phone_number, parking_storage, store_describe, category_id, delete_flag) VALUES (2, 'イタリアーノ', 'store2.jpg', '111-2222', '神奈川県厚木市XXXX', '123-4567-8901', 0, 'イタリアンと言ったらここ。', 2, 0);
INSERT IGNORE INTO store (store_id, store_name, image_name, post_code, address, phone_number, parking_storage, store_describe, category_id, delete_flag) VALUES (3, '私のフレンチ', 'store3.jpg', '111-2222', '神奈川県厚木市XXXX', '123-4567-8901', 20, 'フレンチと言ったらここ。', 3, 0);
INSERT IGNORE INTO store (store_id, store_name, image_name, post_code, address, phone_number, parking_storage, store_describe, category_id, delete_flag) VALUES (4, '和食宮', 'store4.jpg', '111-2222', '神奈川県厚木市XXXX', '123-4567-8901', 30, '荘厳な雰囲気のお店です。', 4, 0);
INSERT IGNORE INTO store (store_id, store_name, image_name, post_code, address, phone_number, parking_storage, store_describe, category_id, delete_flag) VALUES (5, 'スイーツワールド', 'store5.jpg', '111-2222', '神奈川県厚木市XXXX', '123-4567-8901', 5, 'ショコラケーキに力を入れています。', 5, 0);
INSERT IGNORE INTO store (store_id, store_name, image_name, post_code, address, phone_number, parking_storage, store_describe, category_id, delete_flag) VALUES (6, 'フルーツ天国', 'store6.jpg', '111-2222', '神奈川県厚木市XXXX', '123-4567-8901', 15, 'フルーツに囲まれて幸せ。', 6, 0);
INSERT IGNORE INTO store (store_id, store_name, image_name, post_code, address, phone_number, parking_storage, store_describe, category_id, delete_flag) VALUES (7, 'メキシコ', 'store7.jpg', '111-2222', '神奈川県厚木市XXXX', '123-4567-8901', 15, '絶品タコス。', 7, 0);
INSERT IGNORE INTO store (store_id, store_name, image_name, post_code, address, phone_number, parking_storage, store_describe, category_id, delete_flag) VALUES (8, 'マククナルド', 'store8.jpg', '111-2222', '神奈川県厚木市XXXX', '123-4567-8901', 15, 'ジャンクフードと言ったらここ。', 8, 0);
INSERT IGNORE INTO store (store_id, store_name, image_name, post_code, address, phone_number, parking_storage, store_describe, category_id, delete_flag) VALUES (9, '行列のできる唐揚げ', 'store9.jpg', '111-2222', '神奈川県厚木市XXXX', '123-4567-8901', 15, 'ジューシーなから揚げ。', 9, 0);
INSERT IGNORE INTO store (store_id, store_name, image_name, post_code, address, phone_number, parking_storage, store_describe, category_id, delete_flag) VALUES (10, 'イ・タリア', 'store10.jpg', '111-2222', '神奈川県厚木市XXXX', '123-4567-8901', 15, 'イタリアの風を感じたいならここ。', 10, 0);
INSERT IGNORE INTO store (store_id, store_name, image_name, post_code, address, phone_number, parking_storage, store_describe, category_id, delete_flag) VALUES (11, 'ソ・フランス', 'store11.jpg', '111-2222', '神奈川県厚木市XXXX', '123-4567-8901', 20, 'xx駅徒歩5分のお店です。', 11, 0);


-- roleテーブル
INSERT IGNORE INTO role (role_id, name, delete_flag) VALUES (1, 'ROLE_GENERAL', 0);
INSERT IGNORE INTO role (role_id, name, delete_flag) VALUES (2, 'ROLE_ADMIN', 0);

-- userテーブル
INSERT IGNORE INTO user (user_id, name, phone_number, post_code, address, email, age, gender, password, role, enabled, paid_flag, delete_flag) VALUES (1, '侍　太郎', '080-3333-5555', '111-1111', '宮城県太田市xxx', 'taro.samurai@example.com', 27, 1, 'password', 1, true, 0, 0);

-- categoryテーブル
INSERT IGNORE INTO category (category_id, category, delete_flag) VALUES (1, '焼き鳥', 0);
INSERT IGNORE INTO category (category_id, category, delete_flag) VALUES (2, 'イタリアン', 0);
INSERT IGNORE INTO category (category_id, category, delete_flag) VALUES (3, 'フレンチ', 0);
INSERT IGNORE INTO category (category_id, category, delete_flag) VALUES (4, '和食', 0);
INSERT IGNORE INTO category (category_id, category, delete_flag) VALUES (5, 'スイーツ', 0);
INSERT IGNORE INTO category (category_id, category, delete_flag) VALUES (6, 'フルーツ', 0);
INSERT IGNORE INTO category (category_id, category, delete_flag) VALUES (7, 'メキシコ料理', 0);
INSERT IGNORE INTO category (category_id, category, delete_flag) VALUES (8, 'ジャンクフード', 0);