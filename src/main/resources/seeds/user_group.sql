INSERT INTO user_group (user_id, group_id) VALUES
(1, 1),
(2, 2)
ON DUPLICATE KEY UPDATE
user_id = values(user_id),
group_id = values(group_id);


