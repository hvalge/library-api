DROP SCHEMA public CASCADE;
CREATE SCHEMA public;

CREATE TABLE book (
    id INT NOT NULL,
    title VARCHAR(255),
    author VARCHAR(255),
    PRIMARY KEY (id));

CREATE TABLE loaner (
    id INT NOT NULL,
    firstName VARCHAR(255) NOT NULL,
    lastName VARCHAR(255) NOT NULL,
    PRIMARY KEY (id));

CREATE TABLE loanerbook (
    id INT NOT NULL,
    loanerId INT NOT NULL,
    bookId INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (loanerId) REFERENCES loaner(id),
    FOREIGN KEY (bookId) REFERENCES book(id));