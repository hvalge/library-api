DROP SCHEMA public CASCADE;
CREATE SCHEMA public;



CREATE TABLE books (
    id INT NOT NULL,
    title VARCHAR(255),
    author VARCHAR(255),
    copies_available INT,
    age_since_publication TIMESTAMP,
    PRIMARY KEY (id)
);

CREATE TABLE loaners (
    id INT NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    PRIMARY KEY (id));

CREATE TABLE loans (
    id INT NOT NULL,
    loaner_id INT NOT NULL,
    book_id INT NOT NULL,
    loaned_at TIMESTAMP NOT NULL,
    due_date TIMESTAMP NOT NULL,
    is_returned BOOLEAN NOT NULL,
    returned_at TIMESTAMP NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (loaner_id) REFERENCES loaners(id),
    FOREIGN KEY (book_id) REFERENCES books(id)
);


CREATE TABLE users (
    username VARCHAR(50) NOT NULL PRIMARY KEY,
    password VARCHAR(255) NOT NULL,
    enabled BOOLEAN NOT NULL
);

CREATE TABLE authorities (
    username VARCHAR(50) NOT NULL,
    authority VARCHAR(50) NOT NULL,
    FOREIGN KEY (username) REFERENCES users
        ON DELETE CASCADE
);