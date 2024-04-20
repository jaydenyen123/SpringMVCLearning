package guru.springframework.spring6restmvc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "value not found")
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Just for demoing")
public class NotFoundException extends RuntimeException {

}
