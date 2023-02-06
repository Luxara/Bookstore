package k23.bookstore.Bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import k23.bookstore.Bookstore.domain.Book;
import k23.bookstore.Bookstore.domain.BookRepository;

@Controller
public class BookController {
	@RequestMapping(value="/index", method=RequestMethod.GET)
		public String mainPage(Model model){
		return "index";
	}
	
	@Autowired
	private BookRepository repository;

	@RequestMapping(value="/booklist", method=RequestMethod.GET)
		public String showBooks(Model model){
			model.addAttribute("books", repository.findAll());
		
			return "booklist";
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
		public String deleteBook(@PathVariable("id") Long bookId, Model model){
			repository.deleteById(bookId);
			return "redirect:../booklist";
	}
	
	@RequestMapping(value="/add")
		public String addBook(Model model){
			model.addAttribute("book", new Book());
			return "addBook";
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
		public String editBook(@PathVariable("id") Long id, Model model){
		model.addAttribute("book", repository.findById(id));
		return "editBook";
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
		public String saveBook(Book book){
			repository.save(book);
			return "redirect:booklist";
	}
}
