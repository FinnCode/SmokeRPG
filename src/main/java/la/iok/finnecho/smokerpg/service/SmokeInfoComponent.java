package la.iok.finnecho.smokerpg.service;

import la.iok.finnecho.cq.dto.RequestDataDTO;
import la.iok.finnecho.qq.dto.FriendDTO;
import la.iok.finnecho.qq.dto.GroupMemberDTO;
import la.iok.finnecho.qq.service.GroupComponent;
import la.iok.finnecho.smokerpg.dto.SmokeInfoDTO;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * Created by py on 2017/3/26 0026.
 */
@Component
public class SmokeInfoComponent {
    @Resource
    private GroupComponent groupComponent;

    public SmokeInfoDTO getSmokeInfoInformation(RequestDataDTO data) {
        SmokeInfoDTO smokeInfoDTO = new SmokeInfoDTO();
        smokeInfoDTO.setSmoke(false);
        if (data.getGroup() == null) {
            return smokeInfoDTO;
        }

        String msg = data.getMsg();
        if (msg != null && msg.startsWith("smoke")) {
            String[] smokeMsgSplit = msg.split(" ");
            String target = smokeMsgSplit[1];
            String smokeTime = smokeMsgSplit[smokeMsgSplit.length - 1];
            if (StringUtils.isNumeric(smokeTime)) {
                int smokeTimeInt = new BigDecimal(smokeTime).intValue();
                if (smokeTimeInt > 0) {
                    GroupMemberDTO targetMember = null;
                    GroupMemberDTO attacterMember = null;
                    if (target.startsWith("[CQ:at,qq=")) {
                        targetMember = groupComponent.getGroupMemberByQQ(data.getGroup(), target.substring(10, target.length() - 1));
//                    } else if (target.startsWith("@")) {
//                        target = "";
//                        for (int i = 1; i < smokeMsgSplit.length - 1; i++) {
//                            if (smokeMsgSplit[i].isEmpty()) {
//                                continue;
//                            }
//                            target += smokeMsgSplit[i] + " ";
//                        }
//                        target = target.substring(0, target.length() - 1);
//                        targetMember = groupComponent.getGroupMemberByNickName(data.getGroup(), target.substring(1));
                    } else if (StringUtils.isNumeric(target) && target.indexOf(".") == -1) {
                        targetMember = groupComponent.getGroupMemberByQQ(data.getGroup(), target);
                    } else {
                        return smokeInfoDTO;
                    }
                    attacterMember = groupComponent.getGroupMemberByQQ(data.getGroup(), data.getQQ());
                    smokeInfoDTO.setSmoke(true);
                    smokeInfoDTO.setAttacker(attacterMember);
                    smokeInfoDTO.setTarget(targetMember);
                    smokeInfoDTO.setSmokeTime(smokeTimeInt);
                    return smokeInfoDTO;
                } else {
                    return smokeInfoDTO;
                }
            }
        }

        return smokeInfoDTO;
    }
}
