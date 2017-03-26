package la.iok.finnecho.smokerpg.dto;

import la.iok.finnecho.qq.dto.GroupMemberDTO;

/**
 * Created by py on 2017/3/26 0026.
 */
public class SmokeInfoDTO {

    private boolean isSmoke;

    private GroupMemberDTO target;

    private GroupMemberDTO attacker;

    private Integer smokeTime;

    public GroupMemberDTO getTarget() {
        return target;
    }

    public void setTarget(GroupMemberDTO target) {
        this.target = target;
    }

    public GroupMemberDTO getAttacker() {
        return attacker;
    }

    public void setAttacker(GroupMemberDTO attacker) {
        this.attacker = attacker;
    }

    public boolean isSmoke() {
        return isSmoke;
    }

    public void setSmoke(boolean smoke) {
        isSmoke = smoke;
    }

    public Integer getSmokeTime() {
        return smokeTime;
    }

    public void setSmokeTime(Integer smokeTime) {
        this.smokeTime = smokeTime;
    }
}
