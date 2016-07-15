package com.yon.controller;

import com.yon.dao.DAO;
import com.yon.model.Yon;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@Controller
public class YonController {
    private String currentUser;

    @Resource
    DAO dao;

    @RequestMapping(value = { "/", "/welcome" }, method = RequestMethod.GET)
    public ModelAndView welcomePage(Principal principal) {

        currentUser = principal.getName();
        List<Yon> listYons = dao.selectYonsByUser(currentUser);
        ModelAndView model = new ModelAndView();
        model.setViewName("yons");
        model.addObject("yonslist", listYons);
        return model;
    }

    @RequestMapping(value ="/user",  method = RequestMethod.POST)
    public String changeState (Model model, HttpServletRequest request) {

        String roomNumber = request.getParameter("roomNumber");
        String simNumber = request.getParameter("simNumber");

        if (roomNumber.length()>0) {
           dao.updateYonState(simNumber,roomNumber);
        }
        List<Yon> listYons = dao.selectYonsByUser(currentUser);
        model.addAttribute("yonslist", listYons);
        return "yons";
    }


//    @RequestMapping(value = "/admin", method = RequestMethod.GET)
//    public String adminPage(Model model) {
//        return "adminPage";
//    }
//
//    @RequestMapping(value = "/login", method = RequestMethod.GET)
//    public String loginPage(Model model ) {
//
//        return "loginPage";
//    }


    @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
    public String logoutSuccessfulPage(Model model) {
        model.addAttribute("title", "Logout");
        return "logoutSuccessfulPage";
    }
//
//
//    @RequestMapping(value = "/403", method = RequestMethod.GET)
//    public String accessDenied(Model model, Principal principal) {
//
//        if (principal != null) {
//            model.addAttribute("message", "Hi " + principal.getName()
//                    + "<br> You do not have permission to access this page!");
//        } else {
//            model.addAttribute("msg",
//                    "You do not have permission to access this page!");
//        }
//        return "403Page";
//    }
}