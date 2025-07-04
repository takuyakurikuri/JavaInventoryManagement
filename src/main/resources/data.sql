INSERT INTO stores(name, location, created_at) VALUES
    ('水戸店','茨木県水戸市','2025-06-10 16:15:14.778155'),
    ('我孫子店','茨木県我孫子市','2025-06-10 16:15:14.778155'),
    ('千寿店','茨木県千寿市','2025-06-10 16:15:14.778155'),
    ('荒島店','茨木県荒島市','2025-06-10 16:15:14.778155');


INSERT INTO users (name, email, password, role, store_id, created_at, updated_at) VALUES
    ('マトリクス太郎', 'user01@example.jp','$2a$10$rAtb4JMGYhJhFPCqqCZe9u56gdXSsVDF9gCtRThEva9jf6nwMRrUm', 'ROLE_USER', '1', '2025-06-10 16:15:14.778155', '2025-06-10 16:15:14.778155'),
    ('マトリクス花子', 'admin01@example.jp','$2a$10$rAtb4JMGYhJhFPCqqCZe9u56gdXSsVDF9gCtRThEva9jf6nwMRrUm', 'ROLE_ADMIN', '1', '2025-06-10 16:15:14.778155', '2025-06-10 16:15:14.778155');

INSERT INTO categories (id, name) VALUES
    (1, '箱菓子'),
    (2, 'ビール'),
    (3, '醤油'),
    (4, 'スナック菓子'),
    (5, 'ジュース'),
    (6, '缶詰'),
    (7, 'レトルトカレー'),
    (8, 'インスタントラーメン'),
    (9, 'コーヒー'),
    (10, 'お茶'),
    (11, 'ドレッシング'),
    (12, '冷凍食品'),
    (13, 'アイスクリーム'),
    (14, 'パン'),
    (15, 'ヨーグルト'),
    (16, '調味料セット'),
    (17, '米'),
    (18, 'パスタ'),
    (19, '食用油'),
    (20, 'ふりかけ');

INSERT INTO suppliers (id, name, address, phone) VALUES
    (1, '株式会社マルフク商事', '東京都千代田区1-1-1', '03-1234-5678'),
    (2, '食品卸 三光', '大阪府大阪市北区2-2-2', '06-2345-6789'),
    (3, '大和流通株式会社', '神奈川県横浜市中区3-3-3', '045-3456-7890'),
    (4, 'フードリンク合同会社', '愛知県名古屋市西区4-4-4', '052-4567-8901'),
    (5, '北海フーズ', '北海道札幌市南区5-5-5', '011-5678-9012'),
    (6, '南海物産株式会社', '福岡県福岡市中央区6-6-6', '092-6789-0123'),
    (7, '信州フードサービス', '長野県長野市大字7-7-7', '026-7890-1234'),
    (8, '山陽食品株式会社', '岡山県岡山市北区8-8-8', '086-8901-2345'),
    (9, '中部食材センター', '静岡県静岡市駿河区9-9-9', '054-9012-3456'),
    (10, '東北物産合同会社', '宮城県仙台市青葉区10-10-10', '022-0123-4567');


