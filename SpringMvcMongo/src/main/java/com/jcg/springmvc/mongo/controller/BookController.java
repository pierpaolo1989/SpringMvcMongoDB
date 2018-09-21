package com.jcg.springmvc.mongo.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jcg.springmvc.mongo.Book;
import com.jcg.springmvc.mongo.BookService;

@Controller
@RequestMapping("/book")
public class BookController {

	private static Logger log = Logger.getLogger(BookController.class);

	@Resource(name="bookService")
	private BookService bookService;

	// Displaying the initial books list.
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getPersons(Model model) {
		log.debug("Request to fetch all books from the mongo database");
		List<Book> book_list = bookService.getAll();		
		model.addAttribute("books", book_list);		
		return "welcome";
	}

	// Opening the add new book form page.
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addBook(Model model) {
		log.debug("Request to open the new book form page");
		model.addAttribute("userAttr", new Book());
		return "form";
	}

	// Opening the edit book form page.
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String editBook(@RequestParam(value="id", required=true) String id, Model model) {
		log.debug("Request to open the edit book form page");	
		model.addAttribute("userAttr", bookService.findBookById(id));		
		return "form";
	}

	// Deleting the specified book.
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(@RequestParam(value="id", required=true) String id, Model model) {
		bookService.delete(id);
		return "redirect:list";
	}

	// Adding a new book or updating an existing book.
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("userAttr") Book book) {
		if(book.getId() != null && !book.getId().trim().equals("")) {
			bookService.edit(book);
		} else {
			bookService.add(book);
		}
		return "redirect:list";
	}
}