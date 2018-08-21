package com.fourdvoid.eve77.controller;

import com.fourdvoid.eve77.po.WxConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.MessageFormat;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private WxConfig wx;

//https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect
    @RequestMapping("/auth")
    public String auth(@RequestParam(value = "code", required = false) String code, Model model) {
        final String urlFormat = "https://open.weixin.qq.com/connect/oauth2/authorize?appid={0}&redirect_uri={1}&response_type=code&scope={2}&state={3}#wechat_redirect";
        MessageFormat fmter = new MessageFormat(urlFormat);
        // return "user/auth";
        // TODO: 1st. retrieve code first,
        // TODO: 2nd. using code to retrieve user info
        // refer to : https://blog.csdn.net/jeikerxiao/article/details/68064754
        // TODO: java to request HTTP request and parse to JSON object.
        if(code == null || code.isEmpty()) {
            String urlRedirect = fmter.format(wx.getAppId(), wx.getAuthUrl(), "snsapi_base", "base");
            return "redirect:" + urlRedirect;
        }

        model.addAttribute("code", code);

        return "user/auth";
    }
}
