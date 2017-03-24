package hello.model;

import lombok.Data;


@Data
public class Greeting {
  private String name;
  private String message;

  public Greeting(String name){
    this.name = name;
    message = "Hello to " + this.name;
  }
}
