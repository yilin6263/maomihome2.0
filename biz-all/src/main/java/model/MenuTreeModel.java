package model;

import java.util.List;

/**
 * Created by 17020406 on 2017/3/29.
 */
public class MenuTreeModel {

	private int id;

	private int pid;

	private String name;

	private String url;

	String target;

	Boolean isParent;

	Boolean isLeaf;
	/**
	 * 下一级菜单
	 */
	private List<MenuTreeModel> subMenuTrees;


	public int compareTo(MenuTreeModel obj) {
		return Integer.valueOf(this.id).compareTo(Integer.valueOf(obj.id));
	}


	public boolean equals(Object o) {
		if (this == o){
			return true;
		}
		if (o == null || getClass() != o.getClass()){
			return false;
		}
		MenuTreeModel that = (MenuTreeModel) o;
		if (id != that.id){
			return false;
		}

		return true;
	}


	public int hashCode() {
		return id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public Boolean getIsParent() {
		return isParent;
	}

	public void setIsParent(Boolean isParent) {
		this.isParent = isParent;
	}

	public Boolean getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(Boolean isLeaf) {
		this.isLeaf = isLeaf;
	}

	public List<MenuTreeModel> getSubMenuTrees() {
		return subMenuTrees;
	}

	public void setSubMenuTrees(List<MenuTreeModel> subMenuTrees) {
		this.subMenuTrees = subMenuTrees;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MenuTreeModel [id=");
		builder.append(id);
		builder.append(", pid=");
		builder.append(pid);
		builder.append(", name=");
		builder.append(name);
		builder.append(", url=");
		builder.append(url);
		builder.append(", target=");
		builder.append(target);
		builder.append(", isParent=");
		builder.append(isParent);
		builder.append(", isLeaf=");
		builder.append(isLeaf);
		builder.append(", subMenuTrees=");
		builder.append(subMenuTrees);
		builder.append("]");
		return builder.toString();
	}


}
