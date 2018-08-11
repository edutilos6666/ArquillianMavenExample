package com.edutilos.controller;

import com.edutilos.dao.WorkerDAO;
import com.edutilos.model.Worker;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by edutilos on 04.06.18.
 */
@Controller
@RequestMapping("/worker/json")
public class WorkerJsonController {
    @Inject
    private WorkerDAO daoJpaImpl;

    public WorkerJsonController() {
//        daoJpaImpl = new WorkerDAOImpl();
    }



    @GetMapping("/save/{id}/{name}/{age}/{wage}/{active}")
    public ModelAndView save(@PathVariable long id, @PathVariable String name,
                                   @PathVariable int age, @PathVariable double wage,
                                   @PathVariable boolean active) {
        Worker w = new Worker(id, name, age, wage, active);
        daoJpaImpl.insert(w);
        return new ModelAndView("redirect:/worker/json/findAll");
    }


    @GetMapping("/update/{id}/{name}/{age}/{wage}/{active}")
    public ModelAndView update(@PathVariable long id, @PathVariable String name,
                                     @PathVariable int age, @PathVariable double wage,
                                     @PathVariable boolean active) {
        daoJpaImpl.update(id, new Worker(id, name, age, wage, active));
        return new ModelAndView("redirect:/worker/json/findAll");
    }

    @GetMapping("/remove/{id}")
    public ModelAndView remove(@PathVariable long id) {
        daoJpaImpl.delete(id);
        return new ModelAndView("redirect:/worker/json/findAll");
    }

    @GetMapping("/findById/{id}")
    public ModelAndView findById(@PathVariable long id) {
        Worker w= daoJpaImpl.findById(id);
        ModelAndView mav = new ModelAndView("jsonView");
        mav.addObject("worker", w);
        return mav;
    }

    @GetMapping("/findAll")
    public ModelAndView findAll() {
        List<Worker> all = daoJpaImpl.findAll();
        ModelAndView mav = new ModelAndView("jsonView");
        mav.addObject("workers", all);
        return mav;
    }

    //headers and consumes in @PostMapping just narrows , does not do anything
    //@PostMapping(value = "/save", headers="content-type=application/json")
    @PostMapping(value = "/save")
    public ModelAndView save(@RequestBody @Validated Worker worker,
                                   BindingResult result) {
        ModelAndView mav = new ModelAndView("redirect:/worker/json/findAll");
        if(result.hasErrors())
            return mav;
        daoJpaImpl.insert(worker);
        return mav;
    }

    @PostMapping(value="/update")
    public ModelAndView update(@RequestBody @Validated Worker worker,
                                     BindingResult result) {
        ModelAndView mav = new ModelAndView("redirect:/worker/json/findAll");
        if(result.hasErrors())
            return mav;
        daoJpaImpl.update(worker.getId(), worker);
        return mav;
    }


    //with response entity
    @GetMapping("/findAll/re")
    public ResponseEntity<List<Worker>> findAllRE() {
        List<Worker> workers = daoJpaImpl.findAll();
        return new ResponseEntity<List<Worker>>(workers, HttpStatus.OK);
    }


    @PostMapping("/save/re")
    public ResponseEntity<List<Worker>> saveRE(@RequestBody @Validated Worker worker,
                                                     BindingResult result,
                                                     UriComponentsBuilder ucBuilder) {
        if(result.hasErrors()) {
//            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
            return findAllRE();
        }
        daoJpaImpl.insert(worker);
        HttpHeaders headers = new HttpHeaders();
        try {
            headers.setLocation(ucBuilder.path("redirect:/worker/json/findAll/re").build().toUri());
        } catch (Exception e) {
            e.printStackTrace();
        }
//        return new ResponseEntity<Void>(headers, HttpStatus.OK);
        return findAllRE();
    }


}