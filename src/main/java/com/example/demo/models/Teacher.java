package com.example.demo.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "Teacher")
public class Teacher {

	@Id
	private String idTeacher;
	private String nameTeacher;
	private String age;
	private String dictatedCourses;



}
