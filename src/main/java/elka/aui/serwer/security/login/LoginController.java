package elka.aui.serwer.security.login;

import elka.aui.serwer.database.entities.Users;
import elka.aui.serwer.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(value={"/","/login"}, method = RequestMethod.GET)
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping(value="/registration", method = RequestMethod.GET)
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        Users users = new Users();
        modelAndView.addObject("users", users);
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid Users users, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        Users usersExists = userService.findUserByEmail(users.getEmail());
        if (usersExists != null) {
            bindingResult
                    .rejectValue("email", "error.users",
                            "There is already a users registered with the email provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registration");

        } else {
            userService.saveUser(users);
            modelAndView.addObject("successMessage", "Users has been registered successfully");
            modelAndView.addObject("user", new Users());
            modelAndView.setViewName("login");
        }
        return modelAndView;
    }

    @RequestMapping(value="/admin/home", method = RequestMethod.GET)
    public ModelAndView homeAdmin(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Users users = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("userName", "Welcome " + users.getName() + " " + users.getLastName() + " (" + users.getEmail() + ")");
        modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
        modelAndView.setViewName("admin/home");
        return modelAndView;
    }

    @RequestMapping(value={"/error", "/index"}, method = RequestMethod.GET)
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Users users = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("userName", "Welcome " + users.getName() + " " + users.getLastName() + " (" + users.getEmail() + ")");
        modelAndView.setViewName("index");
        return modelAndView;
    }
}
