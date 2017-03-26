package la.iok.finnecho.cq.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import la.iok.finnecho.cq.util.CQSDK;
import la.iok.finnecho.qq.dto.FriendDTO;
import la.iok.finnecho.qq.dto.GroupListDTO;
import la.iok.finnecho.qq.dto.GroupMemberInfoDTO;
import la.iok.finnecho.qq.dto.GroupMemberDTO;
import la.iok.finnecho.qq.dto.StrangerInfoDTO;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 供测试用的Controller
 */
@Controller
@RequestMapping(value = "testAction")
public class TestController {

	//获取群成员的信息
	@RequestMapping(value = "getGroupMemberInfo")
	public void getGroupMemberInfo(){
		GroupMemberInfoDTO info = CQSDK.getGroupMemberInfo("215400054", "913838835", true);
		System.out.println("群昵称："+info.getCard());
		System.out.println("昵称："+info.getName());
		System.out.println("QQ："+info.getQQ());
		System.out.println("等级："+info.getLevel());
	}
	
	//获取陌生人信息
	@RequestMapping(value = "getStrangerInfo")
	public void getStrangerInfo(){
		StrangerInfoDTO info = CQSDK.getStrangerInfo("609056251", true);
		System.out.println("昵称："+info.getName());
		System.out.println("性别："+(info.getGender() == 0 ? "男":"女"));
		System.out.println("QQ："+info.getQQ());
		System.out.println("年龄："+info.getOld());
	}
	
	//获取群列表
	@RequestMapping(value = "getGroupList")
	public void getGroupList(){
		List<GroupListDTO> list = CQSDK.getGroupList();
		System.out.println("我共有"+list.size()+"个群");
		for(GroupListDTO vo : list){
			System.out.println("=>群号:"+vo.getGroup());
			System.out.println("=>群昵称:"+vo.getGroupNickName());
			System.out.println("=>群创建者QQ:"+vo.getGroupOwner());
			System.out.println();
		}
	}
	
	//获取群成员列表1
	@RequestMapping(value = "getGroupMemberList1")
	public void getGroupMemberList1(){
		List<GroupMemberInfoDTO> list = CQSDK.getGroupMemberList1("215400054");
		System.out.println("共有"+list.size()+"个成员");
		for(GroupMemberInfoDTO vo : list){
			System.out.println("=>群号："+vo.getGroup());
			System.out.println("=>QQ："+vo.getQQ());
			System.out.println("=>昵称："+vo.getName());
			System.out.println("=>群名片："+vo.getCard());
			System.out.println("=>Q龄："+vo.getOld());
			System.out.println("=>等级："+vo.getLevel());
			System.out.println("=>性别："+(vo.getGender() == 0 ? "男":"女"));
			System.out.println("=>管理权限："+vo.getPower());
//			System.out.println("=>"+vo.getPower());
			System.out.println();
		}
	}
	
	//获取群成员列表2
	@RequestMapping(value = "getGroupMemberList2")
	public void getGroupMemberList2(){
		List<GroupMemberDTO> list = CQSDK.getGroupMemberList2("215400054");
		System.out.println("共有"+list.size()+"个成员");
		for(GroupMemberDTO vo : list){
			System.out.println("=>群名片："+vo.getName());
			System.out.println("=>QQ："+vo.getQq());
			System.out.println("=>是创建者："+(vo.isCreater() ? "是":"否"));
			System.out.println("=>是管理员："+(vo.isManager() ? "是":"否"));
			System.out.println();
		}
	}
	
	//获取好友列表的方法
	@RequestMapping(value = "getFriendList")
	@ResponseBody
	public String getFriendList(){
		List<FriendDTO> list = CQSDK.getFriendList();
		System.out.println("共有"+list.size()+"个好友");
		for(FriendDTO vo : list){
			System.out.println("=>昵称："+vo.getName());
			System.out.println("=>QQ："+vo.getQq());
			System.out.println();
		}
		return "";
	}
}
