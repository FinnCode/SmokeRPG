package la.iok.finnecho.qq.dto;

import java.io.Serializable;

/**
 * 好友列表
 * 
 *
 */
public class FriendDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;// 昵称
	private Long qq;// QQ

	public FriendDTO() {
	}

	public FriendDTO(String name, Long qq) {
		super();
		this.name = name;
		this.qq = qq;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getQq() {
		return qq;
	}

	public void setQq(Long qq) {
		this.qq = qq;
	}

}
