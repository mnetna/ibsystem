package com.slee.web.common.controller.exbuilder;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cleopatra.spring.UIView;
import com.cleopatra.ui.PageGenerator;
import springfox.documentation.annotations.ApiIgnore;

@Controller
public class CleopatraUIController {

    @PostConstruct
    private void initPageGenerator() throws IOException {
        PageGenerator instance = PageGenerator.getInstance();
        instance.setURLSuffix(".clx");
    }

    @RequestMapping("/ui/app/**/*.clx")
    @ApiIgnore
    public UIView index(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        return new UIView();
    }
}