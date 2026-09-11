package com.example.demo.repository;
 
import com.example.demo.entity.Student;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
 
import java.util.List;
import java.util.Optional;
 
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
 
@DataJpaTest
class StudentRepositoryTest {
 
    @Autowired
    private StudentRepository studentRepository;
 
    @AfterEach
    void cleanUp() {
        studentRepository.deleteAll();
    }
 
    @Test
    @DisplayName("CREATE: save a new student and verify it gets a generated ID")
    void testCreateStudent() {
        Student saved = studentRepository.save(new Student("Alice", 85));
        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getName()).isEqualTo("Alice");
    }
 
    @Test
    @DisplayName("READ: find a student by ID")
    void testReadStudentById() {
        Student saved = studentRepository.save(new Student("Bob", 72));
        Optional<Student> found = studentRepository.findById(saved.getId());
        assertTrue(found.isPresent());
        assertThat(found.get().getName()).isEqualTo("Bob");
    }
 
    @Test
    @DisplayName("READ: find all students")
    void testReadAllStudents() {
        studentRepository.save(new Student("Charlie", 90));
        studentRepository.save(new Student("Diana", 65));
        List<Student> students = studentRepository.findAll();
        assertThat(students).hasSize(2);
    }
 
    @Test
    @DisplayName("UPDATE: modify an existing student")
    void testUpdateStudent() {
        Student saved = studentRepository.save(new Student("Grace", 60));
        Student toUpdate = studentRepository.findById(saved.getId()).orElseThrow();
        toUpdate.setTestscore(88);
        studentRepository.save(toUpdate);
        Student updated = studentRepository.findById(saved.getId()).orElseThrow();
        assertThat(updated.getTestscore()).isEqualTo(88);
    }
 
    @Test
    @DisplayName("DELETE: remove a student by ID")
    void testDeleteStudent() {
        Student saved = studentRepository.save(new Student("Henry", 55));
        studentRepository.deleteById(saved.getId());
        assertFalse(studentRepository.findById(saved.getId()).isPresent());
    }
}
 