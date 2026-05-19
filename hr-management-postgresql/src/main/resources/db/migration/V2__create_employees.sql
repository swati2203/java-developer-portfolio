CREATE TABLE employees (
    id            BIGSERIAL PRIMARY KEY,
    first_name    VARCHAR(50) NOT NULL,
    last_name     VARCHAR(50) NOT NULL,
    email         VARCHAR(150) UNIQUE NOT NULL,
    phone         VARCHAR(20),
    job_title     VARCHAR(100),
    salary        NUMERIC(12,2),
    hire_date     DATE,
    status        VARCHAR(20) DEFAULT 'ACTIVE',
    department_id BIGINT REFERENCES departments(id),
    manager_id    BIGINT REFERENCES employees(id),
    created_at    TIMESTAMP DEFAULT NOW(),
    updated_at    TIMESTAMP DEFAULT NOW()
);

CREATE INDEX idx_employees_dept ON employees(department_id);
CREATE INDEX idx_employees_status ON employees(status);
