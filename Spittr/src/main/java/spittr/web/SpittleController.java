package spittr.web;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import spittr.domain.Spittle;
import spittr.repository.SpittleRepository;


@Controller
@RequestMapping("/spittles")  //放在类上面的RequesMapping做为网址的前缀
public class SpittleController {
	private SpittleRepository spittleRepository;
	private static final String MAX_LONG_AS_STRING="9223372036854775807";
	public SpittleRepository getSpittleRepositotry() {
		return spittleRepository;
	}
	@Autowired
	public SpittleController(SpittleRepository spittleRepositotry) {
		super();
		this.spittleRepository = spittleRepositotry;
	}

	/*此处用到了大量的约定
	 * 如果一个方法返回集合，这个集合会被自动放入Model，放入model内容的key通过推断得出，一般是是集合元素类型+集合类型
	 * 该方法的调用URL是spittles， 则视图名就是spittles.jsp*/
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Spittle> spittles(
			//用请求参数请求资源
		//	@RequestParam(value="max", defaultValue=MAX_LONG_AS_STRING)long max, 
			@RequestParam(value="count", defaultValue="20")int count) { //spittleList
		return spittleRepository.findRecent(count);
	}
	
	//用路径变量请求资源
	@RequestMapping(value="/{spittleId}")
	public String spittle(
			//@PathVariable是路径变量，不再像请求参数那样？count=了，而是使用/
			@PathVariable("spittleId")long spittleId, Model model) {  
		model.addAttribute(spittleRepository.findOne(spittleId));
		return "spittle";
	}
	@RequestMapping(method=RequestMethod.POST)
	  public String saveSpittle(SpittleForm form, Model model) {
	    try {
	      spittleRepository.save(
	    		  new Spittle(null, form.getMessage(), new Date(), 
	          form.getLongitude(), form.getLatitude(),form.getSpitter()));
	      return "redirect:/spittles";
	    } catch (DuplicateSpittleException e) {
	      return "error/duplicate";
	    }
	  }
}
