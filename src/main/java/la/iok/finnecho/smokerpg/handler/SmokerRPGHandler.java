package la.iok.finnecho.smokerpg.handler;

import la.iok.finnecho.cq.dto.RequestDataDTO;
import la.iok.finnecho.cq.util.CQSDK;
import la.iok.finnecho.cq.util.Config;
import la.iok.finnecho.qq.dto.GroupMemberDTO;
import la.iok.finnecho.qq.handler.QQMessageHandler;
import la.iok.finnecho.smokerpg.dto.SmokeInfoDTO;
import la.iok.finnecho.smokerpg.renderer.MessageRenderer;
import la.iok.finnecho.smokerpg.service.SmokeInfoComponent;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by py on 2017/3/26 0026.
 */
@Component
public class SmokerRPGHandler implements QQMessageHandler {

    @Resource
    private SmokeInfoComponent smokeInfoComponent;

    @Resource
    private MessageRenderer messageRenderer;

    /**
     * 當自己受邀入群
     *
     * @param data
     */
    @Override
    public void onInvitedToGroup(RequestDataDTO data) {
        List<String> fathersQQList = Config.getFathersQQ();
        if (fathersQQList != null) {
            fathersQQList.forEach(qq -> CQSDK.sendPrivateMsg(qq, "收到邀請入群信息:" + data.getGroup()));
        }
    }

    @Override
    public void onApplyIntoGroup(RequestDataDTO data) {

    }

    @Override
    public void onReceivedAddFriendRequest(RequestDataDTO data) {

    }

    @Override
    public void onFriendAdded(RequestDataDTO data) {

    }

    @Override
    public void onNewMemberIntoGroupByInvite(RequestDataDTO data) {

    }

    @Override
    public void onNewMemberIntoGroupByAgree(RequestDataDTO data) {

    }

    @Override
    public void onHeWasKicked(RequestDataDTO data) {

    }

    @Override
    public void onGroupMemberWasKicked(RequestDataDTO data) {

    }

    @Override
    public void onGroupMemberLeave(RequestDataDTO data) {

    }

    @Override
    public void onSetUpGroupAdmin(RequestDataDTO data) {
        List<String> fathersQQList = Config.getFathersQQ();
        if (fathersQQList != null) {
            fathersQQList.forEach(qq -> CQSDK.sendPrivateMsg(qq, "被" + data.getGroup() + "群设置为管理员:"));
        }
        CQSDK.sendGroupMsg(data.getGroup(), "已被设为管理员\n那么可以开始啦\n请发送\nSmoke @XXX|QQ号 1");
    }

    @Override
    public void onSetDownGroupAdmin(RequestDataDTO data) {

    }

    @Override
    public void onFileUploadedMessage(RequestDataDTO data) {

    }

    @Override
    public void onDiscussionGroupMessage(RequestDataDTO data) {
        CQSDK.sendGroupMsg(data.getGroup(), "你们干嘛，讨论组又不能smoke");
    }

    @Override
    public void onGroupSystemMessage(RequestDataDTO data) {

    }

    @Override
    public void onGroupAnonymousMessage(RequestDataDTO data) {

    }

    @Override
    public void onGroupMessage(RequestDataDTO data) {
        SmokeInfoDTO smokeInfoInformation = smokeInfoComponent.getSmokeInfoInformation(data);

        if (smokeInfoInformation.isSmoke()) {
            GroupMemberDTO attacker = smokeInfoInformation.getAttacker();
            GroupMemberDTO target = smokeInfoInformation.getTarget();
            Map<String, String> mes = new HashMap<>();
            mes.put("attacker", attacker.getName());
            mes.put("target", target.getName());
            if (target.isCreater()) {
                if (Math.random() > 0.1) {
                    CQSDK.sendGroupMsg(data.getGroup(), messageRenderer.render(MessageRenderer.SMOKE_CREATER, mes));
                } else {
                    CQSDK.sendGroupMsg(data.getGroup(), messageRenderer.render(MessageRenderer.SMOKE_CREATER_BACK_BITE, mes));
                }
            }
            if (target.isManager()) {
                CQSDK.sendGroupMsg(data.getGroup(), messageRenderer.render(MessageRenderer.SMOKE_MANAGER, mes));
            }

            Integer smokeTime = smokeInfoInformation.getSmokeTime();
            smokeTime = smokeTime <= 1 ? 1 : Math.round(smokeTime);
            if (smokeTime > 10) {
                smokeTime = 10;
            }
            float rate = (1 - (float) smokeTime / 10);

            float point = (float) Math.random();
            mes.put("point", String.valueOf(point));
            mes.put("rate", String.valueOf(rate * 100) + "%");
            mes.put("time", String.valueOf(smokeTime));
            if (point > 1 - rate) {
                //成功
                CQSDK.sendGroupMsg(data.getGroup(), messageRenderer.render(MessageRenderer.SMOKE, mes));
                CQSDK.setGroupBan(data.getGroup(), target.getQq(), smokeTime);
            } else {
                //反噬
                CQSDK.sendGroupMsg(data.getGroup(), messageRenderer.render(MessageRenderer.SMOKE_BACK_BIT, mes));
                smokeTime = smokeTime <= 1 ? 1 : Math.round(smokeTime / 2);
                CQSDK.setGroupBan(data.getGroup(), attacker.getQq(), smokeTime);
            }
        }
    }

    @Override
    public void onDiscussionGroupPrivateMessage(RequestDataDTO data) {

    }

    @Override
    public void onGroupPrivateMessage(RequestDataDTO data) {

    }

    @Override
    public void onOnlineStatusPrivateMessage(RequestDataDTO data) {

    }

    @Override
    public void onFriendPrivateMessage(RequestDataDTO data) {

    }
}
