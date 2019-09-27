package com.example.demo.service;

import com.example.demo.models.Teacher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface TeacherService {

	public Flux<Teacher> findAll();

	public Mono<Teacher> findById(String id);

	public Mono<Teacher> save(Teacher course);

	public Mono<Void> delete(Teacher course);

	public Mono<Teacher> findBynameTeacher(String name);

}
