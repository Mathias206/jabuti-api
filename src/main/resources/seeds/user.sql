INSERT INTO
  users (id, user_name, email, updated_at, created_at)
VALUES
  (1, 'Alice Johnson', 'alice@example.com', UTC_TIMESTAMP, UTC_TIMESTAMP),
  (2, 'Bob Smith', 'bob@example.com', UTC_TIMESTAMP, UTC_TIMESTAMP),
  (3, 'Charlie Brown', 'charlie@example.com', UTC_TIMESTAMP, UTC_TIMESTAMP),
  (4, 'Diana Prince', 'diana@example.com', UTC_TIMESTAMP, UTC_TIMESTAMP),
  (5, 'Ethan Hunt', 'ethan@example.com', UTC_TIMESTAMP, UTC_TIMESTAMP),
  (6, 'Fiona Gallagher', 'fiona@example.com', UTC_TIMESTAMP, UTC_TIMESTAMP),
  (7, 'George Clooney', 'george@example.com', UTC_TIMESTAMP, UTC_TIMESTAMP),
  (8, 'Hannah Baker', 'hannah@example.com', UTC_TIMESTAMP, UTC_TIMESTAMP),
  (9, 'Ian Somerhalder', 'ian@example.com', UTC_TIMESTAMP, UTC_TIMESTAMP),
  (10, 'Jane Doe', 'jane@example.com', UTC_TIMESTAMP, UTC_TIMESTAMP)
ON DUPLICATE KEY UPDATE
  user_name = VALUES(user_name),
  email = VALUES(email);

