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
import com.tlqt.pojo.Action;
import com.tlqt.pojo.ActionPost;
import com.tlqt.pojo.ActionPostPK;
import com.tlqt.pojo.Choice;
import com.tlqt.pojo.Comment;
import com.tlqt.pojo.ContentType;
import com.tlqt.pojo.Email;
import com.tlqt.pojo.Invitation;
import com.tlqt.pojo.Post;
import com.tlqt.pojo.PostImage;
import com.tlqt.pojo.Survey;
import com.tlqt.pojo.SurveyQuestion;
import com.tlqt.pojo.User;
import com.tlqt.pojo.Vote;
import com.tlqt.pojo.VotePK;
import com.tlqt.services.ActionPostService;
import com.tlqt.services.ActionService;
import com.tlqt.services.ChoiceService;
import com.tlqt.services.CommentService;
import com.tlqt.services.ContentTypeService;
import com.tlqt.services.EmailService;
import com.tlqt.services.InvitationService;
import com.tlqt.services.PostImageService;
import com.tlqt.services.PostService;
import com.tlqt.services.QuestionService;
import com.tlqt.services.SurveyService;
import com.tlqt.services.UserService;
import com.tlqt.services.VoteService;
import java.io.IOException;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.NoResultException;
import javax.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
public class APIPostController {

    @Autowired
    UserService uService;

    @Autowired
    ContentTypeService ctService;

    @Autowired
    InvitationService iService;

    @Autowired
    PostService postService;

    @Autowired
    QuestionService qService;

    @Autowired
    ChoiceService cService;

    @Autowired
    PostImageService piService;

    @Autowired
    SurveyService surveyService;

    @Autowired
    CommentService commentService;

    @Autowired
    ActionPostService apService;

    @Autowired
    ActionService aService;

    @Autowired
    VoteService vService;

    @Autowired
    EmailService eService;

    @PostMapping(path = "/posts/", consumes = {
        MediaType.APPLICATION_JSON_VALUE,
        MediaType.MULTIPART_FORM_DATA_VALUE
    })
    @CrossOrigin
    public ResponseEntity<Object> createPost(@RequestParam Map<String, Object> params, @RequestPart MultipartFile[] files, Principal pr) throws ParseException, IOException {
        String title = (String) params.get("title");
        int contentTypeId = Integer.parseInt((String) params.get("contentTypeId"));
        boolean locked = Boolean.parseBoolean((String) params.get("locked"));
        String content = (String) params.get("content");
        String location = (String) params.get("location");
        String dateTimeStr = (String) params.get("dateTime");

        Post p = new Post();
        p.setUserId(uService.getUserByUsername(pr.getName()));
        p.setTitle(title);
        ContentType ct = ctService.getContentTypeById(contentTypeId);
        p.setContentTypeId(ct);
        p.setUnlocked(!locked);
        p.setContent(content);
        Instant currentTime = Instant.now();
        p.setCreatedAt(Date.from(currentTime));
        p.setUpdatedAt(Date.from(currentTime));
        postService.addPost(p);

        if (contentTypeId == 2) {
            SimpleDateFormat inputFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            Date dateTime = inputFormatter.parse(dateTimeStr);
            Invitation i = new Invitation();
            i.setLocation(location);
            i.setDateTime(dateTime);
            i.setPostId(p.getId());
            i.setPost(p);
            iService.addInvitation(i);

            System.out.println(params.get("mailCount"));
            int mailCount = Integer.parseInt((String) params.get("mailCount"));
            System.out.println((String) params.get(String.format("email%d", 0)));
            String testMail = (String) params.get(String.format("email%d", 0));
            if (!testMail.matches("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?")) {
                return new ResponseEntity<>("Invalid email format (abc@xyz.com)", HttpStatus.BAD_REQUEST);
            }

            for (int j = 0; j < mailCount; j++) {
                String email = (String) params.get(String.format("email%d", j));
                Email e = new Email();
                e.setEmail(email);
                e.setInvitationId(i);
                eService.add(e);

                User admin = uService.getUserByUsername(pr.getName());

                com.sendgrid.helpers.mail.objects.Email from = new com.sendgrid.helpers.mail.objects.Email(admin.getEmail());
                com.sendgrid.helpers.mail.objects.Email to = new com.sendgrid.helpers.mail.objects.Email(email);
                String subject = "You have an invitation!!!";
                Content econtent = new Content("text/plain", "You have an invitation from a post in Social Network. Sign in the site to find out!");
                Mail mail = new Mail(from, subject, to, econtent);

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
            }
        }

        if (contentTypeId == 3) {
            if (params.get("question0") != null) {
                Survey survey = new Survey();
                survey.setPostId(p.getId());
                survey.setPost(p);
                surveyService.addSurvey(survey);
                int i = 0;
                do {
                    SurveyQuestion sq = new SurveyQuestion();
                    sq.setContent((String) params.get(String.format("question%d", i)));
                    sq.setSurveyId(survey);
                    qService.addQuestion(sq);
                    int j = 0;
                    do {
                        Choice choice = new Choice();
                        choice.setContent((String) params.get(String.format("choice%d_%d", i, j)));
                        choice.setVoteCount(0);
                        choice.setSurveyQuestionId(sq);
                        cService.addChoice(choice);
                        j++;
                    } while (params.get(String.format("choice%d_%d", i, j)) != null);

                    i++;
                } while (params.get(String.format("question%d", i)) != null);

            }
        }

        if (files.length > 0) {
            for (MultipartFile f : files) {
                PostImage pi = new PostImage();
                pi.setFile(f);
                pi.setPostId(p);
                piService.addPostImage(pi);

            }
        }

        return new ResponseEntity<>("post successfully!!!", HttpStatus.CREATED);

    }

