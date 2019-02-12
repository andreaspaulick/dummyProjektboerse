package com.andreas.dummyProjektboerse.Controller;

import com.andreas.dummyProjektboerse.Entity.PostToWp;
import com.andreas.dummyProjektboerse.Entity.Posts;
import com.andreas.dummyProjektboerse.Repository.PostRepository;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

@Controller
public class HtmlController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/")
    public String postsForm(Model model) {
        model.addAttribute("addPost", new Posts());
        model.addAttribute("posts", postRepository.findAll());
        model.addAttribute("sendToWp", new PostToWp(1L,"send"));
        return "posts";
    }

    @PostMapping("/")
    public String postsSubmit(@ModelAttribute("addPost") Posts posts, @ModelAttribute("sendToWp") PostToWp sendToWp) throws IOException, AuthenticationException {
        if(sendToWp.getName() != null) {

            System.out.println("Send this data to WordPress: YES");

            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost("http://localhost/wp/wordpress/wp-json/wp/v2/posts");
            //String json = "{\"title\":\""+posts.getTitle()+"\",\"content\":\""+posts.getContent()+"\",\"status\":\"publish\",\"course\":\""+posts.getCourse()+"\",\"start\":\""+posts.getStart()+"\",\"end\":\""+posts.getEnd()+"\",\"max_party\":\""+posts.getMax_party()+"\",\"tags\":\""+posts.getCourse()+"\"}";
            String json = "{\"title\":\""+posts.getTitle()+"\",\"content\":\""+posts.getContent()+"\",\"status\":\"publish\"}";
            httpPost.setEntity(new StringEntity(json));
            UsernamePasswordCredentials creds = new UsernamePasswordCredentials("restauthor", "^ZhgK3E%Hm5Sm5#vTHy6X3)X");
            httpPost.setHeader("Content-type", "application/json");
            httpPost.addHeader(new BasicScheme().authenticate(creds, httpPost, null));
            //httpPost.setHeader("Authorization","Bearer eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJDQS00eXBfTnU5d3cxZEZmdUd3UzB5LUtCRDF6LVVjMjlxZzZaaTJRUjNZIn0.eyJqdGkiOiI5NDY0Mjk4MC05NDViLTQwMDgtOTgyZi1hNjViZjcyOGUxODUiLCJleHAiOjE1NDkzOTAyMTgsIm5iZiI6MCwiaWF0IjoxNTQ5Mzg5OTE4LCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgxODAvYXV0aC9yZWFsbXMvcGJvZXJzZSIsInN1YiI6IjgzYTFjNmVhLTJhMzktNGU5ZC05N2NiLWI1NTEzMzkyODk0MCIsInR5cCI6IkJlYXJlciIsImF6cCI6InBiY2xpZW50IiwiYXV0aF90aW1lIjowLCJzZXNzaW9uX3N0YXRlIjoiZDI0NWU1ZDgtM2MyZi00NzRkLWJmNjMtZDM1ODE3YTUzMDk3IiwiYWNyIjoiMSIsImFsbG93ZWQtb3JpZ2lucyI6WyJodHRwOi8vbG9jYWxob3N0OjgwNDUiXSwicmVhbG1fYWNjZXNzIjp7InJvbGVzIjpbInVzZXIiXX0sInNjb3BlIjoicHJvZmlsZSBlbWFpbCIsImVtYWlsX3ZlcmlmaWVkIjpmYWxzZSwibmFtZSI6IkFuZHJlYXMgUGF1bGljayIsInByZWZlcnJlZF91c2VybmFtZSI6InBidXNlciIsImdpdmVuX25hbWUiOiJBbmRyZWFzIiwiZmFtaWx5X25hbWUiOiJQYXVsaWNrIiwiZW1haWwiOiJwYnVzZXJAcGJ1c2VyLmNvbSJ9.qzLrw9ISk4qCUm4PVkK0t_MpGuq4eb06TKtxXNPjMPkSZfr3iEFnCFEFSay7gzKWqRwqXXQnXEPg4LRKUlTkuLuCVM5iDYH2eLckOo2QFtx-TrfM3zi8AdobwLiHq7tlMynnxIGXzONZRxjBZ1FDFvVFN9yea_9vw452nkz9bh_NqHBfMpWxKRDyh4q20SW_uZihth_D4YgO3wPvPgKDOX_2sCxN0HAz5Mphnwmmh4Iv9cJQnYxOhOhALvrCjukuC0pRnDSxODpWrdfE67V1E_PZ3Jv1H6_RI8ITrDO8gjwAW_68DErOrcfZohaG8PeXFLTxIuDltwravIzvFaz7hg");

            CloseableHttpResponse response = client.execute(httpPost);
            client.close();
    }
        else {
            System.out.println("Send this data to WordPress: NO");
        }

        posts.setStatus("publish");
        postRepository.save(posts);
        return "redirect:";
    }
}
