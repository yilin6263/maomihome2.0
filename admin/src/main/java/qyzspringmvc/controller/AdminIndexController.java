package qyzspringmvc.controller;

import model.MenuTreeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.servlet.ModelAndView;
import qyzspringmvc.service.AdminMenuService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * 首页控制器
 *
 * @author lulei
 *
 */
@Controller
public class AdminIndexController {

	@Autowired
	protected AdminMenuService adminMenuService;




	@RequestMapping("/login")
	public String login() {

		return "login";
	}

	/**
	 * 功能描述: 查询所有菜单信息<br>
	 * 1、参数为树的根节点数量,现在只是虚构的数据,实际应该是从数据库中抽取出来的
	 * 2、取当前操作人工号
	 *
	 *asd
	 * @param request
	 * @return
	 * @see [相关类/方法](可选)
	 * @since [产品/模块版本](可选)
	 */
	@RequestMapping("/index")
	public ModelAndView index(HttpServletRequest request) {
		List<MenuTreeModel> menuTreeList = adminMenuService.findAllParentMenu();
		request.setAttribute("menuList", menuTreeList);


		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");
		return mav;
	}

	/**
	 * 功能描述: 查询父菜单下的子菜单<br>
	 * 参数为树的根节点数量,现在只是虚构的数据,实际应该是从数据库中抽取出来的<br>
	 *
	 *
	 * @param request
	 * @param parentId
	 * @return
	 * @see [相关类/方法](可选)
	 * @since [产品/模块版本](可选)
	 */
	@RequestMapping("/left_{parentId}")
	public String left(HttpServletRequest request, @PathVariable String parentId) {
		List<MenuTreeModel> menuTreeList = adminMenuService.findMenuByParent(parentId);
		request.setAttribute("menuList", menuTreeList);
		return "main-left";
	}

	/**
	 * 功能描述:查询一级菜单<br>
	 * 参数为树的根节点数量,现在只是虚构的数据,实际应该是从数据库中抽取出来的
	 *
	 *
	 * @param parentId
	 * @return
	 * @see [相关类/方法](可选)
	 * @since [产品/模块版本](可选)
	 */
	@ResponseBody
	@RequestMapping("getMenu/{parentId}")
	public List<MenuTreeModel> getMenu(@PathVariable String parentId) {
		List<MenuTreeModel> menuTreeList = adminMenuService.findMenuByParent(parentId);
		return menuTreeList;
	}

	@RequestMapping("/denyAccess")
	public String denyAccess() {

		return "denyAccess";
	}

}
