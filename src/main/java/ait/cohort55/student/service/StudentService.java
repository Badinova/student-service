package ait.cohort55.student.service;

import ait.cohort55.student.dto.ScoreDto;
import ait.cohort55.student.dto.StudentAddDto;
import ait.cohort55.student.dto.StudentDto;
import ait.cohort55.student.dto.StudentUpdateDto;

import java.util.List;
import java.util.Set;

public interface StudentService {
    Boolean addStudent(StudentAddDto studentAddDto);
    StudentDto findStudent(Long id);
    StudentDto removeStudent(Long id);
    StudentAddDto updateStudent(Long id, StudentUpdateDto studentUpdateDto);
    Boolean addScore(Long id, ScoreDto scoreDto);
    List<StudentDto> findStudentsByName(String name);
    Long getStudentsQuantityByNames(Set<String> names);
    List<StudentDto> findStudentsByExamNameMinScore(String exam, Integer minScore);
}
