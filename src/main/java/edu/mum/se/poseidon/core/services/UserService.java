package edu.mum.se.poseidon.core.services;

import edu.mum.se.poseidon.core.controllers.dto.UserDto;
import edu.mum.se.poseidon.core.repositories.AdminRepository;
import edu.mum.se.poseidon.core.repositories.FacultyRepository;
import edu.mum.se.poseidon.core.repositories.StudentRepository;
import edu.mum.se.poseidon.core.repositories.UserRepository;
import edu.mum.se.poseidon.core.repositories.models.users.Admin;
import edu.mum.se.poseidon.core.repositories.models.users.Faculty;
import edu.mum.se.poseidon.core.repositories.models.users.Student;
import edu.mum.se.poseidon.core.repositories.models.users.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Yuriy Yugay on 10/11/2017.
 *
 * @author Yuriy Yugay
 */
@Service
public class UserService {

    private UserRepository userRepository;
    private StudentRepository studentRepository;
    private FacultyRepository facultyRepository;
    private AdminRepository adminRepository;

    @Autowired
    public UserService(UserRepository userRepository, StudentRepository studentRepository,
    		FacultyRepository facultyRepository, AdminRepository adminRepository) {
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
        this.facultyRepository = facultyRepository;
        this.adminRepository = adminRepository;
    }

    public User getUserByUsername(String username, String password) {
        User user = userRepository.findUserByUsername(username);
        if(user == null) {
            return null;
        }
        String pass = user.getPassword();

        if(!pass.equals(password)) {
            return null;
        }

        return user;
    }
    
    public User createUser(UserDto userDto) {
    	
    	if(userDto.getType().equals("student")) {
    		Student student = new Student();
    		student.setFirstName(userDto.getFirstName());
    		student.setLastName(userDto.getLastName());
    		student.setUsername(userDto.getUsername());
    		student.setPassword(userDto.getPassword());
    		student.setType(userDto.getType());
    		student = studentRepository.save(student);
    		return student;
    	}
    	else if(userDto.getType().equals("faculty")) {
    		Faculty faculty = new Faculty();
    		faculty.setFirstName(userDto.getFirstName());
    		faculty.setLastName(userDto.getLastName());
    		faculty.setUsername(userDto.getUsername());
    		faculty.setPassword(userDto.getPassword());
    		faculty.setType(userDto.getType());
    		faculty = facultyRepository.save(faculty);
    		return faculty;
    	}
    	else {
    		Admin admin = new Admin();
    		admin.setFirstName(userDto.getFirstName());
    		admin.setLastName(userDto.getLastName());
    		admin.setUsername(userDto.getUsername());
    		admin.setPassword(userDto.getPassword());
    		admin.setType(userDto.getType());
    		admin = adminRepository.save(admin);
    		return admin;
    	}
	}
    
    public List<User> getUserList(){
    	return userRepository.findAll();
    }
}
