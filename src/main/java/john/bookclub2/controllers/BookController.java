package john.bookclub2.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import john.bookclub2.models.Book;
import john.bookclub2.models.User;
import john.bookclub2.services.BookService;
import john.bookclub2.services.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
public class BookController {

    private final BookService bookService;
    private final UserService userService;

    public BookController(BookService bookService, UserService userService) {
        this.bookService = bookService;
        this.userService = userService;
    }

    // create
    @GetMapping("/books/new")
    public String newBook(@ModelAttribute("book")Book book, Model model, HttpSession session){
        Long id = (Long) session.getAttribute("userId");
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "books/new.jsp";
    }

    @PostMapping("/books")
    public String createBook(@Valid @ModelAttribute("book")Book book, BindingResult result){
        if(result.hasErrors()){
            return "books/new.jsp";
        } else {
            bookService.create(book);
            return "redirect:/books";
        }
    }

    // read all
    @GetMapping("/books")
    public String allBooks(Model model){
        List<Book> books = bookService.getAll();
        model.addAttribute("books", books);
        return "books/index.jsp";

    }

    // read one

    @GetMapping("/books/{id}")
    public String show(HttpSession session, Model model, @PathVariable("id")Long id){
        Book book = bookService.getOne(id);
        model.addAttribute("book", book);

        Long userId = (Long) session.getAttribute("userId");
        User user = userService.findById(userId);
        model.addAttribute("user", user);
        return "books/show.jsp";
    }

    // edit

    @GetMapping("/books/edit/{id}")
    public String editBook(@PathVariable("id")Long id, Model model){
        Book book = bookService.getOne(id);
        model.addAttribute("book", book);
        return "books/edit.jsp";
    }

    @PutMapping("/books/{id}")
    public String updateBook(@Valid @ModelAttribute("book")Book book, BindingResult result){
        if(result.hasErrors()){
            return "books/edit.jsp";
        } else {
            bookService.update(book);
            return "redirect:/books";
        }
    }
    @DeleteMapping("/books/destroy/{id}")
    public String destroy(@PathVariable("id")Long id){
        bookService.destroy(bookService.getOne(id));
        return "redirect:/books";
    }
}