CREATE TABLE departments (
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR(100) UNIQUE NOT NULL,
    description TEXT,
    location    VARCHAR(100)
);

INSERT INTO departments (name, description, location) VALUES
  ('Engineering', 'Software development team', 'Bengaluru'),
  ('Human Resources', 'HR and recruitment', 'Mumbai'),
  ('Finance', 'Finance and accounting', 'Delhi');
