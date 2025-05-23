package com.mathias.jabuti.api.assembler;

import com.mathias.jabuti.api.model.ChildGoalDTO;
import com.mathias.jabuti.domain.model.Goal;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ChildGoalDTOAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public ChildGoalDTO toModel(Goal goal) {
        return modelMapper.map(goal, ChildGoalDTO.class);
   }

    public List<ChildGoalDTO> toCollectionModel(List<Goal> goals) {
        return goals.stream().map(goal -> toModel(goal)).collect(Collectors.toList());
    }

}
