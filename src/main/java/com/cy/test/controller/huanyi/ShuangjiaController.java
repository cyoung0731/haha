package com.cy.test.controller.huanyi;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cy.util.CyUtil;
import com.cy.util.DeviceConstants;
import com.cy.util.DeviceUtil;

import net.sf.json.JSONObject;

@RestController
public class ShuangjiaController {
    private static Logger logger = LogManager.getLogger(ShuangjiaController.class);

    /**
     * 测试双佳描二维码绑定体检报告接口
     * 
     * @param phone
     * @return
     */
    @RequestMapping(value = "/fangzhou/aio/shuangjia/scan/", method = RequestMethod.GET)
    public JSONObject getDevices(@RequestParam(value = "token", defaultValue = "-1") String token,
            @RequestParam(value = "id", defaultValue = "-1") String id,
            @RequestParam(value = "phone", defaultValue = "-1") String phone,
            @RequestParam(value = "appid", defaultValue = "-1") int appid,
            @RequestParam(value = "time", defaultValue = "-1") String time) {
        String url = "";
        if (appid == 1) {
            url = DeviceConstants.IP_TEST_DY + "/device/aio/nianjia/scan/";
        } else if (appid == 2) {
            url = DeviceConstants.IP_TEST_FZ + "/hy/device/aio/nianjia/scan/";
        }
        String response = "";
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("token", token));
        params.add(new BasicNameValuePair("id", id));
        params.add(new BasicNameValuePair("phone", phone));
        params.add(new BasicNameValuePair("time", time));
        try {
            response = CyUtil.httpGet(url, params, null);
            logger.debug("-----response={}", response);
            JSONObject responseJson = JSONObject.fromObject(response);
            return responseJson;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 测试双佳扫描二维码绑定体检报告接口
     * 
     * @param phone
     * @return
     */
    @RequestMapping(value = "/fangzhou/aio/shuangjia/upload/", method = RequestMethod.GET)
    public JSONObject uploadReport(@RequestParam(value = "token", defaultValue = "-1") String token,
            @RequestParam(value = "id", defaultValue = "-1") String id,
            @RequestParam(value = "phone", defaultValue = "-1") String phone,
            @RequestParam(value = "time", defaultValue = "-1") String time) {
        // String url = IP_TEST + "/hy/device/aio/report/nianjia/";
        String url = DeviceConstants.IP_TEST_DIS + "/device/aio/report/nianjia/";

        String report = "{\"machineId\":\"SK-HSX60HD15051201\",\"macAddr\":\"00:0E:C4:CA:0C:EE\",\"recordNo\":\"" + time
                + "\",\"member\":{\"name\":\"\",\"phone\":\"" + phone + "\",\"idcard\":\"" + id
                + "\",\"age\":\"37\",\"sex\":\"男\",\"address\":\"\",\"measureTime\":\"2016-02-19 10:28:34\",\"birthday\":\"1979-07-15\",\"barcode\":\"\",\"iccard\":\"\",\"userIcon\":\"\"},\"height\":{\"height\":\"180\",\"weight\":\"64.30\",\"BMI\":\"19.80\",\"idealWeight\":\"72.90\"},\"fat\":{\"fatRate\":\"14.60\",\"fat\":\"9.30\",\"exceptFat\":\"55\",\"waterRate\":\"62.50\",\"water\":\"40.10\",\"minerals\":\"1.50\",\"protein\":\"12\",\"fic\":\"26.70\",\"foc\":\"13.40\",\"muscle\":\"52.10\",\"fatAdjust\":\"-0.10\",\"weightAdjust\":\"8.60\",\"muscleAdjust\":\"8.70\",\"basicMetabolism\":\"1495\",\"viscera\":\"5\",\"result\":\"0\",\"bmc\":\"2.80\",\"quganMuscle\":\"28.60\",\"quganFat\":\"4.60\",\"zuotuiMuscle\":\"9.00\",\"zuobiMuscle\":\"2.60\",\"youbiMuscle\":\"2.80\",\"youtuiMuscle\":\"9.10\",\"zuobiFat\":\"0.40\",\"zuotuiFat\":\"2.00\",\"youbiFat\":\"0.40\",\"youtuiFat\":\"1.90\"},\"bloodPressure\":{\"highPressure\":\"125\",\"lowPressure\":\"80\",\"pulse\":\"76\",\"result\":\"2\"},\"bo\":{\"bo\":\"93\",\"result\":\"1\",\"startTime\":\"2016/2/19 10:31:54\",\"endTime\":\"2016/2/19 10:32:16\",\"listBo\":\"88,88,88,88,88,88,88,88,88,88,88,93,93\",\"listBoPulse\":\"43,43,43,43,43,43,43,43,43,43,43,83,83\"},\"temperature\":{\"temperature\":\"36.50\",\"result\":\"2\"}}";
        try {
            String response = DeviceUtil.httpPost(url, null, null, report);
            logger.debug("上传双佳体检报告返回结果=" + response);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return null;
    }
}
