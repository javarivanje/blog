package com.slaverivanje.blog;


import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

@RunWith(SpringRunner.class)
@WebMvcTest(CommentsController.class)
public class CommentsControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommentsService commService;

    @Test
    public void givenComment_whenGetComment_thenReturnJsonComment() throws Exception {
        Comments c1= new Comments("1","1","Ovo je test metoda GET-1","Milos");
        Comments c2= new Comments("2","1","Ovo je test metoda GET-2","Slavisa");
        Comments c3= new Comments("1","1","Ovo je test metoda GET-3","Milos");
        ArrayList<Comments> allComments = new ArrayList<>();
        allComments.add(c1);
        allComments.add(c2);
        allComments.add(c3);

        given(commService.findAllComments("1")).willReturn(allComments);

        mockMvc.perform(get("/article/1/comments")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].author", is(c1.getAuthor())))
                .andExpect(jsonPath("$.[1].author", is(c2.getAuthor())))
                .andExpect(jsonPath("$.[2].author", is(c3.getAuthor())));
    }

    @Test
    public void givenComment_whenPutComment_thenReturnThatComment() throws Exception {
        Comments c1= new Comments("1","1","Ovo je test metoda GET-1","Milos");

        long id = 1;

        given(commService.saveComment(any(Comments.class))).willReturn(c1);


        MockHttpServletRequestBuilder builder =
                put("/article/" + id + "/comments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON_UTF8)
                        .characterEncoding("UTF-8")
                        .content(getCommentInJson(1));

        mockMvc.perform(builder)
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.author", is("Milos")));
    }

    private String getCommentInJson (long id){
        return "{\"id\":\"" + id + "\", \"articleId\":\"1\", \"text\":\"Ovo je test metoda GET-1\",\"author\":\"Milos\"}";
    }

    @Test
    public void givenAuthor_whenGetComment_thenReturnJsonComment() throws Exception {
        Comments c1= new Comments("1","1","Ovo je test metoda GET-1","Milos");
        //Comments c2= new Comments("2","1","Ovo je test metoda GET-2","Slavisa");
        Comments c3= new Comments("1","1","Ovo je test metoda GET-3","Milos");
        ArrayList<Comments> allComments = new ArrayList<>();
        allComments.add(c1);
        //allComments.add(c2);
        allComments.add(c3);

        given(commService.findAllCommentsByAuthor("Milos")).willReturn(allComments);

        mockMvc.perform(get("/article/1/comments/Milos")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].author", is(c1.getAuthor())))
                .andExpect(jsonPath("$.[0].articleId", is(c1.getArticleId())))
                .andExpect(jsonPath("$.[1].author", is(c3.getAuthor())))
                .andExpect(jsonPath("$.[1].articleId", is(c3.getArticleId())));
    }


}
