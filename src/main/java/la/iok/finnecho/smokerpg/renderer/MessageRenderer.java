package la.iok.finnecho.smokerpg.renderer;

import la.iok.finnecho.qq.dto.GroupMemberDTO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by py on 2017/3/26 0026.
 */
@Component
public class MessageRenderer {
    public static final String SMOKE_CREATER = "SMOKE_CREATER";
    public static final String SMOKE_CREATER_BACK_BITE = "SMOKE_CREATER";
    public static final String SMOKE_MANAGER = "SMOKE_MANAGER";
    public static final String SMOKE = "SMOKE";
    public static final String SMOKE_BACK_BIT = "SMOKE_BACK_BIT";

    private Map<String, List<String>> template = new HashMap<>();

    public MessageRenderer() {
        template.put(SMOKE, Arrays.asList("${attacker} somke ${target} ${time}分钟\n成功率 ${rate}, 掷出点数 ${point}\nsmoke 成功"));
        template.put(SMOKE_BACK_BIT, Arrays.asList("${attacker} somke ${target} ${time}分钟\n成功率 ${rate}, 掷出点数 ${point}\nsmoke 失败，遭到反噬"));
        template.put(SMOKE_CREATER, Arrays.asList("你们这是以下犯上， 报告，有人要禁言群主", "来人呀，把${attacter}拖出去ri了", "哎，管理员不好当啊", "群主大人，你说说，该怎么处置他"));
        template.put(SMOKE_MANAGER, Arrays.asList("管理员也是不好惹的", "我是一个势力的人", "开玩笑!!", "smoke成功率0%，掷出99.99，禁言失败"));
        template.put(SMOKE_CREATER_BACK_BITE, Arrays.asList("A permission exception is detected and the attacker must be destroyed", "${attacter}你自己日上吧", "这就是代价"));

    }

    public String render(String rendererID, Map<String, String> mes) {
        List<String> strings = new ArrayList<>(template.get(rendererID));
        Collections.shuffle(strings);
        String s = strings.get(0);
        for (String s1 : mes.keySet()) {
            s = s.replace("${" + s1 + "}", mes.get(s1));
        }
        return s;
    }
}
