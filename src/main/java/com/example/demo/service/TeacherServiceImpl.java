package com.example.demo.service;


import com.example.demo.models.Teacher;
import com.example.demo.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TeacherServiceImpl implements TeacherService {

	@Autowired
	private TeacherRepository teacherRepository;


	@Override
	public Flux<Teacher> findAll() {
		return teacherRepository.findAll();
	}

	@Override
	public Mono<Teacher> findById(String id) {
		return teacherRepository.findById(id);
	}

	@Override
	public Mono<Teacher> save(Teacher teacher) {
		return teacherRepository.save(teacher);
	}

	@Override
	public Mono<Void> delete(Teacher teacher) {
		return teacherRepository.delete(teacher);
	}

	@Override
	public Mono<Teacher> findBynameTeacher(String name) {
		return teacherRepository.findBynameTeacher(name);
	}
}



