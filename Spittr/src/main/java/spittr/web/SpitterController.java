package spittr.web;


import java.io.IOException;

import javax.servlet.http.Part;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spittr.domain.Spitter;
import spittr.repository.SpitterRepository;

@Controller
@RequestMapping("/spitter")
public class SpitterController {
	
	private SpitterRepository spitterRepository;

	public SpitterRepository getSpittleRepositotry() {
		return spitterRepository;
	}
	@Autowired
	public SpitterController(SpitterRepository spitterRepository) {
		super();
		this.spitterRepository = spitterRepository;
	}

	//类上与方法上都有@RequestMapping时，网址路径要将两个括号中的属性结合
	@RequestMapping(value="/register",method=RequestMethod.GET)
	//method=RequestMethod.GET指该请求方法只能接受Get请求，如果不写默认都可以接受
	public String showRegistrationFrom(Model model){
		//跳转到View前，必须在Model中放置一个和CommandName相同的类型
		model.addAttribute("spitter",new Spitter());//spitter可以通过类型推断的出，所以可以省略
		return "registerForm";
	}
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public String processRegistration(
		/*//@RequestPart("profilePicture")Part profilePicture, //文件上传
*/		@Valid Spitter spitter,Errors errors){//使用jsr303校验,一旦发生验证错误就会保存到Errors
	
		if(errors.hasErrors()){
			return "registerForm";
		}
		
		spitterRepository.save(spitter);
		//做文件上传
//		try {
//			profilePicture.write(profilePicture.getSubmittedFileName());
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		return "redirect:/spitter/"+spitter.getUsername();//转发到spitter
		
	}
	//路径变量
	@RequestMapping(value="/{username}", method=RequestMethod.GET)	
	public String showSpitterProfile(@PathVariable String username,//路径参数
			Model model){
		Spitter spitter=spitterRepository.findByUsername(username);
		model.addAttribute(spitter);
		return "profile";
		
	}
}
