INSERT INTO
  users (id, user_name, email, user_password, updated_at, created_at)
VALUES
  (1, 'Alice Johnson', 'alice@example.com', "$2a$10$rhGna/6YNlAID3nP6dYOVe2nUyVGBhs4JGy4VjXbYlumdFAhb2jti", UTC_TIMESTAMP, UTC_TIMESTAMP),
  (2, 'Bob Smith', 'bob@example.com', "$2a$10$rhGna/6YNlAID3nP6dYOVe2nUyVGBhs4JGy4VjXbYlumdFAhb2jti", UTC_TIMESTAMP, UTC_TIMESTAMP),
  (3, 'Charlie Brown', 'charlie@example.com', "$2a$10$rhGna/6YNlAID3nP6dYOVe2nUyVGBhs4JGy4VjXbYlumdFAhb2jti",UTC_TIMESTAMP, UTC_TIMESTAMP),
  (4, 'Diana Prince', 'diana@example.com', "$2a$10$rhGna/6YNlAID3nP6dYOVe2nUyVGBhs4JGy4VjXbYlumdFAhb2jti",UTC_TIMESTAMP, UTC_TIMESTAMP),
  (5, 'Ethan Hunt', 'ethan@example.com', "$2a$10$rhGna/6YNlAID3nP6dYOVe2nUyVGBhs4JGy4VjXbYlumdFAhb2jti",UTC_TIMESTAMP, UTC_TIMESTAMP),
  (6, 'Fiona Gallagher', 'fiona@example.com', "$2a$10$rhGna/6YNlAID3nP6dYOVe2nUyVGBhs4JGy4VjXbYlumdFAhb2jti", UTC_TIMESTAMP, UTC_TIMESTAMP),
  (7, 'George Clooney', 'george@example.com', "$2a$10$rhGna/6YNlAID3nP6dYOVe2nUyVGBhs4JGy4VjXbYlumdFAhb2jti", UTC_TIMESTAMP, UTC_TIMESTAMP),
  (8, 'Hannah Baker', 'hannah@example.com', "$2a$10$rhGna/6YNlAID3nP6dYOVe2nUyVGBhs4JGy4VjXbYlumdFAhb2jti", UTC_TIMESTAMP, UTC_TIMESTAMP),
  (9, 'Ian Somerhalder', 'ian@example.com', "$2a$10$rhGna/6YNlAID3nP6dYOVe2nUyVGBhs4JGy4VjXbYlumdFAhb2jti", UTC_TIMESTAMP, UTC_TIMESTAMP),
  (10, 'Jane Doe', 'jane@example.com', "$2a$10$rhGna/6YNlAID3nP6dYOVe2nUyVGBhs4JGy4VjXbYlumdFAhb2jti", UTC_TIMESTAMP, UTC_TIMESTAMP)
ON DUPLICATE KEY UPDATE
  user_name = VALUES(user_name),
  email = VALUES(email);
  user_password = VALUES(user_password)

