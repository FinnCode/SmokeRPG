package la.iok.finnecho.qq.dto;

import java.io.Serializable;

/**
 * 群列表
 * 
 *
 */
public class GroupListDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long group;// 群号
	private String groupNickName;// 群昵称
	private Long groupOwner;// 群创建者QQ

	public GroupListDTO() {
	}

	public GroupListDTO(Long group, String groupNickName, Long groupOwner) {
		super();
		this.group = group;
		this.groupNickName = groupNickName;
		this.groupOwner = groupOwner;
	}

	public Long getGroup() {
		return group;
	}

	public void setGroup(Long group) {
		this.group = group;
	}

	public String getGroupNickName() {
		return groupNickName;
	}

	public void setGroupNickName(String groupNickName) {
		this.groupNickName = groupNickName;
	}

	public Long getGroupOwner() {
		return groupOwner;
	}

	public void setGroupOwner(Long groupOwner) {
		this.groupOwner = groupOwner;
	}

}
