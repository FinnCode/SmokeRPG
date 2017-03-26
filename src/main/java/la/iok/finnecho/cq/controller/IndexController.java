package la.iok.finnecho.cq.controller;

import java.io.DataInputStream;
import java.net.URLDecoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import com.google.gson.Gson;
import la.iok.finnecho.cq.dto.RequestDataDTO;
import la.iok.finnecho.cq.handler.MessageHandle;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 消息接收Controller
 *
 */
@Controller
public class IndexController {
    // 聊天记录单独存放
    private Logger log = Logger.getLogger("chat");

    @Resource
    private MessageHandle messageHandle;

    @RequestMapping(value = "/coolq", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String coolq(HttpServletRequest request) {
        RequestDataDTO requestData = null;
        try {
            // 消息长度
            int len = request.getContentLength();
            // 创建消息长度的字节数组
            byte[] originData = new byte[len];
            // 写入数据
            DataInputStream in = new DataInputStream(request.getInputStream());
            in.readFully(originData);
            in.close();
            // 得到Json消息
            String str = new String(originData);
            // 转码
            String str1 = URLDecoder.decode(str, "UTF-8");
            log.info("接收消息：" + str1);
            Gson g = new Gson();
            // json转对象（消息对象）
            requestData = g.fromJson(str1, RequestDataDTO.class);
            log.info("消息内容：" + requestData.getMsg());
            // 交给线程处理消息
            messageHandle.handle(requestData);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return "[]";
    }

}
