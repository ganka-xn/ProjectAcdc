package com.model.repository;

import com.model.entities.Question;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class QuestionRepository implements Repository<Question> {
    private final Map<Long, Question> questionMap;
    public static final AtomicLong id = new AtomicLong(System.currentTimeMillis());

    public QuestionRepository() {
        questionMap = new HashMap<>();

        questionMap.put(1L, new Question(1L, "Ты хорошо знаешь математику?", Arrays.asList(1L, 2L), 1L));
        questionMap.put(2L, new Question(2L, "Что это за теорема?: <br>\"Предел отношения функций, " +
            "стремящихся одновременно к бесконечности или к нулю (являющихся одновременно бесконечно большими или бесконечно малыми), " +
            "равен пределу отношения их производных\"", Arrays.asList(3L, 4L, 5L), 4L));
        questionMap.put(3L, new Question(3L, "Как звучит теорема Виета?", Arrays.asList(6L, 7L, 8L), 7L));
        questionMap.put(4L, new Question(4L, "Что устанавливает теорема Лагранжа?", Arrays.asList(9L, 10L), 9L));
        questionMap.put(5L, new Question(5L, "Что это за теорема?: <br>\"Для любой хорды параметрически заданной плоской кривой, " +
            "вектор скорости которой (f/,g/) нигде не обращается в ноль, существует точка на дуге этой кривой с теми же концами, " +
            "что и хорда, в которой касательная параллельна хорде.\"", Arrays.asList(11L, 12L, 13L), 12L));
    }

    public Optional<Long> getCorrectAnswerID(Long questionID) {
        return get(questionID).map(Question::getCorrectAnswer);
    }

    @Override
    public Collection<Question> getAll() {
        return questionMap.values();
    }

    @Override
    public Optional<Question> get(Long id) {
        return Optional.ofNullable(questionMap.get(id));
    }

    @Override
    public void create(Question entity) {
        entity.setId(id.incrementAndGet());
        update(entity);
    }

    @Override
    public void update(Question entity) {
        questionMap.put(entity.getId(), entity);
    }

    @Override
    public void delete(Question entity) {
        questionMap.remove(entity.getId());
    }
}
