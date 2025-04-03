package com.mathias.jabuti.api.assembler;

import com.mathias.jabuti.api.model.input.GoalInputDTO;
import com.mathias.jabuti.domain.model.Goal;
import com.mathias.jabuti.domain.model.User;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GoalDTODisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Goal toDomainObject(GoalInputDTO goalInput) {
        return modelMapper.map(goalInput, Goal.class);
    }

    public void copyToDomainObject(GoalInputDTO goalInputDTO, Goal goal) {
        /*
        To avoid: org.springframework.orm.jpa.JpaSystemException: identifier of an
        instance of com.mathias.jabuti.domain.model.User was altered from 2 to 1
         */
        goal.setUser(new User());
        modelMapper.map(goalInputDTO, goal);

    }
}
