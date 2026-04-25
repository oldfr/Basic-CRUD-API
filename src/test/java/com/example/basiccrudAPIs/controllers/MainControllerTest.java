package com.example.basiccrudAPIs.controllers;

import com.example.basiccrudAPIs.entites.Student;
import com.example.basiccrudAPIs.repo.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(MainController.class)
class MainControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentRepository mockRepository;

    @Test
    void testAddStudent() throws Exception {
        // Setup
        // Configure StudentRepository.save(...).
        final Student student = new Student();
        student.setStudentName("studentName");
        student.setStudentAge("studentAge");
        student.setAddress("address");
        when(mockRepository.save(any(Student.class))).thenReturn(student);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/students")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testGetStudents() throws Exception {
        // Setup
        // Configure StudentRepository.findAll(...).
        final Student student = new Student();
        student.setStudentName("studentName");
        student.setStudentAge("studentAge");
        student.setAddress("address");
        final List<Student> students = List.of(student);
        when(mockRepository.findAll()).thenReturn(students);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/students")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testGetStudents_StudentRepositoryReturnsNoItems() throws Exception {
        // Setup
        when(mockRepository.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/students")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("[]");
    }

    @Test
    void testUpdateStudentAddress() throws Exception {
        // Setup
        // Configure StudentRepository.getById(...).
        final Student student = new Student();
        student.setStudentName("studentName");
        student.setStudentAge("studentAge");
        student.setAddress("address");
        when(mockRepository.getById(0)).thenReturn(student);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(put("/students")
                        .param("Address", "address")
                        .param("Id", "0")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
        verify(mockRepository).updateAddressById("address", 0);
    }

    @Test
    void testDeleteStudent() throws Exception {
        // Setup
        when(mockRepository.existsById(0)).thenReturn(false);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(delete("/students")
                        .param("Id", "0")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
        verify(mockRepository).deleteById(0);
    }
}
