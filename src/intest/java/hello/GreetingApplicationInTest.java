package hello;

import hello.model.Greeting;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class GreetingApplicationInTest {
  @Autowired
  private MockMvc mockMvc;

  private final String me = "Sen";
  private Greeting greetingMe;
  private Greeting greetingWorld;


  @Before
  public void setup() {
    greetingMe = new Greeting(me);
    greetingWorld = new Greeting("World");
  }

  @Test
  @SneakyThrows
  public void testLegalPost() {
    mockMvc.perform(post(String.format("/hello?name=%s", me)))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(jsonPath("$.name").value(greetingMe.getName()))
        .andExpect(jsonPath("$.message").value(greetingMe.getMessage()));
  }

  @Test
  @SneakyThrows
  public void testPostDefaultParameter() {
    mockMvc.perform(post("/hello"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(jsonPath("$.name").value(greetingWorld.getName()))
        .andExpect(jsonPath("$.message").value(greetingWorld.getMessage()));
  }

  @Test
  @SneakyThrows
  public void testLegalGet() {
    mockMvc.perform(get(String.format("/hello/%s", me)))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(jsonPath("$.name").value(greetingMe.getName()))
        .andExpect(jsonPath("$.message").value(greetingMe.getMessage()));
  }

  @Test
  @SneakyThrows
  public void testIllegalGet() {
    mockMvc.perform(get("/hello"))
        .andExpect(status().is4xxClientError());
  }

  @Test
  @SneakyThrows
  public void testIllegalPost() {
    mockMvc.perform(post("/hello/Sen"))
        .andExpect(status().is4xxClientError());
  }
}
