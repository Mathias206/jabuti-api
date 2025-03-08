INSERT INTO
  users (id, user_name, email)
VALUES
  (1, 'Alice Johnson', 'alice@example.com'),
  (2, 'Bob Smith', 'bob@example.com'),
  (3, 'Charlie Brown', 'charlie@example.com'),
  (4, 'Diana Prince', 'diana@example.com'),
  (5, 'Ethan Hunt', 'ethan@example.com'),
  (6, 'Fiona Gallagher', 'fiona@example.com'),
  (7, 'George Clooney', 'george@example.com'),
  (8, 'Hannah Baker', 'hannah@example.com'),
  (9, 'Ian Somerhalder', 'ian@example.com'),
  (10, 'Jane Doe', 'jane@example.com')
ON DUPLICATE KEY UPDATE
  user_name = VALUES(user_name),
  email = VALUES(email);

