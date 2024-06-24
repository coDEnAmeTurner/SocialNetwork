/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tlqt.controllers;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.tlqt.pojo.Admin;
import com.tlqt.pojo.FormLecturer;
import com.tlqt.pojo.Lecturer;
import com.tlqt.pojo.TypicalUser;
import com.tlqt.pojo.User;
import com.tlqt.services.AcademicRankService;
import com.tlqt.services.AdminService;
import com.tlqt.services.DegreeService;
import com.tlqt.services.LecturerService;
import com.tlqt.services.UserService;
import com.tlqt.services.TitleService;
import com.tlqt.services.TypicalUserService;
import com.tlqt.services.UserRoleService;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Admin
 */
@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private DegreeService degreeService;

    @Autowired
    private AcademicRankService academicRankService;

    @Autowired
    private TitleService titleService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private AcademicRankService arService;

    @Autowired
    private DegreeService dService;

    @Autowired
    private TypicalUserService tuService;

    @Autowired
    private AdminService adService;

    @Autowired
    private LecturerService lService;

    @RequestMapping("/")
    public String index(Model model) {
        return "index";
    }

    @ModelAttribute
    public void commonAttr(Model model) {
        model.addAttribute("degrees", degreeService.getDegrees());
        model.addAttribute("academicRanks", academicRankService.getAcademicRanks());
        model.addAttribute("titles", titleService.getTitles());
    }

    @RequestMapping("/users/")
    public String user(Model model) {
        List<Object[]> queriedAlumni = userService.getAlumniAndRelatedInfo();
        List<Object[]> queriedLecturers = userService.getLecturersAndRelatedInfo();

        model.addAttribute("queriedAlumni", queriedAlumni);
        model.addAttribute("queriedLecturers", queriedLecturers);

        return "user";
    }

    @GetMapping("/users/create-lecturer/")
    public String createLecturerView(Model model) {

        model.addAttribute("formLecturer", new FormLecturer());

        return "createLecturer";
    }

    @PostMapping("/users/create-lecturer/")
    @Inject
    public String createLecturer(@ModelAttribute(value = "formLecturer") @Valid FormLecturer fl,
            BindingResult br, Model model) {

        if (!br.hasErrors()) {
            try {
                try {
                    if (userService.getUserByUsername(fl.getUsername()) != null) {
                        model.addAttribute("usernameError","Username already exists!!!");
                        return "createLecturer";
                    }

                } catch (NoResultException ex) {

                }

                try {
                    if (userService.getUserByEmail(fl.getEmail()) != null) {
                        model.addAttribute("emailError","Email already exists!!!");
                        return "createLecturer";
                    }

                } catch (NoResultException ex) {

                }

                User u = new User();
                u.setFullName(fl.getFullName());
                u.setFile(fl.getFile());
                u.setUsername(fl.getUsername());
                u.setPassword("ou@123");
                SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
                u.setDob(dateFormatter.parse(fl.getDob()));
                u.setEmail(fl.getEmail());
                u.setPhone(fl.getPhone());
                u.setUserRoleId(userRoleService.getUserRoleById(3));
                Instant currentTime = Instant.now();
                u.setCreatedAt(Date.from(currentTime));
                u.setUpdatedAt(Date.from(currentTime));
                u.setBackground("https://res.cloudinary.com/dymtveeni/image/upload/v1719069374/irithyll_of_the_boreal_valley__2__by_twin_humanities_darpdcw_qcb6zj.jpg");
                u.setTheme("#eb9123");
                userService.addUser(u);

                TypicalUser tu = new TypicalUser();
                tu.setUserId(u.getId());
                tu.setUser(u);
                String academicRank = fl.getAcademicRankId();
                if (!academicRank.equals(""))
                    tu.setAcademicRankId(academicRankService.getAcademicRankById(Integer.parseInt(academicRank)));
                tu.setDegreeId(dService.getDegreeById(Integer.parseInt(fl.getDegreeId())));
                tuService.addTypicalUser(tu);

                Lecturer l = new Lecturer();
                l.setTypicalUserId(tu.getUserId());
                l.setTypicalUser(tu);
                l.setLocked(false);
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                User adUser = userService.getUserByUsername(authentication.getName());
                System.out.println(adUser.getEmail());
                Admin admin = adService.getAdminByUserId(adUser.getId());
                l.setDispatchingAdminId(admin);
                l.setTitleId(titleService.getTitleById(Integer.parseInt(fl.getTitleId())));
                lService.addLecturer(l);

                Email from = new Email(adUser.getEmail());
                Email to = new Email(u.getEmail());
                String subject = "Your LECTURER account has been created, login and change your password!!!";
                Content content = new Content("text/plain", String.format("username: %s\npassword: ou@123\nYou must change the password in 24 hours starting when this email is sent!!!", u.getUsername()));
                Mail mail = new Mail(from, subject, to, content);

                SendGrid sg = new SendGrid("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");
                Request request = new Request();

                try {
                    request.setMethod(Method.POST);
                    request.setEndpoint("mail/send");
                    request.setBody(mail.build());
                    Response response = sg.api(request);
                    System.out.println(response.getStatusCode());
                    System.out.println(response.getBody());
                    System.out.println(response.getHeaders());

                } catch (IOException ex) {
                    throw ex;
                }

                return "redirect:/users/";
            } catch (DataIntegrityViolationException e) {
                model.addAttribute("createError", e.getMessage());
                return "createLecturer";
            } catch (Exception ex) {
                model.addAttribute("createError", ex.getMessage());
                return "createLecturer";
            }
        }

        return "createLecturer";
    }
}
