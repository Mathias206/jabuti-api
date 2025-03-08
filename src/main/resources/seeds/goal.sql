INSERT INTO
  goals (
    id,
    user_id,
    title,
    goal_description,
    goal_type,
    goal_status,
    goal_priority,
    created_at,
    updated_at
  )
VALUES
  (
    1,
    1,
    'Corrida Matinal',
    'Correr por 30 minutos',
    'DAILY',
    'PENDING',
    'HIGH',
    UTC_TIMESTAMP,
    UTC_TIMESTAMP
  ),
  (
    2,
    1,
    'Ler um Livro',
    'Ler 50 páginas',
    'WEEKLY',
    'IN_PROGRESS',
    'MEDIUM',
    UTC_TIMESTAMP,
    UTC_TIMESTAMP
  ),
  (
    3,
    2,
    'Finalizar Projeto',
    'Completar o módulo final',
    'MONTHLY',
    'PENDING',
    'HIGH',
    UTC_TIMESTAMP,
    UTC_TIMESTAMP
  ),
  (
    4,
    2,
    'Planejar Viagem',
    'Decidir um destino',
    'ANNUAL',
    'COMPLETED',
    'LOW',
    UTC_TIMESTAMP,
    UTC_TIMESTAMP
  ),
  (
    5,
    3,
    'Rotina de Exercícios',
    'Treinar 5 vezes por semana',
    'WEEKLY',
    'PENDING',
    'HIGH',
    UTC_TIMESTAMP,
    UTC_TIMESTAMP
  ),
  (
    6,
    4,
    'Aprender Espanhol',
    'Praticar espanhol diariamente',
    'DAILY',
    'IN_PROGRESS',
    'MEDIUM',
    UTC_TIMESTAMP,
    UTC_TIMESTAMP
  ),
  (
    7,
    5,
    'Economizar Dinheiro',
    'Guardar $500 este mês',
    'MONTHLY',
    'PENDING',
    'HIGH',
    UTC_TIMESTAMP,
    UTC_TIMESTAMP
  ),
  (
    8,
    6,
    'Cozinha Saudável',
    'Preparar 3 refeições saudáveis por semana',
    'WEEKLY',
    'IN_PROGRESS',
    'MEDIUM',
    UTC_TIMESTAMP,
    UTC_TIMESTAMP
  ),
  (
    9,
    7,
    'Meditar',
    'Meditar por 10 minutos diariamente',
    'DAILY',
    'PENDING',
    'HIGH',
    UTC_TIMESTAMP,
    UTC_TIMESTAMP
  ),
  (
    10,
    8,
    'Trabalho Voluntário',
    'Ajudar em um abrigo local',
    'MONTHLY',
    'COMPLETED',
    'LOW',
    UTC_TIMESTAMP,
    UTC_TIMESTAMP
  ),
  (
    11,
    9,
    'Escrever Diário',
    'Registrar pensamentos e reflexões diariamente',
    'DAILY',
    'IN_PROGRESS',
    'MEDIUM',
    UTC_TIMESTAMP,
    UTC_TIMESTAMP
  ),
  (
    12,
    10,
    'Aprender Violão',
    'Praticar violão por uma hora toda semana',
    'WEEKLY',
    'PENDING',
    'MEDIUM',
    UTC_TIMESTAMP,
    UTC_TIMESTAMP
  ),
  (
    13,
    3,
    'Melhorar a Dieta',
    'Comer mais vegetais e reduzir açúcar',
    'DAILY',
    'IN_PROGRESS',
    'HIGH',
    UTC_TIMESTAMP,
    UTC_TIMESTAMP
  ),
  (
    14,
    5,
    'Crescimento Profissional',
    'Concluir um curso online',
    'MONTHLY',
    'PENDING',
    'HIGH',
    UTC_TIMESTAMP,
    UTC_TIMESTAMP
  ),
  (
    15,
    7,
    'Networking',
    'Participar de pelo menos 2 eventos este mês',
    'MONTHLY',
    'PENDING',
    'MEDIUM',
    UTC_TIMESTAMP,
    UTC_TIMESTAMP
  ),
  (
    16,
    9,
    'Caminhada',
    'Fazer trilhas todo fim de semana',
    'WEEKLY',
    'IN_PROGRESS',
    'HIGH',
    UTC_TIMESTAMP,
    UTC_TIMESTAMP
  ),
  (
    17,
    10,
    'Organizar Casa',
    'Doar itens não utilizados',
    'ANNUAL',
    'COMPLETED',
    'LOW',
    UTC_TIMESTAMP,
    UTC_TIMESTAMP
  ),
  (
    18,
    1,
    'Praticar Programação',
    'Resolver 5 problemas de código diariamente',
    'DAILY',
    'PENDING',
    'HIGH',
    UTC_TIMESTAMP,
    UTC_TIMESTAMP
  ),
  (
    19,
    2,
    'Ouvir Podcasts',
    'Ouvir um podcast educativo toda semana',
    'WEEKLY',
    'IN_PROGRESS',
    'MEDIUM',
    UTC_TIMESTAMP,
    UTC_TIMESTAMP
  ),
  (
    20,
    6,
    'Desintoxicação Digital',
    'Reduzir o tempo de tela em 2 horas por dia',
    'DAILY',
    'PENDING',
    'HIGH',
    UTC_TIMESTAMP,
    UTC_TIMESTAMP
  )
ON DUPLICATE KEY UPDATE
  user_id = VALUES(user_id),
  title = VALUES(title),
  goal_description = VALUES(goal_description),
  goal_type = VALUES(goal_type),
  goal_status = VALUES(goal_status),
  goal_priority = VALUES(goal_priority),
  created_at = VALUES(created_at),
  updated_at = VALUES(updated_at);

