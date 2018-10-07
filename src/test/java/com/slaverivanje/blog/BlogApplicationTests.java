package com.slaverivanje.blog;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.ArrayList;
import java.util.Date;


import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@WebMvcTest(ArticleController.class)
public class BlogApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ArticleService articleService;

	@Test
	public void givenArticle_whenGetArticles_thenReturnJsonArticle() throws Exception {

		Article a1 = new Article("1","Kosarka","www.kosarka.rs","Kosarkaske zanimljivosti","Zvezda igra Euro Cup", new Date());

		ArrayList<Article> articles = new ArrayList<>();

		articles.add(a1);

		given(articleService.findArticle("1")).willReturn(a1);

		mockMvc.perform(get("/article/1")
			.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.title", is(a1.getTitle())))
                .andExpect(jsonPath("$.id", is(a1.getId())));
	}

	@Test
	public void givenArticle_whenPutArticle_thenReturnThatArticle() throws Exception {
        //Article a1 = new Article("1", "Kosarka", "www.kosarka.rs", "Kosarkaske zanimljivosti", "Zvezda igra EuroCup", new Date());

        long id = 1;

        MockHttpServletRequestBuilder builder =
                put("/article/" + id)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_UTF8)
                        .characterEncoding("UTF-8")
                        .content(getArticleInJson(1));

        mockMvc.perform(builder)
                .andExpect(status().isOk())
                .andExpect(content().string(""))
                .andDo(print());

        }
        private String getArticleInJson (long id){
            return "{\"id\":\"" + id + "\", \"title\":\"Kosarka\", \"link\":\"www.kosarka.rs\",\"summary\":\"Kosarkaske zanimljivosti\", " +
                    "\"body\":\"Zvezda igra Euro Cup\", \"title\":\"Kosarka\", \"created date\": \""+ new Date()+"\"}";
        }



    @Test
    public void givenArticle_whenGetArticle_thenReturnJsonArrayArticle() throws Exception {
        Article a1 = new Article("1","Kosarka","www.kosarka.rs","Kosarkaske zanimljivosti","Zvezda igra Euro Cup", new Date());
        Article a2 = new Article("2","Fudbal","www.fudbal.rs","Fudbalske zanimljivosti","Zvezda igra Ligu sampiona", new Date());
        Article a3 = new Article("3","Tenis","www.tenis.rs","Teniske zanimljivosti","Novak Djokovic je svetski mega car!", new Date());

        ArrayList<Article> articles = new ArrayList<>();

        articles.add(a1);
        articles.add(a2);
        articles.add(a3);

        given(articleService.findAll()).willReturn(articles);

        mockMvc.perform(get("/article")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$.[0].id", is(articles.get(0).getId())))
                .andExpect(jsonPath("$.[1].title", is(articles.get(1).getTitle())))
                .andExpect(jsonPath("$.[2].link", is(articles.get(2).getLink())));
    }




}
