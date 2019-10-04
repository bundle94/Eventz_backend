package com.eventz.io.eventz.controllers;

import com.eventz.io.eventz.config.cache.EventzRedisCache;
import com.eventz.io.eventz.config.cache.MemoryCache;
import com.eventz.io.eventz.services.contract.UserServices;
import com.eventz.io.eventz.services.contract.ValidationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by Michael.Akobundu on 8/5/2019.
 */
@RestController
@ApiIgnore
public class IndexController {

    private final MemoryCache memoryCache;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public IndexController(EventzRedisCache memoryCache) {
        this.memoryCache = memoryCache;
    }

    @RequestMapping(name = "DOCUMENTATION", value = "/docs", method = RequestMethod.GET)
    public void viewDocs(HttpServletResponse response) throws Exception{
        response.sendRedirect("/swagger-ui.html");
    }

    @RequestMapping(name="TESTCACHE", value="/testCache", method = RequestMethod.DELETE)
    @ResponseBody
    public boolean testcache() throws Exception {
        return memoryCache.ping();
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home/index");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/signin", method = RequestMethod.GET)
    public ModelAndView signin() throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home/signin");
        return modelAndView;
    }


}
