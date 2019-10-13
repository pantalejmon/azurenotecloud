package elka.aui.serwer.services;

import elka.aui.serwer.database.entities.Role;
import elka.aui.serwer.database.entities.Users;
import elka.aui.serwer.database.repo.RoleRepository;
import elka.aui.serwer.database.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;


@Service("userService")
    public class UserService {

        private UserRepository userRepository;
        private RoleRepository roleRepository;
        private BCryptPasswordEncoder bCryptPasswordEncoder;

        @Autowired
        public UserService(UserRepository userRepository,
                           RoleRepository roleRepository,
                           BCryptPasswordEncoder bCryptPasswordEncoder) {
            this.userRepository = userRepository;
            this.roleRepository = roleRepository;
            this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        }

        public Users findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

        public void saveUser(Users users) {

            users.setPassword(bCryptPasswordEncoder.encode(users.getPassword()));

            Role userRole = roleRepository.findByRole("USER");
            users.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
            users.setActive(1);
            userRepository.save(users);
        }

    }
