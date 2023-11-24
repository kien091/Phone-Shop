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
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private PhoneService phoneService;

    @Autowired
    private PhoneInCartService phoneInCartService;

    private String view(Model model, HttpSession session,
                        List<Phone> phones){
        User user = (User) session.getAttribute("user");
        if(user == null)
            return "redirect:/";

        int numInCart = phoneInCartService.findAllByIdCart(user.getCart().getId()).size();

        model.addAttribute("phones", phones);
        model.addAttribute("numInCart", numInCart);//
        return "home";
    }
    @RequestMapping("")
    public String home(Model model, HttpSession session){
        return view(model, session, phoneService.findAll());
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String search(@RequestParam("search") String search,
                         Model model, HttpSession session){
        return view(model, session, phoneService.findAllBySearch(search));
    }

    @RequestMapping(value = "/logout")
    public String logout(Model model){
        return "redirect:/";
    }

    @RequestMapping(value = "/price")
    public String sortByPrice(Model model, HttpSession session){
        return view(model, session, phoneService.findAllByPriceDesc());
    }

    @RequestMapping(value = "/memory")
    public String sortByMemory(Model model, HttpSession session){
        return view(model, session, phoneService.findAllByMemoryDesc());
    }

    @RequestMapping(value = "/name")
    public String sortByName(Model model, HttpSession session){
        return view(model, session, phoneService.findAllByNameAsc());
    }

    @RequestMapping(value = "/color")
    public String sortByColor(Model model, HttpSession session){
        return view(model, session, phoneService.findAllByColorAsc());
    }
}
