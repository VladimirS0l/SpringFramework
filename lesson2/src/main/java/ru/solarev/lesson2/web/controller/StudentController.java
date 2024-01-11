package ru.solarev.lesson2.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.solarev.lesson2.model.Student;
import ru.solarev.lesson2.repository.StudentRepository;

import java.util.List;

@RestController
@RequestMapping("v1/api/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentRepository studentRepository;

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<Student> getAllStudents() {
        return studentRepository.getStudents();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Student getStudentById(@PathVariable("id") int id) {
        return studentRepository.getById(id);
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public List<Student> getStudentsByName(@RequestParam String name) {
        return studentRepository.getStudentsByName(name);
    }

    @GetMapping("/group/{groupName}")
    @ResponseStatus(HttpStatus.OK)
    public List<Student> getStudentsByGroup(@PathVariable("groupName") String groupName) {
        return studentRepository.getStudentsByGroupName(groupName);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Student saveStudent(Student student) {
        return studentRepository.createStudent(student);
    }

    @DeleteMapping("delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteStudentById(@PathVariable("id") int id) {
        studentRepository.deleteStudentById(id);
    }

}
