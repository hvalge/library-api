INSERT INTO books (title, author, copies_available, age_since_publication)
    VALUES ('The Hobbit', 'J.R.R. Tolkien', 5, '2022-01-01');
INSERT INTO books (title, author, copies_available, age_since_publication)
    VALUES ('The Lord of the Rings', 'J.R.R. Tolkien', 4, '2022-12-15');
INSERT INTO books (title, author, copies_available, age_since_publication)
    VALUES ('The Silmarillion', 'J.R.R. Tolkien', 5, '2022-09-01');
INSERT INTO books (title, author, copies_available, age_since_publication)
    VALUES ('A Game of Thrones', 'George R.R. Martin', 10, '2022-01-01');
INSERT INTO books (title, author, copies_available, age_since_publication)
    VALUES ('Dune', 'Frank Herbert', 4, '2022-01-01');

INSERT INTO loaners (first_name, last_name) VALUES ('John', 'Doe');
INSERT INTO loaners (first_name, last_name) VALUES ('Jane', 'Doe');
INSERT INTO loaners (first_name, last_name) VALUES ('John', 'Smith');
INSERT INTO loaners (first_name, last_name) VALUES ('Jane', 'Smith');

INSERT INTO loans (book_id, loaner_id, loaned_at, due_date, is_returned, returned_at)
    VALUES (1, 1, '2022-02-01', '2022-02-09', true, '2022-02-08');
INSERT INTO loans (book_id, loaner_id, loaned_at, due_date, is_returned, returned_at)
    VALUES (2, 1, '2022-12-17', '2022-12-24', false, null);
INSERT INTO loans (book_id, loaner_id, loaned_at, due_date, is_returned, returned_at)
    VALUES (3, 1, '2022-11-01', '2022-11-08', false, null);
INSERT INTO loans (book_id, loaner_id, loaned_at, due_date, is_returned, returned_at)
    VALUES (4, 1, '2021-06-01', '2022-06-29', true, '2022-07-01');
