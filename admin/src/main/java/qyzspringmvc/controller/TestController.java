package qyzspringmvc.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by 17020406 on 2017/3/22.
 */
@Controller
@RequestMapping(value = "/Test/*") //访问的url地址前缀，可以不写，写了就必须在方法url前面先加上class url 进行区分控制器
public class TestController
{
	//访问地址：http://localhost:8080/Test/returnSuccess
	@RequestMapping(value = "qyz")    //实际访问的url地址qyz
	public String returnSuccess() {
		return "/views/success";    //返回Views文件夹下的success.jsp页面
	}

	//访问地址：http://localhost:8080/Test/returnString
	@RequestMapping(value = "returnString", produces = {"text/plain;charset=UTF-8"})
	//produces用于解决返回中文乱码问题，application/json;为json解决中文乱码
	@ResponseBody       //用于返回字符串,不写即返回视图
	public String returnString() {
		return "hello return string 这是中文，并没有乱码";
	}
}
