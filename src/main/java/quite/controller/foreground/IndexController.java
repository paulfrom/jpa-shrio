package quite.controller.foreground;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import quite.entity.Books;
import quite.dao.BookService;

import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 * User: pual
 * Date: 2015/11/17
 * Desc:
 */
@Controller
public class IndexController {

    @Autowired
    private BookService bookService;

    @RequestMapping("index")
    public String index(Model model){
        Optional<Books> books=bookService.findBookByName("yyy");
        model.addAttribute("book",books.isPresent()?books.get():"null");
        return "foreground/index";
    }
}
