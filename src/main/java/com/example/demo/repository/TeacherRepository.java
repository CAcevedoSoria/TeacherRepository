package com.example.demo.repository;

import com.example.demo.models.Teacher;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface TeacherRepository extends ReactiveMongoRepository<Teacher,String> {

	Mono<Teacher> findBynameTeacher(String name);

}
