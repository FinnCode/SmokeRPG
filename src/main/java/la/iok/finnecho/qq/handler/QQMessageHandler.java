package la.iok.finnecho.qq.handler;

import la.iok.finnecho.cq.dto.RequestDataDTO;

/**
 * Created by py on 2017/3/26 0026.
 */
public interface QQMessageHandler {
    void onInvitedToGroup(RequestDataDTO data);

    void onApplyIntoGroup(RequestDataDTO data);

    void onReceivedAddFriendRequest(RequestDataDTO data);

    void onFriendAdded(RequestDataDTO data);

    void onNewMemberIntoGroupByInvite(RequestDataDTO data);

    void onNewMemberIntoGroupByAgree(RequestDataDTO data);

    void onHeWasKicked(RequestDataDTO data);

    void onGroupMemberWasKicked(RequestDataDTO data);

    void onGroupMemberLeave(RequestDataDTO data);

    void onSetUpGroupAdmin(RequestDataDTO data);

    void onSetDownGroupAdmin(RequestDataDTO data);

    void onFileUploadedMessage(RequestDataDTO data);

    void onDiscussionGroupMessage(RequestDataDTO data);

    void onGroupSystemMessage(RequestDataDTO data);

    void onGroupAnonymousMessage(RequestDataDTO data);

    void onGroupMessage(RequestDataDTO data);

    void onDiscussionGroupPrivateMessage(RequestDataDTO data);

    void onGroupPrivateMessage(RequestDataDTO data);

    void onOnlineStatusPrivateMessage(RequestDataDTO data);

    void onFriendPrivateMessage(RequestDataDTO data);
}
