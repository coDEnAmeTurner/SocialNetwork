/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tlqt.controllers;

import com.tlqt.components.JwtService;
import com.tlqt.pojo.AcademicRank;
import com.tlqt.pojo.Alumnus;
import com.tlqt.pojo.Degree;
import com.tlqt.pojo.Lecturer;
import com.tlqt.pojo.Post;
import com.tlqt.pojo.Title;
import com.tlqt.pojo.TypicalUser;
import com.tlqt.pojo.User;
import com.tlqt.pojo.UserRole;
import com.tlqt.services.AcademicRankService;
import com.tlqt.services.AlumnusService;
import com.tlqt.services.DegreeService;
import com.tlqt.services.LecturerService;
import com.tlqt.services.PostService;
import com.tlqt.services.TitleService;
import com.tlqt.services.TypicalUserService;
import com.tlqt.services.UserRoleService;
import com.tlqt.services.UserService;
import java.io.File;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Admin
 */
@RestController
//mapping chung cho ca controller
@RequestMapping("/api")
@CrossOrigin
public class APIUserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private DegreeService degreeService;
    @Autowired
    private AcademicRankService academicRankService;
    @Autowired
    private TypicalUserService typicalUserService;
    @Autowired
    private AlumnusService alumnnService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private PostService postService;
    @Autowired
    private LecturerService lecturerService;
    @Autowired
    private TitleService titleService;
    @Autowired
    private BCryptPasswordEncoder encoder;

    @PostMapping(path = "/users/", consumes = {
        MediaType.APPLICATION_JSON_VALUE,
        MediaType.MULTIPART_FORM_DATA_VALUE
    })
    @ResponseStatus(HttpStatus.CREATED)
    @CrossOrigin
    public ResponseEntity<Object> create(@RequestParam Map<String, String> params, @RequestPart MultipartFile[] file) throws ParseException {
        String userNameStr = params.get("username");
        String emailStr = params.get("email");

        try {
            if (userService.getUserByUsername(userNameStr) != null) {
                return new ResponseEntity<>("Username already exists!!!", HttpStatus.BAD_REQUEST);
            }

        } catch (NoResultException ex) {

        }

        try {
            if (userService.getUserByEmail(emailStr) != null) {
                return new ResponseEntity<>("Email already exists!!!", HttpStatus.BAD_REQUEST);
            }

        } catch (NoResultException ex) {

        }

        User user = new User();
        user.setFullName(params.get("fullName"));
        user.setUsername(userNameStr);
        user.setPassword(params.get("password"));
        user.setEmail(emailStr);
        user.setPhone(params.get("phone"));
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        user.setDob(dateFormatter.parse(params.get("dob")));
        user.setUserRoleId(userRoleService.getUserRoleById(2));
        Instant currentTime = Instant.now();
        user.setCreatedAt(Date.from(currentTime));
        user.setUpdatedAt(Date.from(currentTime));
        user.setBackground("https://res.cloudinary.com/dymtveeni/image/upload/v1719069374/irithyll_of_the_boreal_valley__2__by_twin_humanities_darpdcw_qcb6zj.jpg");
        if (file.length > 0) {
            user.setFile(file[0]);
        }

        this.userService.addUser(user);

        TypicalUser tu = typicalUserService.getTypicalUserByUserId(user.getId());
        Degree degree = this.degreeService.getDegreeById(Integer.parseInt(params.get("degreeId")));
        tu.setDegreeId(degree);
        String academicRankIdParam = params.get("academicRankId");
        AcademicRank academicRank = null;
        if (!academicRankIdParam.equals("")) {
            academicRank = this.academicRankService.getAcademicRankById(Integer.parseInt(academicRankIdParam));
        }
        tu.setAcademicRankId(academicRank);

        this.typicalUserService.addTypicalUser(tu);

        Alumnus alumnus = new Alumnus();
        alumnus.setTypicalUserId(tu.getUserId());
        alumnus.setTypicalUser(tu);
        alumnus.setStudentId(params.get("studentId"));

        this.alumnnService.addAlumnus(alumnus);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(path = "/login/", consumes = {
        MediaType.APPLICATION_JSON_VALUE})
    @CrossOrigin
    public ResponseEntity<Object> login(@RequestBody Map<String, String> user) {
        String username = user.get("username");
        String password = user.get("password");

        try {
            if (this.userService.authUser(username, password) == true) {
                String token = this.jwtService.generateTokenLogin(username);

                return new ResponseEntity<>(token, HttpStatus.OK);
            }

        } catch (Exception ex) {
            return new ResponseEntity<>("Username or password is wrong!!!", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Username or password is wrong!!!", HttpStatus.BAD_REQUEST);
    }

    @GetMapping(path = "/current-user/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<User> getCurrentUser(Principal p) {

        User u = this.userService.getUserByUsername(p.getName());

        return new ResponseEntity<>(
                u,
                HttpStatus.OK
        );
    }

    @GetMapping(path = "/users/get-user-role/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<UserRole> getUserRole(Principal pr) {

        User u = userService.getUserByUsername(pr.getName());

        UserRole ur = userRoleService.getUserRoleByUserId(u.getId());

        System.out.println(ur);

        return new ResponseEntity<>(ur, HttpStatus.OK);

    }

    @GetMapping(path = "/users/{userId}/get-posts", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<List<Post>> getPostsByAuthor(@PathVariable(value = "userId") int userId) {
        List<Post> posts = postService.getPostsByAuthorId(userId);

        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @PostMapping(path = "/users/{userId}/", consumes = {
        MediaType.APPLICATION_JSON_VALUE,
        MediaType.MULTIPART_FORM_DATA_VALUE
    })
    @ResponseStatus(HttpStatus.CREATED)
    @CrossOrigin
    public ResponseEntity<Object> update(@PathVariable(value = "userId") int userId, @RequestParam Map<String, String> params, @RequestPart(required = false) MultipartFile[] file, @RequestPart(required = false) MultipartFile[] file1) throws ParseException {
        User user = userService.getUserById(userId);

        String userNameStr = params.get("username");
        String emailStr = params.get("email");
        
        try {
            if (userService.getUserByUsername(userNameStr) != null && !user.getUsername().equals(userNameStr)) {
                return new ResponseEntity<>("Username already exists!!!", HttpStatus.BAD_REQUEST);
            }

        } catch (NoResultException ex) {

        }

        try {
            if (userService.getUserByEmail(emailStr) != null && !user.getEmail().equals(emailStr)) {
                return new ResponseEntity<>("Email already exists!!!", HttpStatus.BAD_REQUEST);
            }

        } catch (NoResultException ex) {

        }

        user.setFullName(params.get("fullName"));
        user.setUsername(userNameStr);
        user.setEmail(emailStr);
        user.setPhone(params.get("phone"));
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        user.setDob(dateFormatter.parse(params.get("dob")));
        if (file != null && file.length > 0) {
            user.setFile(file[0]);
        }
        
        if (file1 != null && file1.length > 0) {
            user.setFile1(file1[0]);
        }

        Boolean newPass = Boolean.parseBoolean(params.get("newPass"));
        if (newPass) {
            System.out.println("newPass accepted");
            String pCPass = params.get("currentPassword");
            String pNPass = params.get("newPassword");
            if (this.encoder.matches(pCPass, user.getPassword())) {
                user.setPassword(pNPass);

            } else {
                return new ResponseEntity<>("Current password is wrong!!!", HttpStatus.BAD_REQUEST);
            }
        }

        Instant currentTime = Instant.now();
        user.setUpdatedAt(Date.from(currentTime));
        this.userService.update(user, newPass);

        TypicalUser tu = typicalUserService.getTypicalUserByUserId(userId);
        tu.setUserId(user.getId());
        tu.setUser(user);
        Degree degree = this.degreeService.getDegreeById(Integer.parseInt(params.get("degreeId")));
        tu.setDegreeId(degree);
        String academicRankIdParam = params.get("academicRankId");
        AcademicRank academicRank = null;
        if (!academicRankIdParam.equals("")) {
            academicRank = this.academicRankService.getAcademicRankById(Integer.parseInt(academicRankIdParam));
        }
        tu.setAcademicRankId(academicRank);

        this.typicalUserService.update(tu);

        if (userRoleService.getUserRoleByUserId(userId).getRoleName().equals("alumnus")) {
            Alumnus alumnus = alumnnService.getAlumnusByTypicalUserId(userId);
            alumnus.setStudentId(params.get("studentId"));

            this.alumnnService.update(alumnus);

        }

        if (userRoleService.getUserRoleByUserId(userId).getRoleName().equals("lecturer")) {
            Lecturer lecturer = lecturerService.getLecturerByTypicalUserId(userId);
            int titleId = Integer.parseInt(params.get("titleId"));
            lecturer.setTitleId(titleService.getTitleById(titleId));

            this.lecturerService.update(lecturer);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "/users/{userId}/get-rank/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<AcademicRank> getRank(@PathVariable(value = "userId") int userId) {
        TypicalUser u = typicalUserService.getTypicalUserByUserId(userId);

        return new ResponseEntity<>(u.getAcademicRankId(), HttpStatus.OK);
    }

    @GetMapping(path = "/users/{userId}/get-degree/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<Degree> getDegree(@PathVariable(value = "userId") int userId) {
        TypicalUser u = typicalUserService.getTypicalUserByUserId(userId);

        return new ResponseEntity<>(u.getDegreeId(), HttpStatus.OK);
    }

    @GetMapping(path = "/users/{userId}/get-title/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<Title> getTitle(@PathVariable(value = "userId") int userId) {
        Lecturer u = lecturerService.getLecturerByTypicalUserId(userId);

        return new ResponseEntity<>(u.getTitleId(), HttpStatus.OK);
    }

    @GetMapping(path = "/users/{userId}/get-student-id/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<Object> getStudentId(@PathVariable(value = "userId") int userId) {
        Alumnus u = alumnnService.getAlumnusByTypicalUserId(userId);

        return new ResponseEntity<>(u.getStudentId(), HttpStatus.OK);
    }
    
    @GetMapping(path = "/users/count-posts/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<Object> countPost(Principal pr) {
        try {
            int count = postService.countPostsByUserId(userService.getUserByUsername(pr.getName()).getId());
            
            return new ResponseEntity<>(count, HttpStatus.OK);
        }catch (Exception ex ) {
            return new ResponseEntity<>("Cant count", HttpStatus.BAD_REQUEST);
        }
    }
}
