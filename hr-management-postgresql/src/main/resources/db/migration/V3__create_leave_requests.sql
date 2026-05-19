CREATE TABLE leave_requests (
    id          BIGSERIAL PRIMARY KEY,
    employee_id BIGINT NOT NULL REFERENCES employees(id),
    leave_type  VARCHAR(20) NOT NULL,
    start_date  DATE NOT NULL,
    end_date    DATE NOT NULL,
    status      VARCHAR(20) DEFAULT 'PENDING',
    reason      TEXT,
    applied_at  TIMESTAMP DEFAULT NOW()
);

CREATE INDEX idx_leave_employee ON leave_requests(employee_id);
CREATE INDEX idx_leave_status ON leave_requests(status);
