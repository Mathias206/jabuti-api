INSERT INTO permissions (id, permission_name, permission_description) VALUES
(1, 'LISTAR', 'pode listar'),
(2, 'CRIAR', 'pode criar'),
(3, 'ATUALIZAR', 'pode atualizar'),
(4, 'DELETAR', 'pode deletar')
ON DUPLICATE KEY UPDATE
id = values(id),
permission_name = values(permission_name);