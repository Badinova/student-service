package ait.cohort55.student.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.Map;

@Getter
@Document(collection = "students")
public class Student {
    @Id
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
