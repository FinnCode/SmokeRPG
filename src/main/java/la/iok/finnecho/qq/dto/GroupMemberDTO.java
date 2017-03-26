package la.iok.finnecho.qq.dto;

import java.io.Serializable;

/**
 * 群成员列表，包含是否是创建者、是否是管理员、昵称、QQ
 * 
 *
 */
public class GroupMemberDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;// 昵称
	private String qq;// QQ
	private boolean isCreater;// 是否是创建者
	private boolean isManager;// 是否是管理员

	public GroupMemberDTO() {
	}

	public GroupMemberDTO(String name, String qq) {
		super();
		this.name = name;
		this.qq = qq;
	}

	public GroupMemberDTO(String name, String qq, boolean isCreater, boolean isManager) {
		super();
		this.name = name;
		this.qq = qq;
		this.isCreater = isCreater;
		this.isManager = isManager;
	}

	public boolean isCreater() {
		return isCreater;
	}

	public void setCreater(boolean isCreater) {
		this.isCreater = isCreater;
	}

	public boolean isManager() {
		return isManager;
	}

	public void setManager(boolean isManager) {
		this.isManager = isManager;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

}
