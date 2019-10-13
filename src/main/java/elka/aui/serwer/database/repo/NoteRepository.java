package elka.aui.serwer.database.repo;

import elka.aui.serwer.database.entities.Note;
import elka.aui.serwer.database.entities.Role;
import elka.aui.serwer.database.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("noteRepository")
public interface NoteRepository extends JpaRepository<Note, Long> {
    Note findByUsers(Users user);
}