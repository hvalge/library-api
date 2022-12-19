INSERT INTO books (id, title, author, copies_available, age_since_publication)
    VALUES (1, 'The Hobbit', 'J.R.R. Tolkien', 5, '2022-01-01');
INSERT INTO books (id, title, author, copies_available, age_since_publication)
    VALUES (2, 'The Lord of the Rings', 'J.R.R. Tolkien', 4, '2022-12-15');
INSERT INTO books (id, title, author, copies_available, age_since_publication)
    VALUES (3, 'The Silmarillion', 'J.R.R. Tolkien', 5, '2022-09-01');
INSERT INTO books (id, title, author, copies_available, age_since_publication)
    VALUES (4, 'A Game of Thrones', 'George R.R. Martin', 10, '2022-01-01');
INSERT INTO books (id, title, author, copies_available, age_since_publication)
    VALUES (5, 'Dune', 'Frank Herbert', 4, '2022-01-01');

INSERT INTO loaners (id, first_name, last_name) VALUES (1, 'John', 'Doe');
INSERT INTO loaners (id, first_name, last_name) VALUES (2, 'Jane', 'Doe');
INSERT INTO loaners (id, first_name, last_name) VALUES (3, 'John', 'Smith');
INSERT INTO loaners (id, first_name, last_name) VALUES (4, 'Jane', 'Smith');

INSERT INTO loans (id, book_id, loaner_id, loaned_at, due_date, is_returned, returned_at)
    VALUES (1, 1, 1, '2022-02-01', '2022-02-09', true, '2022-02-08');
INSERT INTO loans (id, book_id, loaner_id, loaned_at, due_date, is_returned, returned_at)
    VALUES (2, 2, 1, '2022-12-17', '2022-12-24', false, null);
INSERT INTO loans (id, book_id, loaner_id, loaned_at, due_date, is_returned, returned_at)
    VALUES (3, 3, 1, '2022-11-01', '2022-11-08', false, null);
INSERT INTO loans (id, book_id, loaner_id, loaned_at, due_date, is_returned, returned_at)
    VALUES (4, 4, 1, '2021-06-01', '2022-06-29', true, '2022-07-01');


