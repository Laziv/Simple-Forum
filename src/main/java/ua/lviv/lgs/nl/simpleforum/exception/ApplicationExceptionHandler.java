package ua.lviv.lgs.nl.simpleforum.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
public class ApplicationExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public String handle(ResourceNotFoundException exception, Model model) {
        model.addAttribute("message", exception.getMessage());
        log.error("Resource not found", exception);
        return "error";
    }

}
