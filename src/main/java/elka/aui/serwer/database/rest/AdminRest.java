package elka.aui.serwer.database.rest;


import elka.aui.serwer.database.entities.Note;
import elka.aui.serwer.database.entities.Role;
import elka.aui.serwer.database.entities.Users;
import elka.aui.serwer.database.exceptions.NoAccessException;
import elka.aui.serwer.database.exceptions.NoteNotFoundException;
import elka.aui.serwer.database.repo.NoteRepository;
import elka.aui.serwer.database.repo.UserRepository;
import elka.aui.serwer.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminRest {
    private final UserRepository userRepository;
    private final NoteRepository noteRepository;
    private UserService userService;

    @Autowired
    public AdminRest(UserRepository userRepository, NoteRepository noteRepository, UserService userService) {
        this.userRepository = userRepository;
        this.noteRepository = noteRepository;
        this.userService = userService;
    }


    /*************************************************************************/
    /**_____________________USERS____________________________________________*/
    /*************************************************************************/

    /** _____________________GROUP____________________________________________*/

    /**
     * Display all users
     *
     * @return
     */
    @GetMapping("/users")
    List<Users> users() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Users currentUser = userRepository.findByEmail(auth.getName());
        for(Role t : currentUser.getRoles()) {
            if(t.getRole().equals("ADMIN")) return userRepository.findAll();
        }
        throw new NoAccessException(currentUser);

    }

    /** _____________________SINGLE____________________________________________*/

    /**
     * Add new user (For logged user)
     */
    @PostMapping("/users")
    Users newUser(@RequestBody Users newUser) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Users currentUser = userRepository.findByEmail(auth.getName());
        for(Role t : currentUser.getRoles()) {
            if(t.getRole().equals("ADMIN"))  {
                 userService.saveUser(newUser);
                 return newUser;
            }
        }
        throw new NoAccessException(currentUser);
    }

    /**
     * Get user with id {id} (Only when user have access)
     *
     * @param id
     * @return
     */
    @GetMapping("/user/{id}")
    Users oneUser(@PathVariable Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Users currentUser = userRepository.findByEmail(auth.getName());

        for(Role t : currentUser.getRoles()) {
            if(t.getRole().equals("ADMIN"))  {
               Users user = userRepository.findById(id).orElseThrow(()->new NoteNotFoundException(id));
               return user;
            }
        }
        throw new NoAccessException(currentUser);
    }

    /**
     * Delete user with id {id} (Only when user have access)
     *
     * @param id
     */
    @DeleteMapping("/user/{id}")
    void deleteUser(@PathVariable Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Users currentUser = userRepository.findByEmail(auth.getName());

        for(Role t : currentUser.getRoles()) {
            if(t.getRole().equals("ADMIN"))  {
                Users user = userRepository.findById(id).orElseThrow(()->new NoteNotFoundException(id));
                userRepository.delete(user);
            }
        }
        throw new NoAccessException(currentUser);
    }

}
