package oz.web.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import oz.web.dao.IPlayerDao;
import oz.web.pojo.Player;

@Controller
@RequestMapping("/test")
public class TestController {
	
	@Resource
	private IPlayerDao playerDao;
	
	@RequestMapping(value="/a" )
	public String methodA(Player p,Player a){
		System.out.println("TestController.methodA()");
		System.out.println("name="+p.getName());
		System.out.println("a = "+a.getName());
		playerDao.add(p);
		return "success";
	}
//	@RequestMapping(value="/b")
//	public String methodB(){
//		return "view";
//	}

}
