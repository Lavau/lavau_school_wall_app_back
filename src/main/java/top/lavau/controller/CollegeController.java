package top.lavau.controller;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.lavau.service.CollegeService;

/**
 * description
 *
 * @author Leet
 * @date 2020-12-01 17:24
 **/
@Slf4j
@RestController
@RequestMapping("/app/college")
public class CollegeController {

    @Autowired
    private CollegeService collegeService;

    @GetMapping("/list")
    public String listCollege() {
        return JSON.toJSONString(collegeService.listCollege());
    }
}
