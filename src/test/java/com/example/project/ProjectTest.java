package com.example.project;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.project.model.Friend;
import com.example.project.model.User;
import com.example.project.model.UserDTO;
import com.example.project.repository.UserDTORepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)	
@Transactional
public class ProjectTest {
	
	@Autowired
	private UserDTORepository udr;
	
	private MockMvc mockMvc; 
	@Autowired
	  WebApplicationContext context;
	 @Before
	  public void setup() throws Exception {
	    mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	  }
	 @Test
	 public void addUserTest_ok() throws Exception{
		User u=new User();
		u.userId=1;
		u.userName="jim";
		u.emailId="jim.split.com";
		u.totalBalance=0.0;
		u.balanceStatus="";
		byte[] iJson = toJson(u);
		mockMvc.perform(post("/user")
				.content(iJson)
	 			.contentType(MediaType.APPLICATION_JSON)
	 			.accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());	 
	 }
	 @Test
	 public void getUserTest_ok() throws Exception{
		User u=new User();
		u.userId=1;
		u.userName="jim";
		u.emailId="jim@split.com";
		u.totalBalance=0.0;
		u.balanceStatus="";
		byte[] iJson = toJson(u);
		mockMvc.perform(post("/user")
				.content(iJson)
	 			.contentType(MediaType.APPLICATION_JSON)
	 			.accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());	
		 mockMvc.perform(get("/user/1" )).andDo(print())
		 .andExpect(status().isOk())
         .andExpect(MockMvcResultMatchers.jsonPath("$.userId").value(1))
         .andExpect(MockMvcResultMatchers.jsonPath("$.userName").value("jim"))
         .andExpect(MockMvcResultMatchers.jsonPath("$.emailId").value("jim@split.com"))
         .andExpect(MockMvcResultMatchers.jsonPath("$.totalBalance").value(0.0))
         .andExpect(MockMvcResultMatchers.jsonPath("$.balanceStatus").value(""));
		UserDTO udt=udr.getOne(1);
		List<Friend> f=udt.getFriends();
		int i=f.size();
		assertEquals(i,0);
		}
	 
	 @Test
	 public void deleteUserTest_ok() throws Exception{
		 User u=new User();
			u.userId=1;
			u.userName="jim";
			u.emailId="jim@split.com";
			u.totalBalance=0.0;
			u.balanceStatus="";
			byte[] iJson = toJson(u);
			mockMvc.perform(post("/user")
					.content(iJson)
		 			.contentType(MediaType.APPLICATION_JSON)
		 			.accept(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk());	
			mockMvc.perform(delete("/user/1")
					.content(iJson)
		 			.contentType(MediaType.APPLICATION_JSON)
		 			.accept(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk());
			
	 }
	 @Test
	 public void getFriendTest_ok() throws Exception{
		 User u=new User();
			u.userId=1;
			u.userName="jim";
			u.emailId="jim@split.com";
			u.totalBalance=0.0;
			u.balanceStatus="";
			byte[] iJson = toJson(u);
			mockMvc.perform(post("/user")
					.content(iJson)
		 			.contentType(MediaType.APPLICATION_JSON)
		 			.accept(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk());	
			Friend f= new Friend();
			f.setFriendId(1);
			f.setFriendName("scott");
			f.setFriendMail("scott@split.com");
			f.setIndividualAmount(0.0);
			f.setBalanceStatus("");
			byte[] iJson1 = toJson(f);
			mockMvc.perform(post("/friend")
					.content(iJson1)
		 			.contentType(MediaType.APPLICATION_JSON)
		 			.accept(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk());	
			 mockMvc.perform(get("/friend/1" )).andDo(print())
			 .andExpect(status().isOk())
	         .andExpect(MockMvcResultMatchers.jsonPath("$.friendId").value(1))
	         .andExpect(MockMvcResultMatchers.jsonPath("$.friendName").value("scott"))
	         .andExpect(MockMvcResultMatchers.jsonPath("$.friendMail").value("scott@split.com"))
	         .andExpect(MockMvcResultMatchers.jsonPath("$.individualAmount").value(0.0))
	         .andExpect(MockMvcResultMatchers.jsonPath("$.balanceStatus").value(""));
			UserDTO udt=udr.getOne(1);
			List<Friend> f1=udt.getFriends();
			int i=f1.size();
			assertEquals(i,1);
	 }
	 @Test
	 public void addFriendTest() throws Exception{
		 User u=new User();
			u.userId=1;
			u.userName="jim";
			u.emailId="jim@split.com";
			u.totalBalance=0.0;
			u.balanceStatus="";
			byte[] iJson = toJson(u);
			mockMvc.perform(post("/user")
					.content(iJson)
		 			.contentType(MediaType.APPLICATION_JSON)
		 			.accept(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk());
			Friend f= new Friend();
			f.setFriendId(1);
			f.setFriendName("scott");
			f.setFriendMail("scott@split.com");
			f.setIndividualAmount(0.0);
			f.setBalanceStatus("");
			byte[] iJson1 = toJson(f);
			mockMvc.perform(post("/friend")
					.content(iJson1)
		 			.contentType(MediaType.APPLICATION_JSON)
		 			.accept(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk());	
	 }
	 @Test
	 public void deleteFriendTest() throws Exception{
		 User u=new User();
			u.userId=1;
			u.userName="jim";
			u.emailId="jim@split.com";
			u.totalBalance=0.0;
			u.balanceStatus="";
			byte[] iJson = toJson(u);
			mockMvc.perform(post("/user")
					.content(iJson)
		 			.contentType(MediaType.APPLICATION_JSON)
		 			.accept(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk());
			Friend f= new Friend();
			f.setFriendId(1);
			f.setFriendName("scott");
			f.setFriendMail("scott@split.com");
			f.setIndividualAmount(0.0);
			f.setBalanceStatus("");
			byte[] iJson1 = toJson(f);
			mockMvc.perform(post("/friend")
					.content(iJson1)
		 			.contentType(MediaType.APPLICATION_JSON)
		 			.accept(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk());	
			mockMvc.perform(delete("/friend/1")
					.content(iJson)
		 			.contentType(MediaType.APPLICATION_JSON)
		 			.accept(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk());
			UserDTO udt=udr.getOne(1);
			List<Friend> f1=udt.getFriends();
			int i=f1.size();
			assertEquals(i,0);
	 }
	 @Test
	 public void updateUserTest() throws Exception{
		 User u=new User();
			u.userId=1;
			u.userName="jim";
			u.emailId="jim@split.com";
			u.totalBalance=0.0;
			u.balanceStatus="";
			byte[] iJson = toJson(u);
			mockMvc.perform(post("/user")
					.content(iJson)
		 			.contentType(MediaType.APPLICATION_JSON)
		 			.accept(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk());
			Friend f= new Friend();
			f.setFriendId(1);
			f.setFriendName("scott");
			f.setFriendMail("scott@split.com");
			f.setIndividualAmount(0.0);
			f.setBalanceStatus("");
			byte[] iJson1 = toJson(f);
			mockMvc.perform(post("/friend")
					.content(iJson1)
		 			.contentType(MediaType.APPLICATION_JSON)
		 			.accept(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk());	
	 }
	 
	 private byte[] toJson(Object r) throws Exception {
	        ObjectMapper map = new ObjectMapper();
	        return map.writeValueAsString(r).getBytes();
	        }
	
}
