package com.produtos.montaveis.challenges.api.controller

import com.produtos.montaveis.challenges.domain.model.Student
import com.produtos.montaveis.challenges.domain.repository.StudentRepository
import lombok.AllArgsConstructor
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/students")
class StudentController(val studentRepository: StudentRepository) {

    @GetMapping
    fun listStudents(): List<Student> {
        return studentRepository.findAll()
    }

    @GetMapping("/{studentId}")
    fun getStudent(@PathVariable studentId: Long): ResponseEntity<Student> {
        return if (studentRepository.existsById(studentId))
            ResponseEntity.ok(studentRepository.findByIdOrNull(studentId))
        else ResponseEntity.notFound().build()
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addStudent(@RequestBody student: Student): ResponseEntity<Student> {
        return ResponseEntity.ok(studentRepository.save(student));
    }

    @PutMapping("/{studentId}")
    fun updateStudent(@PathVariable studentId: Long, @RequestBody student: Student): ResponseEntity<Student> {
        return if (studentRepository.existsById(studentId)) {
            val updatedStudent = Student(studentId, student.name, student.level, student.exercisesCompleted)
            ResponseEntity.ok(studentRepository.save(updatedStudent))
        } else ResponseEntity.notFound().build()
    }

    @DeleteMapping("/{studentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteStudent(@PathVariable studentId: Long): ResponseEntity<Student> {
        return if (studentRepository.existsById(studentId)) {
            studentRepository.deleteById(studentId)
            ResponseEntity.noContent().build()
        } else ResponseEntity.notFound().build()
    }

}