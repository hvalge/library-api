package com.library.seed;

import com.library.db.entity.Book;
import com.library.db.entity.Loan;
import com.library.db.entity.Loaner;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class LibraryTestDataSeeder {

    private final TestEntityManager entityManager;

    public LibraryTestDataSeeder(TestEntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void seedLibraryData() {
        Book book1 = new Book();
        book1.setTitle("The Lord of the Rings");
        book1.setAuthor("J.R.R. Tolkien");
        book1.setAgeSincePublication(LocalDate.parse("2022-01-01"));
        book1.setCopiesAvailable(5);
        entityManager.persist(book1);

        Book book2 = new Book();
        book2.setTitle("The Hobbit");
        book2.setAuthor("J.R.R. Tolkien");
        book2.setAgeSincePublication(LocalDate.parse("2022-12-15"));
        book2.setCopiesAvailable(4);
        entityManager.persist(book2);

        Book book3 = new Book();
        book3.setTitle("The Silmarillion");
        book3.setAuthor("J.R.R. Tolkien");
        book3.setAgeSincePublication(LocalDate.parse("2022-09-01"));
        book3.setCopiesAvailable(3);
        entityManager.persist(book3);

        Book book4 = new Book();
        book4.setTitle("A Game of Thrones");
        book4.setAuthor("George R.R. Martin");
        book4.setAgeSincePublication(LocalDate.parse("2022-01-01"));
        book4.setCopiesAvailable(10);
        entityManager.persist(book4);

        Loaner loaner1 = new Loaner();
        loaner1.setFirstName("John");
        loaner1.setLastName("Doe");
        entityManager.persist(loaner1);

        Loaner loaner2 = new Loaner();
        loaner2.setFirstName("Jane");
        loaner2.setLastName("Doe");
        entityManager.persist(loaner2);

        Loaner loaner3 = new Loaner();
        loaner3.setFirstName("John");
        loaner3.setLastName("Smith");
        entityManager.persist(loaner3);

        Loaner loaner4 = new Loaner();
        loaner4.setFirstName("Jane");
        loaner4.setLastName("Smith");
        entityManager.persist(loaner4);

        Loan loan1 = new Loan();
        loan1.setBook(book1);
        loan1.setLoaner(loaner1);
        loan1.setLoanedAt(LocalDate.parse("2022-02-01"));
        loan1.setDueDate(LocalDate.parse("2022-02-08"));
        loan1.setIsReturned(true);
        loan1.setReturnedAt(LocalDate.parse("2022-02-08"));
        entityManager.persist(loan1);

        Loan loan2 = new Loan();
        loan2.setBook(book2);
        loan2.setLoaner(loaner1);
        loan2.setLoanedAt(LocalDate.now().minusDays(4));
        loan2.setIsReturned(false);
        loan2.setDueDate(LocalDate.now().plusDays(3));
        loan2.setReturnedAt(null);
        entityManager.persist(loan2);

        Loan loan3 = new Loan();
        loan3.setBook(book3);
        loan3.setLoaner(loaner1);
        loan3.setLoanedAt(LocalDate.parse("2022-12-01"));
        loan3.setIsReturned(false);
        loan3.setDueDate(LocalDate.parse("2022-12-08"));
        loan3.setReturnedAt(null);
        entityManager.persist(loan3);

        Loan loan4 = new Loan();
        loan4.setBook(book4);
        loan4.setLoaner(loaner4);
        loan4.setLoanedAt(LocalDate.parse("2021-06-01"));
        loan4.setIsReturned(true);
        loan4.setDueDate(LocalDate.parse("2022-06-29"));
        loan4.setReturnedAt(LocalDate.parse("2022-07-01"));
        entityManager.persist(loan4);
    }
}
