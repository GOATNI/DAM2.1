INSERT INTO company (id, name, description) VALUES (1, 'Tech Corp', 'Technology Company');
INSERT INTO company (id, name, description) VALUES (2, 'Finance Inc', 'Financial Services');
INSERT INTO company (id, name, description) VALUES (3, 'Health Plus', 'Healthcare Provider');

INSERT INTO job (id, title, description, min_salary, max_salary, location, company_id) VALUES (1, 'Software Engineer', 'Java Developer', '50000', '80000', 'Remote', 1);
INSERT INTO job (id, title, description, min_salary, max_salary, location, company_id) VALUES (2, 'Data Analyst', 'SQL Expert', '45000', '70000', 'New York', 2);
INSERT INTO job (id, title, description, min_salary, max_salary, location, company_id) VALUES (3, 'DevOps Engineer', 'Cloud Infrastructure', '60000', '90000', 'Remote', 1);


INSERT INTO reviws (id, content, company_id)
VALUES (1, 'Great work environment and supportive team!', 1);

INSERT INTO reviws (id, content, company_id)
VALUES (2, 'Decent benefits but growth opportunities are limited.', 1);

INSERT INTO reviws (id, content, company_id)
VALUES (3, 'Professional culture and excellent financial training programs.', 2);

INSERT INTO reviws (id, content, company_id)
VALUES (4, 'Fast-paced environment, perfect for career growth.', 2);

INSERT INTO reviws (id, content, company_id)
VALUES (5, 'Caring staff and very patient-centered services.', 3);

INSERT INTO reviws (id, content, company_id)
VALUES (6, 'Workload can be high but management is understanding.', 3);
