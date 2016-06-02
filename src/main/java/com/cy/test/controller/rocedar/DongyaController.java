package com.cy.test.controller.rocedar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cy.test.result.JsonObjectResult;
import com.cy.test.service.rocedar.DongyaService;

import net.sf.json.JSONObject;

@RestController
public class DongyaController {
//    private Logger logger = LogManager.getLogger(DongyaController.class);
    
    @Autowired
    private DongyaService dongyaService;
    
    /**
     * 根据手机号查询userid
     * @param phone
     * @return
     */
    @RequestMapping(value = "/dongya/getUseridByPhone/", method = RequestMethod.GET)
    public JsonObjectResult getUseridByPhone(@RequestParam(value = "phone", defaultValue = "-1") String phone) {
        dongyaService.getUseridByPhone(phone);
        JSONObject result = new JSONObject();
        result.put("userid", dongyaService.getUseridByPhone(phone));
        return new JsonObjectResult(0,result);
    }
}
