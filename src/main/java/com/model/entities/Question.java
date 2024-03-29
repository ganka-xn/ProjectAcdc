package com.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question {

    private Long id;
    private String text;
    private List<Long> answers;
    @Getter
    private Long correctAnswer;

}
