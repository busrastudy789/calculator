-- Calculator Application Database Setup Script
-- Run this in phpMyAdmin or MySQL CLI to create the database structure

-- Create database
CREATE DATABASE IF NOT EXISTS calculator_db;
USE calculator_db;

-- Create users table
CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Add some sample users (optional)
-- INSERT INTO users (username, password) VALUES ('demo', 'demo123');
-- INSERT INTO users (username, password) VALUES ('test', 'test123');

-- View all users
-- SELECT * FROM users;

-- Delete a user
-- DELETE FROM users WHERE username = 'demo';

-- Update a password
-- UPDATE users SET password = 'newpassword' WHERE username = 'demo';
