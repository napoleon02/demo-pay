package com.github.xuchengen.web.alipay;

import cn.hutool.json.JSONUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradePrecreateModel;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.github.xuchengen.setting.AliPaySetting;
import com.github.xuchengen.setting.SettingTool;
import com.github.xuchengen.web.BaseCtl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 支付宝-扫码支付
 * 作者：徐承恩
 * 邮箱：xuchengen@gmail.com
 * 日期：2019/9/18
 */
@Controller
@RequestMapping(value = "/alipay/scanCodePay")
public class ScanCodePayCtl extends BaseCtl {

    private static final Log log = LogFactory.get(ScanCodePayCtl.class);

    @GetMapping(value = {"", "/"})
    public String index(ModelMap modelMap) {
        modelMap.put("notifyUrl", getHostUrl() + "/alipay/doNotify");
        return "/alipay/scanCodePay.html";
    }

    @PostMapping(value = "/getQRCode")
    @ResponseBody
    public String getQRCode(String orderNo,
                            String subject,
                            String totalAmount,
                            String notifyUrl) {
        try {
            AliPaySetting aliPaySetting = SettingTool.getAliPaySetting();

            DefaultAlipayClient client = DefaultAlipayClient.builder(aliPaySetting.getGateway(), aliPaySetting.getAppId(), aliPaySetting.getPrivateKey())
                    .alipayPublicKey(aliPaySetting.getPublicKey())
                    .charset(aliPaySetting.getCharset())
                    .format(aliPaySetting.getFormat())
                    .signType(aliPaySetting.getSignType())
                    .build();

            AlipayTradePrecreateRequest alipayTradePrecreateRequest = new AlipayTradePrecreateRequest();
            alipayTradePrecreateRequest.setNotifyUrl(notifyUrl);

            AlipayTradePrecreateModel alipayTradePrecreateModel = new AlipayTradePrecreateModel();
            alipayTradePrecreateModel.setOutTradeNo(orderNo);
            alipayTradePrecreateModel.setSubject(subject);
            alipayTradePrecreateModel.setTotalAmount(totalAmount);
            alipayTradePrecreateModel.setProductCode("FACE_TO_FACE_PAYMENT");

            alipayTradePrecreateRequest.setBizModel(alipayTradePrecreateModel);

            AlipayTradePrecreateResponse alipayTradePrecreateResponse = client.execute(alipayTradePrecreateRequest);

            if (alipayTradePrecreateResponse.isSuccess()) {
                return alipayTradePrecreateResponse.getQrCode();
            } else {
                throw new RuntimeException("调用支付宝扫码支付失败");
            }
        } catch (Exception e) {
            log.error("调用支付宝扫码支付接口异常：{}", JSONUtil.toJsonStr(e));
            throw new RuntimeException(e);
        }
    }

}
