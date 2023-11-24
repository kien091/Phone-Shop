package Source.Controllers;

import Source.Models.Phone;
import Source.Models.User;
import Source.Services.PhoneInCartService;
import Source.Services.PhoneService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    private PhoneService phoneService;

    @Autowired
    private PhoneInCartService phoneInCartService;

    @RequestMapping("home")
    public String home(Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null)
            return "redirect:/";

        List<Phone> phones = phoneService.findAll();
        int numInCart = phoneInCartService.findAllByIdCart(user.getCart().getId()).size();

        model.addAttribute("phones", phones);
        model.addAttribute("numInCart", numInCart);//
        return "home";
    }

    @RequestMapping(value = "home", method = RequestMethod.POST)
    public String search(@RequestParam("search") String search,
                         Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null)
            return "redirect:/";

        List<Phone> phones = phoneService.findAllBySearch(search);
        int numInCart = phoneInCartService.findAllByIdCart(user.getCart().getId()).size();

        model.addAttribute("phones", phones);
        model.addAttribute("numInCart", numInCart);//
        return "home";
    }

    @RequestMapping(value = "logout")
    public String logout(Model model){
        return "redirect:/";
    }
}
