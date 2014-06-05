package oz.web.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletConfigAware;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import oz.web.dao.IPlayerDao;
import oz.web.pojo.Player;

@Controller
@RequestMapping("/test")
public class TestController implements ServletContextAware{
	
	@Resource
	private IPlayerDao playerDao;
	private ServletContext servletContext;
//	@RequestMapping(value="/a")
//	public String methodA(Player p,Player a){
//		System.out.println("TestController.methodA()");
//		System.out.println("p.name="+p.getName());
//		System.out.println("a.name = "+a.getName());
//		playerDao.add(p);
//		return "success";
//	}
//	@RequestParam("name")
	@RequestMapping(value="/b")
	public String methodB(){
		return "view";
	}
	
	@RequestMapping("/c")
	public String methodC(Player p){
		
		System.out.println("name = "+p.getName());
		System.out.println("passWord = "+p.getPassWord());
		
		return "registed";
//		return "/WEB-INF/view/registed";
	}
	
	
	@RequestMapping("/upload")
	public String upload(@RequestParam("file") MultipartFile file){
		System.out.println("TestController.upload()");
		if(!file.isEmpty()){
			String path = servletContext.getRealPath("/uploadfile/");
			System.out.println("path = "+path);
			File newFile = new File(path	, file.getOriginalFilename());
			
			try {
				file.transferTo(newFile);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return "view";
		
	}
	
	@RequestMapping("/ajax")
	public   @ResponseBody  List<Player> ajax(Player player){
		System.out.println("TestController.ajax()");
		System.out.println(" name="+player.getName()+"   passWord="+player.getPassWord());
		playerDao.add(player);
		List<Player> players = playerDao.getPlayers();
		return players;
	}
	
	
	
	
	
	
	
	

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
	

}
