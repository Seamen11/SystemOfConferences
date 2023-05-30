package com.example.systemofconferences.basicSettings;

import com.example.systemofconferences.blog.Blog;
import com.example.systemofconferences.blog.BlogService;
import com.example.systemofconferences.configuration.UserInfo;
import com.example.systemofconferences.configuration.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class AppController {

    private final ConferenceService conferenceService;
    private final BlogService blogService;

    @Autowired
    private UserService userService;

    @GetMapping("/blog")
    public String showBlog(Model model, @Param("keyword") String keyword) {
        List<Blog> listPosts = blogService.listAll(keyword);
        model.addAttribute("listPosts", listPosts);
        model.addAttribute("keyword", keyword);
        return "blog";
    }
    @GetMapping("/new_post")
    public String showNewPostForm(Model model) {
        Blog blog = new Blog();
        model.addAttribute("blog", blog);
        return "new_post";
    }
    @PostMapping("/save_post")
    public String savePost(@ModelAttribute("blog") Blog blog) {
        blogService.save(blog);
        return "redirect:/blog";
    }

    @GetMapping("/delete_post/{post_id}")
    public String deletePost(@PathVariable(name = "post_id") Long post_id) {
        blogService.delete(post_id);
        return "redirect:/blog";
    }

    @GetMapping("/edit_post/{post_id}")
    public ModelAndView showEditUserForm(@PathVariable(name="post_id") Long post_id) {
        ModelAndView mav = new ModelAndView("edit_post");
        Blog blog = blogService.get(post_id);
        mav.addObject("Blog", blog);
        return mav;
    }

    @PostMapping("/auth/register")
    //@PreAuthorize("hasAuthority('ADMIN')")
    public String addNewUser(@ModelAttribute UserInfo userInfo, @RequestParam String name, @RequestParam String roles, HttpSession session) {
        System.out.println("UserInfo object is: " + userInfo);
        userService.addUser(userInfo);
        session.setAttribute("username", name);
        session.setAttribute("roles", roles);
        return "redirect:/";
    }
    @GetMapping("/auth/register")
    public String register(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return "register";
    }


    @GetMapping("/")
    public String viewHomePage(Model model, @Param("keyword") String keyword){
        List<Conference> listConference = conferenceService.listAll(keyword);
        model.addAttribute("listConference", listConference);
        model.addAttribute("keyword", keyword);
        return "index";
    }
    @GetMapping("/new")
    public String newConference(Model model) {
        model.addAttribute("conference", new Conference());
        return "new_conference";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveConference(@ModelAttribute("conference") Conference conference){
        conferenceService.save(conference);
        return "redirect:/";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView showEditConferenceFrom(@PathVariable(name="id") Long id){
        ModelAndView mav = new ModelAndView("edit_conference");
        Conference conference = conferenceService.get(id);
        mav.addObject("conference", conference);
        return mav;
    }


    @RequestMapping("/delete/{id}")
    public String deleteConference(@PathVariable(name="id") Long id){
        conferenceService.delete(id);
        return "redirect:/";
    }

    @GetMapping("/login_page")
    public String showLogin() {
        return "login_page";
    }

    @GetMapping("/about")
    public String showAbout() {
        return "about";
    }
    @GetMapping("/main")
    public String showMain() {
        return "index";
    }

    @PostMapping("/login_page")
    public String SuccessLogin(@RequestParam String username, HttpSession session) {
        System.out.println("Username is: " + username);
        final String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        session.setAttribute("username", currentUser);
        return "redirect:/";
    }
}

