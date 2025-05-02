INSERT INTO `groups` (id, group_name) VALUES
(1, 'ADMIN'),
(2, 'USER')
ON DUPLICATE KEY UPDATE
id = values(id),
group_name = values(group_name);