    @GetMapping(path = "/posts/", produces = {
        MediaType.APPLICATION_JSON_VALUE
    })
    @CrossOrigin
    public ResponseEntity<List<Post>> getPosts() {
        List<Post> posts = this.postService.getPosts();

        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping(path = "/posts/{postId}/get-invitation/", produces = {
        MediaType.APPLICATION_JSON_VALUE
    })
    @CrossOrigin
    public ResponseEntity<Invitation> getInvitation(@PathVariable(value = "postId") int postId) {
        Invitation invitation = this.iService.getInvitationByPostId(postId);

        return new ResponseEntity<>(invitation, HttpStatus.OK);
    }

    @GetMapping(path = "/posts/{postId}/get-questions/", produces = {
        MediaType.APPLICATION_JSON_VALUE
    })
    @CrossOrigin
    public ResponseEntity<List<SurveyQuestion>> getSurveyQuestions(@PathVariable(value = "postId") int postId) {
        List<SurveyQuestion> surveyQuestions = this.qService.getSurveyQuestionsByPostId(postId);
        System.out.println(surveyQuestions);

        return new ResponseEntity<>(surveyQuestions, HttpStatus.OK);
    }

    @GetMapping(path = "/posts/{postId}/get-contentType/", produces = {
        MediaType.APPLICATION_JSON_VALUE
    })
    @CrossOrigin
    public ResponseEntity<ContentType> getContentType(@PathVariable(value = "postId") int postId) {
        ContentType ct = ctService.getContentTypeByPostId(postId);

        return new ResponseEntity<>(ct, HttpStatus.OK);
    }

    @GetMapping(path = "/posts/{postId}/get-postImages/", produces = {
        MediaType.APPLICATION_JSON_VALUE
    })
    @CrossOrigin
    public ResponseEntity<List<PostImage>> getPostImages(@PathVariable(value = "postId") int postId) {
        List<PostImage> postImages = this.piService.getPostImagesByPostId(postId);

        return new ResponseEntity<>(postImages, HttpStatus.OK);
    }

    @GetMapping(path = "/posts/{postId}/get-author/", produces = {
        MediaType.APPLICATION_JSON_VALUE
    })
    @CrossOrigin
    public ResponseEntity<User> getAuthor(@PathVariable(value = "postId") int postId) {
        User u = uService.getAuthorByPostId(postId);

        return new ResponseEntity<>(u, HttpStatus.OK);
    }

    @GetMapping(path = "/posts/{postId}/get-comments/", produces = {
        MediaType.APPLICATION_JSON_VALUE
    })
    @CrossOrigin
    public ResponseEntity<List<Comment>> getComments(@PathVariable(value = "postId") int postId) {
        List<Comment> comments = commentService.getCommentsByPostId(postId);

        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @PutMapping(path = "/posts/{postId}/", consumes = {
        MediaType.APPLICATION_JSON_VALUE
    })
    @CrossOrigin
    public ResponseEntity<Object> updatePost(@RequestBody Map<String, String> params, @PathVariable(value = "postId") int postId) {

        Post p = postService.getPostById(postId);

        p.setTitle(params.get("title"));
        p.setContent(params.get("content"));
        p.setUnlocked(Boolean.parseBoolean(params.get("unlocked")));
        Instant currentTime = Instant.now();
        p.setUpdatedAt(Date.from(currentTime));

        postService.update(p);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(path = "/posts/{postId}/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable(value = "postId") int postId) {
        this.postService.deletePostById(postId);
    }

    @PostMapping(path = "/posts/{postId}/post-comment/", consumes = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<Object> postComment(@PathVariable(value = "postId") int postId, @RequestBody Map<String, String> params, Principal pr) {
        Comment c = new Comment();
        c.setContent(params.get("content"));
        c.setUserId(uService.getUserByUsername(pr.getName()));

        Post p = postService.getPostById(postId);
        c.setPostId(p);

        commentService.add(c);

        return new ResponseEntity<>(c.getId(), HttpStatus.CREATED);
    }

    @GetMapping(path = "/posts/{postId}/", produces = {
        MediaType.APPLICATION_JSON_VALUE
    })
    @CrossOrigin
    public ResponseEntity<Post> detail(@PathVariable(value = "postId") int postId) {
        Post p = postService.getPostById(postId);

        return new ResponseEntity<>(p, HttpStatus.OK);
    }

    @PostMapping(path = "/posts/{postId}/do-action/", consumes = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<Object> doAction(@PathVariable(value = "postId") int postId, @RequestBody Map<String, String> params, Principal pr) {
        User u = uService.getUserByUsername(pr.getName());
        int id = Integer.parseInt(params.get("id"));
        Action a = aService.getActionById(id);
        try {
            ActionPost ap = apService.getActionPostByIds(postId, u.getId());

            if (ap.getActionId().equals(a)) {
                apService.delete(ap);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                ap.setActionId(a);
                apService.update(ap);
                return new ResponseEntity<>(HttpStatus.OK);

            }
        } catch (NoResultException ex) {
            ActionPost ap = new ActionPost();
            ap.setActionId(a);

            Post p = postService.getPostById(postId);
            ap.setPost(p);
            ap.setUser(u);
            ap.setActionPostPK(new ActionPostPK(u.getId(), postId));

            apService.add(ap);

            return new ResponseEntity<>(HttpStatus.CREATED);

        }
    }

}
