package cn.otote.springbootdemo.controller;

import cn.otote.springbootdemo.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * Create on 2019/3/1 16:39
 *
 * @Author stevenl
 */
@Controller
public class TestController {

    @Autowired
    private FileService fileService;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(@RequestParam("img") MultipartFile file, ModelMap modelMap) throws Exception {
        String path = fileService.upload(file);
        modelMap.addAttribute("path",path);
        return "success";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(){
        return "index";
    }


}
