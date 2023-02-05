package k23.bookstore.Bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
}
