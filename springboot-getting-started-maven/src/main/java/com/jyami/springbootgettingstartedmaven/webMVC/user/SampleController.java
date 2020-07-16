package com.jyami.springbootgettingstartedmaven.webMVC.user;

import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Created by jyami on 2020/07/16
 */
@RequestMapping("sample")
@Controller
public class SampleController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("name", "jyami");
        return "hello";
    }

    @GetMapping("error")
    public String error() {
        throw new SampleException();
    }

    @GetMapping("selfLink")
    public @ResponseBody EntityModel<Sample> selfLink() {
        Sample sample = new Sample();
        sample.setPrefix("Hey,");
        sample.setName("jyami");

        EntityModel<Sample> sampleResource = new EntityModel<>(sample);
        sampleResource.add(linkTo(methodOn(SampleController.class).selfLink()).withSelfRel());

        return sampleResource;
    }

    @ExceptionHandler(SampleException.class)
    public @ResponseBody
    AppError sampleError(SampleException e) {
        AppError appError = new AppError();
        appError.setMessage("error.app.key");
        appError.setMessage("IDK IDK IDK");
        return appError;
    }

}
