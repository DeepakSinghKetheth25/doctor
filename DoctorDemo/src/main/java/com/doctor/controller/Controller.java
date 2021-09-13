package com.doctor.controller;

import java.util.Comparator;
import java.util.List;

import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.doctor.entity.Doctor;
import com.doctor.repository.DoctorRepository;

@RestController
public class Controller {

	@Autowired
	DoctorRepository repo;
	
	@PostMapping("/add-doctor")
	public Doctor addDoctor(@RequestBody Doctor doctor) {
		return repo.save(doctor);
	}
	
	
	@GetMapping("/doctor/{id}")
	public Doctor getDoctor(@PathVariable("id") Long id) {
		return repo.findById(id).orElse(null);
	}
	
	@GetMapping("/doctor")
	public List<Doctor> getAllDoctor(@QueryParam("language") String language) {
		List<Doctor> doctorList = repo.findAll();
		if(language!=null)
			doctorList.sort(Comparator.comparing(Doctor::getLanguage));
		return doctorList;
	}
	
	
}
