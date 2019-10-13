package elka.aui.serwer.database.repo;

import elka.aui.serwer.database.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<Users, Long> {

    Users findByEmail(String email);
}
