package guru.springframework.spring6restmvc.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "value not found")
@ResponseStatus(value = HttpStatus.HTTP_VERSION_NOT_SUPPORTED, reason = "Just for demoing")
public class NotFoundException extends RuntimeException {

}
