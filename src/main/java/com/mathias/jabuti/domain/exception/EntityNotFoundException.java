package com.mathias.jabuti.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends RuntimeException {

  public EntityNotFoundException(Class<?> entityClass, Long id) {
    super(MessageType.ENITTY_NOT_FOUND.format(entityClass.getSimpleName(), id));
  }
}
