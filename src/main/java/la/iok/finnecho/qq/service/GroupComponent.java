package la.iok.finnecho.qq.service;

import la.iok.finnecho.cq.util.CQSDK;
import la.iok.finnecho.qq.dto.GroupMemberDTO;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by py on 2017/3/26 0026.
 */
@Component
public class GroupComponent {
    private Map<String, List<GroupMemberDTO>> memberListMap = new HashMap<>();

    /**
     * 从本地缓存中取得用户
     *
     * @param group
     * @param name
     * @return
     */
    private GroupMemberDTO getGroupMemberLocalByName(String group, String name) {
        List<GroupMemberDTO> groupMemberList = memberListMap.get(group);
        GroupMemberDTO result = groupMemberList.stream().filter(groupMemberDTO -> groupMemberDTO.getName().equals(name)).collect(Collectors.toList()).get(0);
        return result;
    }

    /**
     * 从本地缓存中取得用户
     *
     * @param group
     * @param qq
     * @return
     */
    private GroupMemberDTO getGroupMemberLocalByQQ(String group, String qq) {
        List<GroupMemberDTO> groupMemberList = memberListMap.get(group);
        GroupMemberDTO result = groupMemberList.stream().filter(groupMemberDTO -> groupMemberDTO.getQq().equals(qq)).collect(Collectors.toList()).get(0);
        return result;
    }


    private void clear(String group) {
        memberListMap.remove(group);
    }

    public GroupMemberDTO getGroupMemberByNickName(String group, String name) {
        if (!memberListMap.containsKey(group)) {
            memberListMap.put(group, CQSDK.getGroupMemberList2(group));
        }
        GroupMemberDTO result = getGroupMemberLocalByName(group, name);
        if (result == null) {
            clear(group);
        } else {
            return result;
        }
        return getGroupMemberByNickName(group, name);
    }

    public GroupMemberDTO getGroupMemberByQQ(String group, String qq) {
        if (!memberListMap.containsKey(group)) {
            memberListMap.put(group, CQSDK.getGroupMemberList2(group));
        }
        GroupMemberDTO result = getGroupMemberLocalByQQ(group, qq);
        if (result == null) {
            clear(group);
        } else {
            return result;
        }
        return getGroupMemberByQQ(group, qq);
    }
}
