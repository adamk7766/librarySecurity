package pl.gaamit.librarySecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.gaamit.librarySecurity.model.Book;
import pl.gaamit.librarySecurity.model.User;
import pl.gaamit.librarySecurity.model.UserService;
import pl.gaamit.librarySecurity.repositories.AuthorRepository;
import pl.gaamit.librarySecurity.repositories.BookRepository;

@Controller
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private UserService userService;


    @GetMapping("/books/add")
    public String add(ModelMap modelMap) {
        modelMap.addAttribute("book", new Book());
        modelMap.addAttribute("authors", authorRepository.findAll());
        return "books/add";
    }

    @PostMapping("/books")
    public String save(@ModelAttribute Book book) {
        bookRepository.save(book);
        return "redirect:/books";
    }

/*    @GetMapping("/books")
    public String index(ModelMap modelMap) {
        modelMap.addAttribute("books", bookRepository.findAll());
        return "books/bookslist";
    }*/

    @RequestMapping(value="/books", method = RequestMethod.GET)
    public ModelAndView booksList(ModelMap modelMap){
        modelMap.addAttribute("books", bookRepository.findAll());
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
        modelAndView.setViewName("books/bookslist");
        return modelAndView;
    }

    @GetMapping("books/{id}")
    public String show(@PathVariable Long id, ModelMap modelMap) {
        modelMap.put("book", bookRepository.findById(id).get());
        return "books/show";
    }

    @GetMapping("books/{id}/delete")
    public String delete(@PathVariable Long id, ModelMap modelMap) {
        bookRepository.deleteById(id);
        return "redirect:/books";
    }

    @GetMapping("books/{id}/edit")
    public String edit(@PathVariable Long id, ModelMap modelMap) {
        modelMap.put("book", bookRepository.findById(id).get());
        modelMap.addAttribute("authors", authorRepository.findAll());
        return "books/add";
    }


}
