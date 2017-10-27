package spring.test.sbTest;

import java.awt.PageAttributes.MediaType;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SbTestApplicationTests {

	@Autowired
    private MockMvc mvc;
 
    @Test
    public void getIndex() throws Exception {
      mvc.perform(MockMvcRequestBuilders.get("/")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().string(equalTo("Index Page")));
    }
 
    @Test
    public void getLocal() throws Exception {
      mvc.perform(MockMvcRequestBuilders.get("/local")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().string(equalTo("/local")));
    }

}
