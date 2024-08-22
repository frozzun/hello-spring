package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class hellocontroller {

  @GetMapping("hello")
  public String hello(Model model) {
    model.addAttribute("data", "hello!!");
    //templates 에 있는 hello.html 로 가라
    return "hello";
  }

  @GetMapping("hello-mvc")
  public String helloMvc(@RequestParam("name") String name, Model model) {
    model.addAttribute("name", name);
    return "hello-template";
  }
}
