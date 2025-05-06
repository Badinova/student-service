package ait.cohort55.student.model;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class Student {
    private long id;
    @Setter
    private String name;
    @Setter
    private String password;
    private Map<String, Integer> scores;

    public Student(long id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
        scores = new HashMap<>();
    }

    public boolean addScore(String exam, int score) {
        return scores.put(exam, score) == null;
    }
}
