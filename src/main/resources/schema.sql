DROP SCHEMA public CASCADE;
CREATE SCHEMA public;



CREATE TABLE book (
    id INT NOT NULL,
    title VARCHAR(255),
    author VARCHAR(255),
    PRIMARY KEY (id));

CREATE TABLE loaner (
    id INT NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    PRIMARY KEY (id));

CREATE TABLE loaner_book (
    id INT NOT NULL,
    loaner_id INT NOT NULL,
    book_id INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (loaner_id) REFERENCES loaner(id),
    FOREIGN KEY (book_id) REFERENCES book(id));