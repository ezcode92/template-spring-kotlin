CREATE TABLE ping_message (
    id BIGSERIAL PRIMARY KEY,
    message VARCHAR(100) NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT now()
);

CREATE INDEX idx_ping_message_created_at ON ping_message (created_at);

