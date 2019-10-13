package elka.aui.serwer.database.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * Class for exception response
 */
@ControllerAdvice
public class ExceptionsView {

    @ResponseBody
    @ExceptionHandler(NoteNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String noteNotFoundHandler(NoteNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(NoAccessException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    String noAccessHandler(NoAccessException ex) {
        return ex.getMessage();
    }
}
