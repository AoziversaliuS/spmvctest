package oz.web.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
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
	
	
   @RequestMapping("/interceptor")	
   public String interceptor(){
	   return "view";
   }
   
   
	//测试bean类的注入
	@Resource//要在bean类中注解上 @Component
	private Player player;
   @RequestMapping("/annotationA")
   public String annotationTest(){
	   System.out.println("TestController.annotationTest()");
	   System.out.println("Player = " + this.player);
	   return "view";
   }
   
   @RequestMapping("/annotationB")
   public String annotationTest2(Player player){
	   System.out.println("TestController.annotationTest2()");
	   System.out.println("Player = " +player);//有值，且是多例的
	   return "view";
   }
   
   @RequestMapping("/annotationC")
   public String annotationTest3(@RequestParam(required=false) Player player){
	   System.out.println("TestController.annotationTest3()");
	   System.out.println("Player = " +player);//空值！！
	   return "view";
   }
   
   //重定向测试：从redirect1重定向到redirect2,开头加“/”则表示从应用根目录开始
   @RequestMapping("/redirect1.do")
   public String redirectA(){
	   System.out.println("TestController.redirectA()");
	   return "redirect:/test/redirect2.do";
   }
   
   @RequestMapping("/redirect2.do")
   public String redirectB(){
	   System.out.println("TestController.redirectB()");
	   return "view";
   }
   
   //请求转发测试：从forward1转发请求到forward2，请求转发url是不会变的
   @RequestMapping("forward1.do")
	public String forwardA(){
	   System.out.println("TestController.forwardA()");
	   return "forward:forward2.do";
   }
   @RequestMapping("forward2.do")
   public String forwardB(){
	   System.out.println("TestController.forwardB()");
	   return "view";
   }
	
	
	
	
	
	

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
	

}
