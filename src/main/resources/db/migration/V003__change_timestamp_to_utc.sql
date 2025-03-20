UPDATE users
SET created_at = UTC_TIMESTAMP(),
    updated_at = UTC_TIMESTAMP();