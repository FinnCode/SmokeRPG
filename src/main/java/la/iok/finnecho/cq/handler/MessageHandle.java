package la.iok.finnecho.cq.handler;

import la.iok.finnecho.cq.dto.RequestDataDTO;
import la.iok.finnecho.qq.handler.QQMessageHandler;
import la.iok.finnecho.qq.handler.FatherHandler;
import la.iok.finnecho.smokerpg.handler.SmokerRPGHandler;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 消息处理器
 */
@Component
public class MessageHandle {

    private List<QQMessageHandler> handlers = new ArrayList<>();

    @Resource
    private FatherHandler fatherHandler;
    @Resource
    private SmokerRPGHandler smokerRPGHandler;

    @PostConstruct
    public void init(){
        addListener(fatherHandler);
        addListener(smokerRPGHandler);
    }

    public void addListener(QQMessageHandler handler) {
        this.handlers.add(handler);
    }

    public void handle(RequestDataDTO data) {
        String message = null;
        switch (data.getType()) {
            //私聊消息
            case 1:
                if (data.getSubType() == 11) {//来自好友
                    handlers.forEach(handler -> handler.onFriendPrivateMessage(data));
                } else if (data.getSubType() == 1) {//来自在线状态 私聊
                    handlers.forEach(handler -> handler.onOnlineStatusPrivateMessage(data));
                } else if (data.getSubType() == 2) {//来自群 私聊
                    handlers.forEach(handler -> handler.onGroupPrivateMessage(data));
                } else if (data.getSubType() == 3) {//来自讨论组 私聊
                    handlers.forEach(handler -> handler.onDiscussionGroupPrivateMessage(data));
                }
                break;
            //群消息
            case 2:

                if (data.getSubType() == 1) {//普通消息
                    handlers.forEach(handler -> handler.onGroupMessage(data));
                } else if (data.getSubType() == 2) {//匿名消息
                    handlers.forEach(handler -> handler.onGroupAnonymousMessage(data));
                } else if (data.getSubType() == 3) {//系统消息
                    handlers.forEach(handler -> handler.onGroupSystemMessage(data));
                }
                break;
            //讨论组信息
            case 4:
                handlers.forEach(handler -> handler.onDiscussionGroupMessage(data));
                break;
            //上传群文件
            case 11:
                handlers.forEach(handler -> handler.onFileUploadedMessage(data));
                break;
            //群管理员变动
            case 101:
                if (data.getSubType() == 1) {//被取消管理员
                    handlers.forEach(handler -> handler.onSetDownGroupAdmin(data));
                } else if (data.getSubType() == 2) {//被设置管理员
                    handlers.forEach(handler -> handler.onSetUpGroupAdmin(data));
                }
                break;
            //群成员减少
            case 102:
                if (data.getSubType() == 1) {//群员离开
                    handlers.forEach(handler -> handler.onGroupMemberLeave(data));
                } else if (data.getSubType() == 2) {//群员被踢
                    handlers.forEach(handler -> handler.onGroupMemberWasKicked(data));
                } else if (data.getSubType() == 3) {//自己(即登录号)被踢
                    handlers.forEach(handler -> handler.onHeWasKicked(data));
                }
                break;
            //群成员增加
            case 103:
                if (data.getSubType() == 1) {//管理员已同意
                    handlers.forEach(handler -> handler.onNewMemberIntoGroupByAgree(data));
                } else if (data.getSubType() == 2) {//管理员邀请
                    handlers.forEach(handler -> handler.onNewMemberIntoGroupByInvite(data));
                }
                break;
            //好友已添加
            case 201:
                handlers.forEach(handler -> handler.onFriendAdded(data));
                break;
            //请求添加好友
            case 301:
                handlers.forEach(handler -> handler.onReceivedAddFriendRequest(data));
                break;
            //请求添加群
            case 302:
                if (data.getSubType() == 1) {//他人申请入群
                    handlers.forEach(handler -> handler.onApplyIntoGroup(data));
                } else if (data.getSubType() == 2) {//自己(即登录号)受邀入群
                    handlers.forEach(handler -> handler.onInvitedToGroup(data));
                }
                break;
            default:
                break;
        }
    }
}
