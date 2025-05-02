-- ADMIN tem todas as permissões
INSERT INTO group_permission (group_id, permission_id) VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 4)
ON DUPLICATE KEY UPDATE
group_id = values(group_id),
permission_id = values(permission_id);

-- USER tem apenas LISTAR e CRIAR
INSERT INTO group_permission (group_id, permission_id) VALUES
(2, 1),
(2, 2)
ON DUPLICATE KEY UPDATE
group_id = values(group_id),
permission_id = values(permission_id);