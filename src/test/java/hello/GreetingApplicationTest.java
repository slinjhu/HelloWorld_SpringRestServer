package hello;
import hello.controller.GreetingController;
import hello.model.Greeting;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class GreetingApplicationTest {

  private GreetingController controller;

  @Before
  public void setup(){
    controller = new GreetingController();
  }

  @Test
  public void unitTest() {
    final String me = "Sen";
    final Greeting greetingMe = new Greeting(me);

    assertEquals(greetingMe, controller.sayHello1(me));
    assertEquals(greetingMe, controller.sayHello2(me));
  }

}