INSERT INTO items (id, jan_code, name, category_id, stock_std, order_unit, price, created_at, updated_at, supplier_id) VALUES
(1, 4901234567890, 'チョコレートギフトボックス', 1, 50, 1000, 800, '2025-06-10 16:15:14.778155', '2025-06-10 16:15:14.778155', 1),
(2, 4901234567891, 'アサヒスーパードライ 350ml 6缶パック', 2, 100, 1200, 1200, '2025-06-10 16:15:14.778155', '2025-06-10 16:15:14.778155', 2),
(3, 4901234567892, 'キッコーマン 特選丸大豆しょうゆ', 3, 80, 300, 450, '2025-06-10 16:15:14.778155', '2025-06-10 16:15:14.778155', 3),
(4, 4901234567893, 'ポテトチップス うすしお味', 4, 150, 150, 130, '2025-06-10 16:15:14.778155', '2025-06-10 16:15:14.778155', 1),
(5, 4901234567894, 'オレンジジュース 1L', 5, 60, 120, 160, '2025-06-10 16:15:14.778155', '2025-06-10 16:15:14.778155', 2),
(6, 4901234567895, 'シーチキン マイルド 70g×3缶', 6, 70, 250, 300, '2025-06-10 16:15:14.778155', '2025-06-10 16:15:14.778155', 3),
(7, 4901234567896, '銀座カリー 中辛', 7, 90, 300, 350, '2025-06-10 16:15:14.778155', '2025-06-10 16:15:14.778155', 4),
(8, 4901234567897, '日清 カップヌードル', 8, 200, 180, 220, '2025-06-10 16:15:14.778155', '2025-06-10 16:15:14.778155', 5),
(9, 4901234567898, 'ブレンディ スティック カフェオレ', 9, 120, 500, 500, '2025-06-10 16:15:14.778155', '2025-06-10 16:15:14.778155', 2),
(10, 4901234567899, '伊右衛門 緑茶 ペットボトル 500ml', 10, 130, 110, 140, '2025-06-10 16:15:14.778155', '2025-06-10 16:15:14.778155', 3),
(11, 4901234567900, 'インスタント味噌汁', 1, 119, 1425, 1367, '2025-06-10 16:15:14.778155', '2025-06-10 16:15:14.778155', 10),
(12, 4901234567901, 'ピーナッツバター', 2, 117, 1328, 1379, '2025-06-10 16:15:14.778155', '2025-06-10 16:15:14.778155', 10),
(13, 4901234567902, '黒豆甘納豆', 3, 67, 475, 850, '2025-06-10 16:15:14.778155', '2025-06-10 16:15:14.778155', 5),
(14, 4901234567903, 'ビーフジャーキー', 4, 148, 1125, 1243, '2025-06-10 16:15:14.778155', '2025-06-10 16:15:14.778155', 2),
(15, 4901234567904, 'ウーロン茶ペット', 5, 175, 1096, 878, '2025-06-10 16:15:14.778155', '2025-06-10 16:15:14.778155', 10),
(16, 4901234567905, 'あられミックス', 6, 72, 524, 1194, '2025-06-10 16:15:14.778155', '2025-06-10 16:15:14.778155', 10),
(17, 4901234567906, '缶コーヒー 微糖', 7, 130, 909, 933, '2025-06-10 16:15:14.778155', '2025-06-10 16:15:14.778155', 10),
(18, 4901234567907, 'グラノーラ 500g', 8, 171, 1451, 1174, '2025-06-10 16:15:14.778155', '2025-06-10 16:15:14.778155', 9),
(19, 4901234567908, 'レトルトカレー 激辛', 9, 177, 832, 887, '2025-06-10 16:15:14.778155', '2025-06-10 16:15:14.778155', 5),
(20, 4901234567909, 'フルーツ缶（みかん）', 10, 186, 1213, 1386, '2025-06-10 16:15:14.778155', '2025-06-10 16:15:14.778155', 10),
(21, 4901234567910, 'スナックミックス', 1, 74, 1174, 1293, '2025-06-10 16:15:14.778155', '2025-06-10 16:15:14.778155', 9),
(22, 4901234567911, 'ごまドレッシング', 2, 61, 1156, 737, '2025-06-10 16:15:14.778155', '2025-06-10 16:15:14.778155', 5),
(23, 4901234567912, '乾燥わかめ', 3, 177, 1060, 1467, '2025-06-10 16:15:14.778155', '2025-06-10 16:15:14.778155', 2),
(24, 4901234567913, '梅干し', 4, 63, 687, 1399, '2025-06-10 16:15:14.778155', '2025-06-10 16:15:14.778155', 1),
(25, 4901234567914, '野菜ジュース', 5, 112, 814, 1174, '2025-06-10 16:15:14.778155', '2025-06-10 16:15:14.778155', 5),
(26, 4901234567915, '玄米ごはんパック', 6, 193, 999, 1163, '2025-06-10 16:15:14.778155', '2025-06-10 16:15:14.778155', 10),
(27, 4901234567916, '冷凍枝豆', 7, 100, 974, 845, '2025-06-10 16:15:14.778155', '2025-06-10 16:15:14.778155', 8),
(28, 4901234567917, 'カップ焼きそば', 8, 92, 1092, 1177, '2025-06-10 16:15:14.778155', '2025-06-10 16:15:14.778155', 4),
(29, 4901234567918, '缶ビール 500ml', 9, 83, 1331, 1466, '2025-06-10 16:15:14.778155', '2025-06-10 16:15:14.778155', 7),
(30, 4901234567919, '煎茶ティーバッグ', 10, 177, 1265, 1294, '2025-06-10 16:15:14.778155', '2025-06-10 16:15:14.778155', 7),
(31, 4901234567920, 'ミネラルウォーター 2L', 1, 120, 536, 912, '2025-06-10 16:15:14.778155', '2025-06-10 16:15:14.778155', 7),
(32, 4901234567921, 'レトルトシチュー', 2, 60, 988, 1177, '2025-06-10 16:15:14.778155', '2025-06-10 16:15:14.778155', 4),
(33, 4901234567922, 'ソーセージパック', 3, 127, 1380, 1134, '2025-06-10 16:15:14.778155', '2025-06-10 16:15:14.778155', 5),
(34, 4901234567923, 'ジャム（いちご）', 4, 64, 1435, 1370, '2025-06-10 16:15:14.778155', '2025-06-10 16:15:14.778155', 7),
(35, 4901234567924, 'コーンスープ', 5, 108, 1167, 1372, '2025-06-10 16:15:14.778155', '2025-06-10 16:15:14.778155', 8),
(36, 4901234567925, '麦茶パック', 6, 106, 1374, 1242, '2025-06-10 16:15:14.778155', '2025-06-10 16:15:14.778155', 9),
(37, 4901234567926, 'コーンフレーク', 7, 70, 1114, 1300, '2025-06-10 16:15:14.778155', '2025-06-10 16:15:14.778155', 10),
(38, 4901234567927, '乾燥パスタ 1kg', 8, 147, 1007, 1002, '2025-06-10 16:15:14.778155', '2025-06-10 16:15:14.778155', 8),
(39, 4901234567928, 'インスタントラーメン 5食', 9, 79, 871, 1169, '2025-06-10 16:15:14.778155', '2025-06-10 16:15:14.778155', 4),
(40, 4901234567929, '焼き海苔 全形', 10, 167, 1180, 1433, '2025-06-10 16:15:14.778155', '2025-06-10 16:15:14.778155', 9),
(41, 4901234567930, 'ツナ缶詰 4個セット', 1, 197, 1326, 1466, '2025-06-10 16:15:14.778155', '2025-06-10 16:15:14.778155', 3),
(42, 4901234567931, 'ホットケーキミックス', 2, 178, 1094, 1033, '2025-06-10 16:15:14.778155', '2025-06-10 16:15:14.778155', 3),
(43, 4901234567932, '蜂蜜ボトル', 3, 188, 1188, 1053, '2025-06-10 16:15:14.778155', '2025-06-10 16:15:14.778155', 1),
(44, 4901234567933, '板チョコ', 4, 150, 1237, 949, '2025-06-10 16:15:14.778155', '2025-06-10 16:15:14.778155', 2),
(45, 4901234567934, 'バター風味クッキー', 5, 114, 950, 992, '2025-06-10 16:15:14.778155', '2025-06-10 16:15:14.778155', 5),
(46, 4901234567935, '冷凍チャーハン', 6, 157, 1275, 1109, '2025-06-10 16:15:14.778155', '2025-06-10 16:15:14.778155', 4),
(47, 4901234567936, 'トマトジュース', 7, 179, 1403, 1084, '2025-06-10 16:15:14.778155', '2025-06-10 16:15:14.778155', 6),
(48, 4901234567937, '野菜ミックス缶', 8, 135, 1106, 1353, '2025-06-10 16:15:14.778155', '2025-06-10 16:15:14.778155', 7),
(49, 4901234567938, 'マカロニサラダ', 9, 76, 1255, 1224, '2025-06-10 16:15:14.778155', '2025-06-10 16:15:14.778155', 4),
(50, 4901234567939, 'ソフトパン（6枚入り）', 10, 115, 1082, 1180, '2025-06-10 16:15:14.778155', '2025-06-10 16:15:14.778155', 1),
(51, 4901234567940, '即席スープ春雨', 1, 108, 1061, 1417, '2025-06-10 16:15:14.778155', '2025-06-10 16:15:14.778155', 4),
(52, 4901234567941, 'チーズスナック', 2, 194, 946, 1264, '2025-06-10 16:15:14.778155', '2025-06-10 16:15:14.778155', 3),
(53, 4901234567942, '冷凍たこ焼き', 3, 155, 1147, 1171, '2025-06-10 16:15:14.778155', '2025-06-10 16:15:14.778155', 4),
(54, 4901234567943, '干ししいたけ', 4, 152, 1393, 1303, '2025-06-10 16:15:14.778155', '2025-06-10 16:15:14.778155', 8),
(55, 4901234567944, '冷凍うどん', 5, 165, 903, 1045, '2025-06-10 16:15:14.778155', '2025-06-10 16:15:14.778155', 6),
(56, 4901234567945, 'プロテインバー', 6, 60, 1431, 1236, '2025-06-10 16:15:14.778155', '2025-06-10 16:15:14.778155', 9),
(57, 4901234567946, 'ドライマンゴー', 7, 114, 865, 1412, '2025-06-10 16:15:14.778155', '2025-06-10 16:15:14.778155', 7),
(58, 4901234567947, '焼き菓子詰め合わせ', 8, 84, 764, 1117, '2025-06-10 16:15:14.778155', '2025-06-10 16:15:14.778155', 5),
(59, 4901234567948, 'ピーナッツバター', 9, 198, 1081, 1112, '2025-06-10 16:15:14.778155', '2025-06-10 16:15:14.778155', 2),
(60, 4901234567949, '黒豆甘納豆', 10, 113, 720, 1251, '2025-06-10 16:15:14.778155', '2025-06-10 16:15:14.778155', 8);

