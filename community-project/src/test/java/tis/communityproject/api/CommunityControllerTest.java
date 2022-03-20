package tis.communityproject.api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
//@WebMvcTest(CommunityController.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CommunityControllerTest {

	MockMvc mockMvc;

	@Autowired
	public void setMockMvc(MockMvc mockMvc) {
		this.mockMvc = mockMvc;
	}

	@Test
	void createPost() throws Exception {
		ResultActions result = mockMvc.perform(
			post("/community/{boardId}/post", 1)
				.accept(MediaType.APPLICATION_JSON)
				.param("title", "test title")
				.param("content", "test contents")
		);
//		result.andDo(print())
//			.andExpect(status().isOk())
//			.andExpect(handler().handlerType(CommunityController.class))
//			.andExpect(handler().methodName("post"))
//			.andExpect(jsonPath("$.success", is(true)))
//			.andExpect(jsonPath("$.response.name", is("tester00")))
//			.andExpect(jsonPath("$.response.email.address", is("test00@gmail.com")))
//			.andExpect(jsonPath("$.response.loginCount").exists())
//			.andExpect(jsonPath("$.response.loginCount").isNumber())
//			.andExpect(jsonPath("$.response.createAt").exists())
//		;
	}
//	@Test
//	void findPost() throws Exception {
//		ResultActions result = mockMvc.perform(
//			get("/community/{boardId}/post/{postId}", 1L, 3L)
//		);
//		result.andDo(print())
//			.andExpect(status().isOk())
//			.andExpect(handler().handlerType(CommunityController.class))
//			.andExpect(jsonPath("$.success", is(true)))
//			.andExpect(jsonPath("$.response.name", is("tester00")))
//			.andExpect(jsonPath("$.response.email.address", is("test00@gmail.com")))
//			.andExpect(jsonPath("$.response.loginCount").exists())
//			.andExpect(jsonPath("$.response.loginCount").isNumber())
//			.andExpect(jsonPath("$.response.createAt").exists())
//		;
//	}
}
