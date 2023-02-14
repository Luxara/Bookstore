package k23.bookstore.Bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import k23.bookstore.Bookstore.domain.Book;
import k23.bookstore.Bookstore.domain.BookRepository;
import k23.bookstore.Bookstore.domain.CategoryRepository;

@Controller
public class BookController {
	@RequestMapping(value="/index", method=RequestMethod.GET)
		public String mainPage(Model model){
		return "index";
	}
	
	@Autowired
	private BookRepository repository;
	
	@Autowired
	private CategoryRepository catrepository;

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
			model.addAttribute("newbook", new Book());
			model.addAttribute("categories", catrepository.findAll());
			return "addBook";
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
		public String editBook(@PathVariable("id") Long id, Model model){
		model.addAttribute("editbook", repository.findById(id));
		model.addAttribute("categories", catrepository.findAll());
		return "editBook";
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
		public String saveBook(Book book){
			repository.save(book);
			return "redirect:booklist";
	}
}
