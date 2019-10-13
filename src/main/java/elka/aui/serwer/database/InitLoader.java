package elka.aui.serwer.database;

import elka.aui.serwer.database.entities.Note;
import elka.aui.serwer.database.entities.Role;
import elka.aui.serwer.database.entities.Users;
import elka.aui.serwer.database.exceptions.NoteNotFoundException;
import elka.aui.serwer.database.repo.NoteRepository;
import elka.aui.serwer.database.repo.RoleRepository;
import elka.aui.serwer.database.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Class for preloading value to database
 */
@Component
public class InitLoader implements CommandLineRunner {
    private final RoleRepository roleRepo;
    private final UserRepository userRepo;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private final NoteRepository noteRepo;


    @Autowired
    public InitLoader(@Qualifier("roleRepository") RoleRepository roleRepo, UserRepository userRepo, BCryptPasswordEncoder bCryptPasswordEncoder, NoteRepository noteRepo) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.noteRepo = noteRepo;
    }



    @Override
    public void run(String... args) throws Exception {
//        System.out.print("Zapisuje do Admina");
//        roleRepo.save(new Role("ADMIN"));
//        roleRepo.save(new Role("USER"));
//        roleRepo.save(new Role("PREMIUM"));
//
//        Users user = new Users();
//
//        user.setName("Test");
//        user.setLastName("Testowy");
//        user.setPassword("test1");
//        user.setEmail("test@test.pl");
//        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//        user.setActive(1);
//        Role userRole = roleRepo.findByRole("ADMIN");
//        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
//
//        userRepo.save(user);
//
//        Note note = new Note();
//        note.setUsers(user);
//        note.setContent("Lorem ipsum dolor sit amet, " +
//                "consectetur adipiscing elit, sed do " +
//                "eiusmod tempor incididunt ut labore et " +
//                "dolore magna aliqua. Ut enim ad minim" +
//                " veniam, quis nostrud exercitation ullamco" +
//                " laboris nisi ut aliquip ex ea commodo consequat." +
//                " Duis aute irure dolor in reprehenderit in" +
//                " voluptate velit esse cillum dolore eu fugiat" +
//                " nulla pariatur. Excepteur sint occaecat" +
//                " cupidatat non proident, sunt in culpa qui" +
//                " officia deserunt mollit anim id est laborum. ");
//        noteRepo.save(note);

    }
}
