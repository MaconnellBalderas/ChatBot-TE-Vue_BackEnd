package com.techelevator.controller;

import com.techelevator.authentication.AuthProvider;
import com.techelevator.authentication.UnauthorizedException;
import com.techelevator.model.Curriculum;
import com.techelevator.model.CurriculumDao;
import com.techelevator.model.Pathway;
import com.techelevator.model.PathwayDao;
import com.techelevator.model.Quote;
import com.techelevator.model.QuoteDao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat")
@CrossOrigin 
public class ApiController {

    @Autowired
    private PathwayDao pathwayDao;
    @Autowired
    private QuoteDao quoteDao;
    @Autowired
    private CurriculumDao curriculumDao;
    
    @GetMapping("/quote")
    public List<Quote> getQuote() {
    	return quoteDao.getRandomQuote();
    }
    
    @GetMapping("/pathway")
    public List<Pathway> getPathway() {
    	return pathwayDao.pathways();
    }
    
    @GetMapping("/curriculum")
    public List<Curriculum> getCurriculum() {
    	return curriculumDao.getCurriculums();
    }
//    @GetMapping
//    public Pathway getPathway() {
//    	return pathwayDao.getPathwayByTopic(topic);
//    }
    
    //fetch  attach what ever id query string to api  
    
//    @RequestMapping(path = "/", method = RequestMethod.GET)
//    public String authorizedOnly() throws UnauthorizedException {
//        /*
//        You can lock down which roles are allowed by checking
//        if the current user has a role.
//        
//        In this example, if the user does not have the admin role
//        we send back an unauthorized error.
//        */
//        if (!authProvider.userHasRole(new String[] { "admin" })) {
//            throw new UnauthorizedException();
//        }
//        return "Success";
//    }
}