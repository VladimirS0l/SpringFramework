package ru.solarev.lesson2.repository;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.stereotype.Component;
import ru.solarev.lesson2.exception.NotFoundResourceException;
import ru.solarev.lesson2.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Component
@Getter
public class StudentRepository {
    private List<Student> students;
    private int counter = 0;

    @PostConstruct
    public void init() {
        students = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            students.add(new Student(i, "Student name #" + i,
                    Groups.values()[ThreadLocalRandom.current().nextInt(0, 3)].name()));
            counter++;
        }
    }

    enum Groups {
        IT, Physics, Math
    }

    public Student getById(int id) {
        return students.stream()
                .filter(s -> s.getId() == id)
                .findFirst()
                .orElseThrow( () ->
                        new NotFoundResourceException("Student not found by id"));
    }

    public List<Student> getStudentsByName(String studentName) {
        return students.stream()
                .filter(s -> s.getStudentName().contains(studentName))
                .collect(Collectors.toList());
    }

    public List<Student> getStudentsByGroupName(String groupName) {
        return students.stream()
                .filter(s -> s.getGroupName().equals(groupName))
                .collect(Collectors.toList());
    }

    public Student createStudent(Student student) {
        Student studentSave = new Student(counter++, student.getStudentName(), student.getGroupName());
        students.add(studentSave);
        return studentSave;
    }

    public void deleteStudentById(int id) {
        Student student = getById(id);
        students.remove(student);
    }

}




