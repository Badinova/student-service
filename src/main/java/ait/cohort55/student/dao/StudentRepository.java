package ait.cohort55.student.dao;

import ait.cohort55.student.model.Student;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

public interface StudentRepository extends CrudRepository<Student, Long> {
    Stream<Student> findByNameIgnoreCase(String name);
    Stream<Student> findByNameIn(Set<String> names);
    @Query("{'scores.?0':{$gt:?1}}")
    List<Student> findByExamScoreGreaterThan(String exam, Integer minScore);

}
