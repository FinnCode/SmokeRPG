package la.iok.finnecho.qq.handler;

import la.iok.finnecho.cq.dto.RequestDataDTO;
import la.iok.finnecho.qq.handler.QQMessageHandler;
import org.springframework.stereotype.Component;

/**
 * Created by py on 2017/3/26 0026.
 */
@Component
public class FatherHandler implements QQMessageHandler {
    @Override
    public void onInvitedToGroup(RequestDataDTO data) {

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

    }

    @Override
    public void onSetDownGroupAdmin(RequestDataDTO data) {

    }

    @Override
    public void onFileUploadedMessage(RequestDataDTO data) {

    }

    @Override
    public void onDiscussionGroupMessage(RequestDataDTO data) {

    }

    @Override
    public void onGroupSystemMessage(RequestDataDTO data) {

    }

    @Override
    public void onGroupAnonymousMessage(RequestDataDTO data) {

    }

    @Override
    public void onGroupMessage(RequestDataDTO data) {

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
