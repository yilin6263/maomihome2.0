package qyzspringmvc.service;




import model.MenuTreeModel;
import model.SimpleJsonTree;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;



/**
 * Created by 17020406 on 2017/3/27.
 */
@Service
public class AdminMenuService {


	private static final Logger LOGGER = LoggerFactory.getLogger(AdminMenuService.class);

	public List<MenuTreeModel> findAllMenu() {
		LOGGER.info("[findAllJsonMenu]-->started");
		// 参数为树的根节点数量,现在只是虚构的数据,实际应该是从数据库中抽取出来的
		List<MenuTreeModel> list = this.getTreeNodeData(true, null);
		LOGGER.info("[findAllJsonMenu]-->result.size=" + list.size());
		return list;
	}

	public List<MenuTreeModel> findAllParentMenu() {
		LOGGER.info("[findAllJsonMenu]-->started");
		// 参数为树的根节点数量,现在只是虚构的数据,实际应该是从数据库中抽取出来的
		List<MenuTreeModel> list = this.getTreeNodeData(false, "0");
		LOGGER.info("[findAllJsonMenu]-->result.size=" + list.size());
		return list;
	}

	public List<MenuTreeModel> findMenuByParent(String pid) {
		LOGGER.info("[findAllJsonMenu]-->started");
		// 参数为树的根节点数量,现在只是虚构的数据,实际应该是从数据库中抽取出来的
		List<MenuTreeModel> list = this.getTreeNodeData(false, pid);
		for (MenuTreeModel smt : list) {
			String pId = String.valueOf(smt.getId());
			List<MenuTreeModel> subList = getTreeNodeData(false, pId);
			smt.setSubMenuTrees(subList);
			if (subList != null) {
				findMenuByParent(pId);
			}
		}
		LOGGER.info("[findAllJsonMenu]-->result.size=" + list.size());
		return list;
	}

	public List<SimpleJsonTree> changeToJsonTree(List<MenuTreeModel> sysList) {
		List<SimpleJsonTree> jsonList = new ArrayList<SimpleJsonTree>();
		for (MenuTreeModel menu : sysList) {
			SimpleJsonTree jt = new SimpleJsonTree();
			jt.setId("" + menu.getId());
			jt.setName(menu.getName());
			jt.setpId("" + menu.getPid());
			if (null != menu.getUrl()) {
				jt.setUrl("" + menu.getUrl());
			} else {
				jt.setUrl("");
			}
			jt.setTarget("mainFrame");
			if (menu.getId() == 0) {
				jt.setOpen(true);
			}
			jsonList.add(jt);
		}
		return jsonList;
	}

	public List<MenuTreeModel> getTreeNodeData(boolean isAll, String parentId) {
		List<MenuTreeModel> treeDataLst = new ArrayList<MenuTreeModel>();
		InputStream input = AdminMenuService.class.getClassLoader().getResourceAsStream("conf/tree-data.properties");
		Properties properties = new Properties();

		try {
			properties.load(input);
		} catch (IOException e) {
			LOGGER.error("getTreeNodeData,error",e);
		}

		Set<MenuTreeModel> treeSet = new TreeSet<MenuTreeModel>();
		for (Object value : properties.values()) {
			String valStr = (String) value;
			String[] treeDatas = valStr.split(",");
			if (!isAll && !parentId.equals(treeDatas[1])) {
				continue;
			}
			MenuTreeModel mt = new MenuTreeModel();
			mt.setId(Integer.valueOf(treeDatas[0]));
			mt.setPid(Integer.valueOf(treeDatas[1]));
			mt.setName(treeDatas[2]);
			if (!"NO_URL".equalsIgnoreCase(treeDatas[3])) {
				mt.setUrl(treeDatas[3]);
			}
			treeSet.add(mt);
		}
		treeDataLst.addAll(treeSet);
		return treeDataLst;
	}
}
