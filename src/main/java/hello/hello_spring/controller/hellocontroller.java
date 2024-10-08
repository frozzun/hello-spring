package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class hellocontroller {

  @GetMapping("hello")
  public String hello(Model model) {
    model.addAttribute("data", "hello!!");
    //templates 에 있는 hello.html 로 가라
    return "hello";
  }

  /**
   * MVC
   * */
  @GetMapping("hello-mvc")
  public String helloMvc(@RequestParam(value = "name", required = false) String name, Model model) {
    model.addAttribute("name", name);
    return "hello-template";
  }

  /**
   * API
   * */
  @GetMapping("hello-string")
  @ResponseBody
  public String helloString(@RequestParam("name") String name) {
    return "hello " + name;
  }

  /**
   * API
   * */
  @GetMapping("hello-api")
  @ResponseBody
  public Hello helloApi(@RequestParam("name") String name) {
    Hello hello = new Hello();
    hello.setName(name);
    return hello;
  }

  static class Hello {
    private String name;
    public String getName() {
      return name;
    }
    public void setName(String name) {
      this.name = name;
    }
  }
}