INSERT INTO inventories (item_id, store_id, quantity, updated_at) VALUES
(1, 1, 55, CURRENT_TIMESTAMP),
(2, 1, 95, CURRENT_TIMESTAMP),
(3, 1, 85, CURRENT_TIMESTAMP),
(4, 1, 145, CURRENT_TIMESTAMP),
(5, 1, 58, CURRENT_TIMESTAMP),
(6, 1, 75, CURRENT_TIMESTAMP),
(7, 1, 95, CURRENT_TIMESTAMP),
(8, 1, 190, CURRENT_TIMESTAMP),
(9, 1, 125, CURRENT_TIMESTAMP),
(10, 1, 135, CURRENT_TIMESTAMP),
(11, 1, 115, CURRENT_TIMESTAMP),
(12, 1, 110, CURRENT_TIMESTAMP),
(13, 1, 65, CURRENT_TIMESTAMP),
(14, 1, 140, CURRENT_TIMESTAMP),
(15, 1, 170, CURRENT_TIMESTAMP),
(16, 1, 68, CURRENT_TIMESTAMP),
(17, 1, 135, CURRENT_TIMESTAMP),
(18, 1, 165, CURRENT_TIMESTAMP),
(19, 1, 170, CURRENT_TIMESTAMP),
(20, 1, 180, CURRENT_TIMESTAMP);

