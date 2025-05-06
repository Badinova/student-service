package ait.cohort55.student.dao;

import ait.cohort55.student.model.Student;
import java.util.Optional;

public interface StudentRepository {
    Student save(Student student);

    Optional<Student> findById(Long id);

    void deleteById(Long id);

    Iterable<Student> findAll();
}
