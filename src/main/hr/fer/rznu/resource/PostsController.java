package hr.fer.rznu.resource;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hr.fer.rznu.jdbc.templates.PostsJDBCTemplate;
import hr.fer.rznu.jdbc.templates.postclasses.Post;

@Controller
public class PostsController {

	@Autowired
	private ApplicationContext appContext;

	@RequestMapping(value = "/posts", method = RequestMethod.GET)
	public String getPosts(ModelMap model) {

		PostsJDBCTemplate posts = (PostsJDBCTemplate) appContext.getBean("postsJDBCTemplate");

		List<Post> postList = posts.listposts();

		model.addAttribute("posts", postList);

		return "posts";
	}

	@RequestMapping(value = "/users/{username}/posts", method = RequestMethod.GET)
	public String getUserPosts(@PathVariable final String username, ModelMap model) {

		PostsJDBCTemplate posts = (PostsJDBCTemplate) appContext.getBean("postsJDBCTemplate");
		
		List<Post> postList = posts.listposts(username);

		model.addAttribute("posts", postList);

		return "posts";
	}
	
	@RequestMapping(value = "/**/posts/{postId}", method = RequestMethod.DELETE)
	public String deletePost(@PathVariable final int postId, HttpServletRequest request, HttpServletResponse response){
//		PostsJDBCTemplate posts = (PostsJDBCTemplate) appContext.getBean("postsJDBCTemplate");
//		posts.delete(postId);
		String mapping = request.getServletPath();
		System.out.println(mapping);
		System.out.println(mapping.substring(0, mapping.lastIndexOf('/')));
		return "redirect:/users";
	}
	
	

}
