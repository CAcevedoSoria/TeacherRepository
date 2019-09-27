package com.example.demo.controller;

import com.example.demo.models.Teacher;
import com.example.demo.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping
public class TeacherController {

	@Autowired
	private TeacherService teacherService;

	@PostMapping("/")
	public Mono<ResponseEntity<Teacher>> create(@Valid @RequestBody Teacher teacher) {

		return teacherService
			.save(teacher)
			.map(
				p ->
					ResponseEntity.created(URI.create("/".concat(p.getIdTeacher())))
						.contentType(MediaType.APPLICATION_JSON_UTF8)
						.body(p));
	}


	/**
	 * Find all mono.
	 *
	 * @return the mono
	 */
	@GetMapping("/")
	public Mono<ResponseEntity<Flux<Teacher>>> findAll() {
		return Mono.just(
			ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.body(teacherService.findAll()));
	}

	/**
	 * Find by id mono.
	 *
	 * @param id the id
	 * @return the mono
	 */
	@GetMapping("/{id}")
	public Mono<ResponseEntity<Teacher>> findById(@PathVariable String id) {
		return teacherService
			.findById(id)
			.map(p -> ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(p))
			.defaultIfEmpty(ResponseEntity.notFound().build());
	}


	@PutMapping("/{id}")
	public Mono<ResponseEntity<Teacher>> update(
		@RequestBody Teacher teacher, @PathVariable String id) {
		return teacherService
			.findById(id)
			.flatMap(
				p -> {

					p.setNameTeacher(teacher.getNameTeacher());
					p.setAge(teacher.getAge());
					p.setDictatedCourses(teacher.getDictatedCourses());;


					return teacherService.save(p);
				})
			.map(
				p ->

					ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(p))
			.defaultIfEmpty(ResponseEntity.notFound().build());
	}

	/**
	 * Eliminar mono.
	 *
	 * @param id the id
	 * @return the mono
	 */
	@DeleteMapping("/{id}")
	public Mono<ResponseEntity<Void>> eliminar(@PathVariable String id) {
		return teacherService
			.findById(id)
			.flatMap(
				p -> {
					return teacherService
						.delete(p)
						.then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)));
				})
			.defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
	}



	@GetMapping("name/{name}")
	public Mono<ResponseEntity<Teacher>> findByFullName(@PathVariable String name) {
		return teacherService
			.findBynameTeacher(name)
			.map(p -> ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(p))
			.defaultIfEmpty(ResponseEntity.notFound().build());

	}


	}





