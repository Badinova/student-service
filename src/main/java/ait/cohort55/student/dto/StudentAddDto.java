package ait.cohort55.student.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StudentAddDto {
    private Long id;
    private String name;
    private String password;
}
