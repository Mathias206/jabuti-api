package com.mathias.jabuti.domain.exception;

import com.mathias.jabuti.domain.service.DomainException;

public class DuplicatedEntityException extends DomainException {

    public DuplicatedEntityException(Class<?> entityClass, Long entityId) {
        super(MessageType.DUPLICATED_ENTITY.format(entityClass.getSimpleName(), entityId));
    }
}
