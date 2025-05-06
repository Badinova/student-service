package ait.cohort55.student.service;

import ait.cohort55.student.dao.StudentRepository;
import ait.cohort55.student.dto.ScoreDto;
import ait.cohort55.student.dto.StudentAddDto;
import ait.cohort55.student.dto.StudentDto;
import ait.cohort55.student.dto.StudentUpdateDto;
import ait.cohort55.student.dto.exeptions.StudentNotFoundException;
import ait.cohort55.student.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.StreamSupport;

@Component
public class StudentServiceImpl implements StudentService{
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Boolean addStudent(StudentAddDto studentAddDto) {
        if (studentRepository.findById(studentAddDto.getId()).isPresent()) {
            return false;
        }
        Student student = new Student(studentAddDto.getId(), studentAddDto.getName(), studentAddDto.getPassword());
        studentRepository.save(student);
        return true;
    }

    @Override
    public StudentDto findStudent(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
        return new StudentDto(student.getId(), student.getName(), student.getScores());
    }

    @Override
    public StudentDto removeStudent(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
        studentRepository.deleteById(id);
        return new StudentDto(student.getId(), student.getName(), student.getScores());
    }

    @Override
    public StudentAddDto updateStudent(Long id, StudentUpdateDto studentUpdateDto) {
        Student student = studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
        if (studentUpdateDto.getName() != null) {
            student.setName(studentUpdateDto.getName());
        }
        if (studentUpdateDto.getPassword() != null) {
            student.setPassword(studentUpdateDto.getPassword());
        }
        studentRepository.save(student);
        return new StudentAddDto(student.getId(), student.getName(), student.getPassword());
    }

    @Override
    public Boolean addScore(Long id, ScoreDto scoreDto) {
        Student student = studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
        student.getScores().put(scoreDto.getExamName(), scoreDto.getScore());
        studentRepository.save(student);
        return true;
    }

    @Override
    public List<StudentDto> findStudentsByName(String name) {
        return StreamSupport.stream(studentRepository.findAll().spliterator(), false)
                .filter(student -> student.getName().equalsIgnoreCase(name))
                .map(student -> new StudentDto(student.getId(), student.getName(), student.getScores()))
                .toList();
    }

    @Override
    public Long getStudentsQuantityByNames(Set<String> names) {
        return StreamSupport.stream(studentRepository.findAll().spliterator(), false)
                .filter(student -> names.contains(student.getName()))
                .count();
    }

    @Override
    public List<StudentDto> findStudentsByExamNameMinScore(String exam, Integer minScore) {
        return StreamSupport.stream(studentRepository.findAll().spliterator(), false)
                .filter(student -> {Integer score = student.getScores().get(exam);
                return score != null && score >= minScore;
                })
                .map(student -> new StudentDto(student.getId(), student.getName(), student.getScores()))
                .toList();
    }
}
