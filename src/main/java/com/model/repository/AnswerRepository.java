package com.model.repository;

import com.model.entities.Answer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class AnswerRepository implements Repository<Answer> {
    private final Map<Long, Answer> answerMap;
    public static final AtomicLong id = new AtomicLong(System.currentTimeMillis());

    public AnswerRepository() {
        answerMap = new HashMap<>();

        answerMap.put(1L, new Answer(1L, "Вряд ли ее можно познать полностью"));
        answerMap.put(2L, new Answer(2L, "Не знаю, зачем она нужна"));
        answerMap.put(3L, new Answer(3L, "Формула Коши"));
        answerMap.put(4L, new Answer(4L, "Правило Лопиталя-Бернулли"));
        answerMap.put(5L, new Answer(5L, "Теорема Лагранжа"));
        answerMap.put(6L, new Answer(6L, "\"Для любого натурального n>2 уравнение xn + yn = zn " +
            "не имеет решений в целых ненулевых числах\"."));
        answerMap.put(7L, new Answer(7L, "\"Сумма корней приведенного квадратного трехчлена $x^{2}+p x+q=0$ " +
            "равна его второму коэффициенту $p$ с противоположным знаком, а произведение - свободному члену $q$\"."));
        answerMap.put(8L, new Answer(8L, "\"Грани многогранника вместе с правилом склейки " +
            "полностью определяют выпуклый многогранник\"."));
        answerMap.put(9L, new Answer(9L, "Теорема Лагранжа устанавливает условия существования " +
            "хотя бы одной точки c, в которой касательная к графику функции параллельна секущей AB. " +
            "Таких точек может быть несколько\"."));
        answerMap.put(10L, new Answer(10L, "Теорема Лагранжа устанавливает, что однородные функции " +
            "пропорциональны скалярному произведению своего градиента на вектор своих переменных " +
            "с коэффициентом равным порядку однородности: v ⋅ ∇ f ( v ) = q f ( v )."));
        answerMap.put(11L, new Answer(11L, "Теорема Гаусса"));
        answerMap.put(12L, new Answer(12L, "Теорема Коши"));
        answerMap.put(13L, new Answer(13L, "Правило Крамера"));
    }

    public String getAnswerByID(Long id) {
        return answerMap.get(id).getText();
    }

    @Override
    public Collection<Answer> getAll() {
        return answerMap.values();
    }

    @Override
    public Optional<Answer> get(Long id) {
        return Optional.ofNullable(answerMap.get(id));
    }

    @Override
    public void create(Answer entity) {
        entity.setId(id.incrementAndGet());
        update(entity);
    }

    @Override
    public void update(Answer entity) {
        answerMap.put(entity.getId(), entity);
    }

    @Override
    public void delete(Answer entity) {
        answerMap.remove(entity.getId());
    }
}
