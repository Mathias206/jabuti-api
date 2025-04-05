ALTER TABLE goals
  ADD COLUMN parent_goal_id BIGINT NULL,
  ADD CONSTRAINT fk_parent_goal FOREIGN KEY(parent_goal_id) REFERENCES goals(id)
  ON DELETE SET NULL; 