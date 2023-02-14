package k23.bookstore.Bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import k23.bookstore.Bookstore.domain.Book;
import k23.bookstore.Bookstore.domain.BookRepository;
import k23.bookstore.Bookstore.domain.Category;
import k23.bookstore.Bookstore.domain.CategoryRepository;

@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(BookRepository repository, CategoryRepository catrepository) {
	return (args) -> {
		catrepository.save(new Category("Drama"));
		catrepository.save(new Category("Non-fiction"));
		catrepository.save(new Category("Scifi"));
		
		Book demo1 = new Book("Hello world!", "Uknown programmer", "2023", "978-951-12345-1-2", "9,99", catrepository.findByName("Scifi").get(0));
		Book demo2 = new Book("Programming for dummies", "Uknown author", "1999", "978-951-56789-1-3", "21,49", catrepository.findByName("Non-fiction").get(0));
		
		repository.save(demo1);
		repository.save(demo2);
	};
	}

}