INSERT INTO transactions (type, item_id, store_id, quantity, expiration_date, user_id, created_at, checked) VALUES
('IN', 1, 1, 55, '2025-07-15', 1, CURRENT_TIMESTAMP, false),
('IN', 2, 1, 95, '2025-08-10', 1, CURRENT_TIMESTAMP, false),
('IN', 3, 1, 85, '2025-07-01', 1, CURRENT_TIMESTAMP, false),
('IN', 4, 1, 145, '2025-09-05', 1, CURRENT_TIMESTAMP, false),
('IN', 5, 1, 58, '2025-06-30', 1, CURRENT_TIMESTAMP, false),
('IN', 6, 1, 75, '2025-08-20', 1, CURRENT_TIMESTAMP, false),
('IN', 7, 1, 95, '2025-07-25', 1, CURRENT_TIMESTAMP, false),
('IN', 8, 1, 190, '2025-09-10', 1, CURRENT_TIMESTAMP, false),
('IN', 9, 1, 125, '2025-07-05', 1, CURRENT_TIMESTAMP, false),
('IN', 10, 1, 135, '2025-09-01', 1, CURRENT_TIMESTAMP, false),
('IN', 11, 1, 115, '2025-08-25', 1, CURRENT_TIMESTAMP, false),
('IN', 12, 1, 110, '2025-07-12', 1, CURRENT_TIMESTAMP, false),
('IN', 13, 1, 65, '2025-08-15', 1, CURRENT_TIMESTAMP, false),
('IN', 14, 1, 140, '2025-09-30', 1, CURRENT_TIMESTAMP, false),
('IN', 15, 1, 170, '2025-06-30', 1, CURRENT_TIMESTAMP, false),
('IN', 16, 1, 68, '2025-07-30', 1, CURRENT_TIMESTAMP, false),
('IN', 17, 1, 135, '2025-08-08', 1, CURRENT_TIMESTAMP, false),
('IN', 18, 1, 165, '2025-09-12', 1, CURRENT_TIMESTAMP, false),
('IN', 19, 1, 170, '2025-07-03', 1, CURRENT_TIMESTAMP, false),
('IN', 20, 1, 180, '2025-08-05', 1, CURRENT_TIMESTAMP, false);