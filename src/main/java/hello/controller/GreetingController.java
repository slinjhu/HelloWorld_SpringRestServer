package hello.controller;

import hello.model.Greeting;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class GreetingController {
  /**
   * Pass information by request parameters
   * @param name Name of the person to be greeted.
   * @return An object of type Greeting
   */
  @PostMapping("/hello")
  public Greeting sayHello1(@RequestParam(value = "name", defaultValue = "World") String name){
    return new Greeting(name);
  }

  /**
   * Pass information by path variables
   * @param name Name of the person to be greeted.
   * @return An object of type Greeting
   */
  @GetMapping("/hello/{name}")
  public Greeting sayHello2(@PathVariable("name") String name){
    return new Greeting(name);
  }
}
