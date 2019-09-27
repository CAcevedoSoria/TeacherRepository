package com.example.demo.controller;

import com.example.demo.models.Teacher;
import com.example.demo.repository.TeacherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.junit.Assert.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TeacherControllerTest {

	@Autowired
	private ApplicationContext applicationContext;
	@Autowired
	private TeacherRepository teacherRepository;
	private WebTestClient client;
	private List<Teacher> expectedProducts;
	@BeforeEach

	void setUp() {
		client = WebTestClient
			.bindToApplicationContext(applicationContext)
			.configureClient()
			.baseUrl("")
			.build();

		Flux<Teacher> initData = teacherRepository.deleteAll()
			.thenMany(Flux.just(
				Teacher.builder().idTeacher("T001").nameTeacher("Angela").age("20").dictatedCourses("1").build(),
				Teacher.builder().idTeacher("T002").nameTeacher("Javier").age("25").dictatedCourses("2").build())
				.flatMap(teacherRepository::save))
			.thenMany(teacherRepository.findAll());

		expectedProducts = initData.collectList().block();

	}


	@Test
	void create() {

		Teacher expectedProduct = expectedProducts.get(0);

		client.post().uri("/").body(Mono.just(expectedProduct), Teacher.class).exchange()
			.expectStatus().isCreated();

	}

	@Test
	void findAll() {

			client.get().uri("/").exchange()
				.expectStatus().isOk();
	}

	@Test
	void findById() {

		String id = "T001";
		client.get().uri("/{id}", id).exchange()
			.expectStatus().isOk();
	}

	@Test
	void update() {

		Teacher expectedProduct = expectedProducts.get(0);

		client.put().uri("/{id}", expectedProduct.getIdTeacher()).body(Mono.just(expectedProduct), Teacher.class).exchange()
			.expectStatus().isOk();

	}

	@Test
	void eliminar() {
		Teacher teacher = expectedProducts.get(0);
		client.delete().uri("/{id}", teacher.getIdTeacher()).exchange()
			.expectStatus().isNoContent();
	}

	@Test
	void findByFullName() {



		String title = "Angela";
		client.get().uri("/name/{title}", title).exchange()
			.expectStatus().isOk();

	}
}