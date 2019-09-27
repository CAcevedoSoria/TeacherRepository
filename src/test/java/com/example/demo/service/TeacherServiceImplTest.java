package com.example.demo.service;

import com.example.demo.models.Teacher;
import com.example.demo.repository.TeacherRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.reactivestreams.Publisher;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class TeacherServiceImplTest {

  @Mock
  private TeacherRepository teacherRepository;

  @InjectMocks
  private TeacherServiceImpl serviceCourse;

  @Test
  void findAll() {

    Teacher teacher = new Teacher();
    teacher.setIdTeacher("T001");
    teacher.setNameTeacher("Angela");
    teacher.setAge("21");
    teacher.setDictatedCourses("2");



    when(serviceCourse.findAll()).thenReturn(Flux.just(teacher));

    Flux<Teacher> actual = serviceCourse.findAll();

    assertResults(actual, teacher);
  }

  @Test
  void findById() {
    Teacher teacher = new Teacher();
    teacher.setIdTeacher("T001");
    teacher.setNameTeacher("Angela");
    teacher.setAge("21");
    teacher.setDictatedCourses("2");

    when(teacherRepository.findById(teacher.getIdTeacher())).thenReturn(Mono.just(teacher));
    Mono<Teacher> actual = serviceCourse.findById(teacher.getIdTeacher());

    assertResults(actual, teacher);

  }

  @Test
  void save() {

    Teacher teacher = new Teacher();
    teacher.setIdTeacher("T001");
    teacher.setNameTeacher("Angela");
    teacher.setAge("21");
    teacher.setDictatedCourses("2");

    when(teacherRepository.save(teacher)).thenReturn(Mono.just(teacher));

    Mono<Teacher> actual = serviceCourse.save(teacher);

    assertResults(actual, teacher);
  }

  @Test
  void delete() {

    Teacher teacher = new Teacher();
    teacher.setIdTeacher("T001");
    teacher.setNameTeacher("Angela");
    teacher.setAge("21");
    teacher.setDictatedCourses("2");
    when(teacherRepository.delete(teacher)).thenReturn(Mono.empty());
  }

  @Test
  void findBynameTeacher() {
    Teacher teacher = new Teacher();
    teacher.setIdTeacher("T001");
    teacher.setNameTeacher("Angela");
    teacher.setAge("21");
    teacher.setDictatedCourses("2");

    when(teacherRepository.findBynameTeacher("Angela")).thenReturn(Mono.just(teacher));

    Mono<Teacher> actual = serviceCourse.findBynameTeacher("Angela");

    assertResults(actual, teacher);

  }

  private void assertResults(Publisher<Teacher> publisher, Teacher... expectedProducts) {
    StepVerifier.create(publisher).expectNext(expectedProducts).verifyComplete();
  }
}