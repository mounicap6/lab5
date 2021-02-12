package addressbook;

import addressbook.repository.AddressBookRepository;
import addressbook.repository.BuddyInfoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.junit.Assert.*;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class ApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    BuddyInfoRepository buddyRepo;

    @Autowired
    AddressBookRepository addressBookRepository;

    @Test
    public void addressBookIsEmpty() throws Exception {
        String url = "/addressbook";
        this.mockMvc.perform(get(url))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/hal+json"))
                .andExpect(jsonPath("_embedded.addressbook", hasSize(0)));
    }

    @Test
    public void createAddressBook() throws Exception {
        String url = "/addressbook";
        this.mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andDo(print())
                .andExpect(status().is(201));

        assertNotEquals(this.addressBookRepository.count(), 0);
    }

    @Test
    public void createBuddy() throws Exception {
        String url = "/buddy";
        MvcResult result = this.mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON).content("{\"name\": \"Mounica\",\n" +
                        "\t\"phoneNumber\": \"211-311-4111\"}"))
                .andDo(print())
                .andExpect(status().is(201))
                .andReturn();

        assertNotNull(this.buddyRepo.findByName("Mounica"));
    }

}
