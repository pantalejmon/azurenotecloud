package elka.aui.serwer.database.rest;


import elka.aui.serwer.database.entities.Note;
import elka.aui.serwer.database.entities.Users;
import elka.aui.serwer.database.exceptions.NoAccessException;
import elka.aui.serwer.database.exceptions.NoteNotFoundException;
import elka.aui.serwer.database.repo.FileRepository;
import elka.aui.serwer.database.repo.NoteRepository;
import elka.aui.serwer.database.repo.RoleRepository;
import elka.aui.serwer.database.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class MainRest {


    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final NoteRepository noteRepository;
    private final FileRepository fileRepository;

    /**
     * Constructor ...
     *
     * @param userRepository
     * @param roleRepository
     * @param noteRepository
     */

    @Autowired
    public MainRest(UserRepository userRepository, RoleRepository roleRepository, NoteRepository noteRepository, FileRepository fileRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.noteRepository = noteRepository;
        this.fileRepository = fileRepository;
    }

    /*************************************************************************/
    /**_____________________USERS____________________________________________*/
    /*************************************************************************/

    /**
     * Display information about current user logged in system
     *
     * @return
     */
    @GetMapping("/current")
    Users users() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByEmail(auth.getName());
    }

    /*************************************************************************/
    /**_____________________NOTES____________________________________________*/
    /*************************************************************************/

    /** _____________________GROUP____________________________________________*/

    /**
     * Display all users notes
     *
     * @return
     */
    @GetMapping("/notes")
    List<Note> notes() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Users currentUser = userRepository.findByEmail(auth.getName());
        List<Note> notelist = new ArrayList<Note>();
        for (Note t : noteRepository.findAll()) {
            if (t != null && t.getUsers().getId() == currentUser.getId()) {
                notelist.add(t);
            }
        }
        return notelist;
    }

    /** _____________________SINGLE____________________________________________*/

    /**
     * Add new note (For logged user)
     */
    @PostMapping("/notes")
    Note newNote(@RequestBody Note newNote) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Users currentUser = userRepository.findByEmail(auth.getName());
        newNote.setUsers(currentUser);
        // Zmienic na POST jak najszybciej
        final String uri = "https://phtcvt-20181205000425624.azurewebsites.net/api/phtcvt?string=" + newNote.getContent();
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        newNote.setLength(Integer.parseInt(result));
        return noteRepository.save(newNote);
    }

    /**
     * Get note with id {id} (Only when user have access)
     *
     * @param id
     * @return
     */
    @GetMapping("/notes/{id}")
    Note oneNote(@PathVariable Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Users currentUser = userRepository.findByEmail(auth.getName());
        Note note = noteRepository.findById(id).orElseThrow(() -> new NoteNotFoundException(id));
        if (note.getUsers().getId() == currentUser.getId()) return note;
        else throw new NoAccessException(currentUser);
    }

    /**
     * Delete note with id {id} (Only when user have access)
     *
     * @param id
     */
    @DeleteMapping("/notes/{id}")
    void deleteNote(@PathVariable Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Users currentUser = userRepository.findByEmail(auth.getName());
        Note note = noteRepository.findById(id).orElseThrow(() -> new NoteNotFoundException(id));
        if (note.getUsers().getId() == currentUser.getId()) noteRepository.delete(note);
        else throw new NoAccessException(currentUser);
    }

    /**
     * Replace or create note with id {id} (Only when user have access)
     *
     * @param newNote
     * @param id
     * @return
     */
    @PutMapping("/notes/{id}")
    Note putNote(@RequestBody Note newNote, @PathVariable Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Users currentUser = userRepository.findByEmail(auth.getName());
        Note note = noteRepository.findById(id).orElseThrow(() -> new NoteNotFoundException(id));
        if (note.getUsers().getId() == currentUser.getId()) {
            note.setContent(newNote.getContent());
            final String uri = "https://phtcvt-20181205000425624.azurewebsites.net/api/phtcvt?string=" + newNote.getContent();
            RestTemplate restTemplate = new RestTemplate();
            String result = restTemplate.getForObject(uri, String.class);
            note.setLength(Integer.parseInt(result));
            return noteRepository.save(note);
        }
        else throw new NoAccessException(currentUser);
    }

    @GetMapping("/notes/file")
    public @ResponseBody String countStrings(@RequestParam(value = "string" ) String string) {
        SecurityContextHolder.getContext().getAuthentication();

        final String uri = "https://phtcvt-20181205000425624.azurewebsites.net/api/phtcvt?string=" + string;

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);

        return result;
    }
}
