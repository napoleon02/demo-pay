package com.demo.pay.web.unionpay;

import cn.hutool.core.io.FileUtil;
import com.demo.pay.support.response.ResponseWrapper;
import com.demo.pay.unionpay.*;
import com.demo.pay.unionpay.internal.SecureHelper;
import com.demo.pay.unionpay.request.UnionPayQueryTransRequest;
import com.demo.pay.unionpay.response.UnionPayQueryTransResponse;
import com.demo.pay.web.BaseCtl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 作者：徐承恩
 * 邮箱：xuchengen@gmail.com
 * 日期：2019/8/30
 */
@Controller
@RequestMapping(value = "/unionpay/queryTrans")
public class QueryTransCtl extends BaseCtl {

    @GetMapping(value = {"", "/"})
    @ResponseBody
    public ResponseWrapper index() {
        try {
            UnionPayCertInfo certInfo = SecureHelper.getCertInfoFromInputStream(FileUtil.getInputStream("/users/xuchengen/z_dev/files/certs/acp_test_sign.pfx"), "000000");

            UnionPayV510Signture unionpayV510Signture = new UnionPayV510Signture()
                    .setRootCertStr(SecureHelper.getCertStrFromInputStream(FileUtil.getInputStream("/users/xuchengen/z_dev/files/certs/acp_test_root.cer")))
                    .setMiddleCertStr(SecureHelper.getCertStrFromInputStream(FileUtil.getInputStream("/users/xuchengen/z_dev/files/certs/acp_test_middle.cer")))
                    .setEncryCertStr(SecureHelper.getCertStrFromInputStream(FileUtil.getInputStream("/users/xuchengen/z_dev/files/certs/acp_test_enc.cer")))
                    .setMerchantCertInfo(certInfo);

            UnionPayConfig unionPayConfig = new UnionPayConfig()
                    .setEncoding(UnionPayConstants.DEFAULT_UTF8)
                    .setHost("https://gateway.test.95516.com")
                    .setMerchantId("777290058172475")
                    .setSignature(unionpayV510Signture)
                    .setFileDownloadHost("https://filedownload.test.95516.com/");

            DefaultUnionPayClient client = new DefaultUnionPayClient(unionPayConfig);

            UnionPayQueryTransRequest unionPayQueryTransRequest = new UnionPayQueryTransRequest();
            unionPayQueryTransRequest.setOrderId("20190830111505633316");
            unionPayQueryTransRequest.setTxnType("00");
            unionPayQueryTransRequest.setTxnSubType("00");
            unionPayQueryTransRequest.setAccessType("0");

            UnionPayQueryTransResponse respone = client.execute(unionPayQueryTransRequest);

            return ResponseWrapper.ok(respone);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}