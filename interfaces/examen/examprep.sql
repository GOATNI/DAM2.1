-- =========================================
-- JAVA FX EXAM DATABASE (ONE EXECUTION)
-- =========================================

DROP DATABASE IF EXISTS javafx_exam;
CREATE DATABASE javafx_exam;
USE javafx_exam;

-- =========================================
-- COMPANY TABLE (COMBOBOX)
-- =========================================
CREATE TABLE company (
    company_id INT AUTO_INCREMENT PRIMARY KEY,
    company_name VARCHAR(100) NOT NULL
);

INSERT INTO company (company_name) VALUES
('Google'),
('Microsoft'),
('Amazon'),
('Apple');

-- =========================================
-- EMPLOYEE TABLE (ALL CONTROLS)
-- =========================================
CREATE TABLE employee (
    employee_id INT AUTO_INCREMENT PRIMARY KEY,

    -- TextFields
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    phone VARCHAR(20),

    -- DatePicker
    date_of_birth DATE NOT NULL,

    -- RadioButtons
    gender ENUM('Male', 'Female', 'Other') NOT NULL,

    -- CheckBoxes (skills / categories)
    knows_java BOOLEAN DEFAULT FALSE,
    knows_python BOOLEAN DEFAULT FALSE,
    knows_sql BOOLEAN DEFAULT FALSE,
    is_full_time BOOLEAN DEFAULT FALSE,

    -- ComboBox
    company_id INT,

    FOREIGN KEY (company_id) REFERENCES company(company_id)
);

-- =========================================
-- SAMPLE DATA
-- =========================================
INSERT INTO employee
(full_name, email, phone, date_of_birth, gender,
 knows_java, knows_python, knows_sql, is_full_time, company_id)
VALUES
('Alice Brown', 'alice@gmail.com', '123456789',
 '2000-05-10', 'Female',
 TRUE, FALSE, TRUE, TRUE, 1),

('John Smith', 'john@gmail.com', '987654321',
 '1998-02-20', 'Male',
 TRUE, TRUE, FALSE, FALSE, 2),

('Sara Lee', 'sara@gmail.com', '555666777',
 '2001-11-15', 'Other',
 FALSE, TRUE, TRUE, TRUE, 3);

-- =========================================
-- TEST QUERY (OPTIONAL)
-- =========================================
SELECT e.full_name, e.gender, c.company_name
FROM employee e
JOIN company c ON e.company_id = c.company_id;
