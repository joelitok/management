package test_package;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.Customization;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.comparator.CustomComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import management.entities.entities_ressource_concept.Order;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/* Simple checks of endpoints */

@SpringBootTest
@AutoConfigureMockMvc
public class RequestTest {

    //// SETUP section
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    Order order = new Order (
            "Martin Router King",
            "Dev",
            "Nirvana",
            "Framework Laptop",
            "Fairphone 4",
            "Litfass Platz 2, 10178 Berlin",
            "01.10.2021",
            "SN 98AS72 89SD",
            "And so Sally can wait"
    );

    private String request_body;
    private String post_respond_body;
    
    @BeforeEach
    public void setUp() throws Exception{
        request_body = objectMapper.writeValueAsString(order);

        MvcResult result = mockMvc
                .perform(post("/api/v1/order").contentType("application/json").content(request_body))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        
        post_respond_body = result.getResponse().getContentAsString();
    }

    //// TEST section
    // todo: fix security
    // test fails due to @WithMockUser annotation creating an already authenticated user
//    @Test
//    @WithMockUser(username = "evil_corp", password = "hacked_pw")
//    public void get_withWrongCredentials_shouldRespondUnauthorized() throws Exception {
//        mockMvc.perform(get("/api/v1/order"))
//                .andDo(print())
//                .andExpect(status().isUnauthorized());
//    }

    @Test
    @WithMockUser(username = "user", password = "user")
    public void get_shouldRespondSuccessful() throws Exception {
        mockMvc.perform(get("/api/v1/order"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user", password = "user")
    public void getUnknownId_shouldRespondNotFound() throws Exception {
        mockMvc.perform(get("/api/v1/order/98126487684353"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(username = "user", password = "user")
    public void getId_shouldReturnCorrectObject() throws Exception {
        JSONObject posted_obj = new JSONObject(post_respond_body);

        MvcResult get_result = mockMvc
                .perform(get("/api/v1/order/" + posted_obj.getString("id")))
                .andDo(print())
                .andReturn();

        JSONObject get_obj = new JSONObject(get_result.getResponse().getContentAsString());

        JSONAssert.assertEquals(posted_obj, get_obj, true);
    }

    @Test
    @WithMockUser(username = "user", password = "user")
    public void post_shouldCreateObject() throws Exception {
        JSONAssert.assertEquals(request_body, post_respond_body,
                new CustomComparator(JSONCompareMode.LENIENT,
                        new Customization("id", (o1, o2) -> true)));
    }

    @Test
    @WithMockUser(username = "user", password = "user")
    public void put_shouldChangeObj() throws Exception {
        JSONObject posted_obj = new JSONObject(post_respond_body);

        Order changed_order = order;
        changed_order.setEmployeeName("Spoderman");
        String put_request_body = objectMapper.writeValueAsString(changed_order);

        MvcResult put_result = mockMvc
                .perform(
                        put("/api/v1/order/" + posted_obj.getString("id"))
                                .contentType("application/json")
                                .content(put_request_body)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String new_body = put_result.getResponse().getContentAsString();

        JSONAssert.assertEquals(put_request_body,new_body,
                new CustomComparator(JSONCompareMode.LENIENT,
                        new Customization("id", (o1, o2) -> true)));
    }

    @Test
    @WithMockUser(username = "user", password = "user")
    public void delete_shouldRemoveObj() throws Exception {
        JSONObject posted_obj = new JSONObject(post_respond_body);

        mockMvc.perform(delete("/api/v1/order/" + posted_obj.getString("id")))
                .andDo(print())
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/v1/order/" + posted_obj.getString("id")))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

}
