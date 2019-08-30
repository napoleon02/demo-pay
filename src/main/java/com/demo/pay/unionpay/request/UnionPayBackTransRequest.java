package com.demo.pay.unionpay.request;

import com.demo.pay.unionpay.UnionPayRequest;
import com.demo.pay.unionpay.response.UnionPayBackTransResponse;

/**
 * 在线网关支付--消费撤销响应模型<br>
 * 客服小姐姐说该接口比退货接口要快
 * 作者：徐承恩
 * 邮箱：xuchengen@gmail.com
 * 日期：2019/8/30
 */
public class UnionPayBackTransRequest extends UnionPayCommonRequest implements UnionPayRequest<UnionPayBackTransResponse> {

    private static final String API_PATH = "/gateway/api/backTransReq.do";

    /**
     * 产品类型
     */
    private String bizType = "000000";

    /**
     * 后台通知地址
     */
    private String backUrl;

    /**
     * 交易金额
     */
    private String txnAmt;

    /**
     * 渠道类型
     */
    private String channelType;

    /**
     * 商户订单号(该订单号非原交易商户订单号，而是重新生成一笔订单号)
     */
    private String orderId;

    /**
     * 原交易查询流水号
     */
    private String origQryId;

    /**
     * 二级商户代码
     */
    private String subMerId;

    /**
     * 二级商户简称
     */
    private String subMerAbbr;

    /**
     * 二级商户名称
     */
    private String subMerName;

    /**
     * 保留域
     */
    private String reserved;

    /**
     * 请求方保留域
     */
    private String reqReserved;

    /**
     * 终端号
     */
    private String termId;

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public String getBackUrl() {
        return backUrl;
    }

    public void setBackUrl(String backUrl) {
        this.backUrl = backUrl;
    }

    public String getTxnAmt() {
        return txnAmt;
    }

    public void setTxnAmt(String txnAmt) {
        this.txnAmt = txnAmt;
    }

    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrigQryId() {
        return origQryId;
    }

    public void setOrigQryId(String origQryId) {
        this.origQryId = origQryId;
    }

    public String getSubMerId() {
        return subMerId;
    }

    public void setSubMerId(String subMerId) {
        this.subMerId = subMerId;
    }

    public String getSubMerAbbr() {
        return subMerAbbr;
    }

    public void setSubMerAbbr(String subMerAbbr) {
        this.subMerAbbr = subMerAbbr;
    }

    public String getSubMerName() {
        return subMerName;
    }

    public void setSubMerName(String subMerName) {
        this.subMerName = subMerName;
    }

    public String getReserved() {
        return reserved;
    }

    public void setReserved(String reserved) {
        this.reserved = reserved;
    }

    public String getReqReserved() {
        return reqReserved;
    }

    public void setReqReserved(String reqReserved) {
        this.reqReserved = reqReserved;
    }

    public String getTermId() {
        return termId;
    }

    public void setTermId(String termId) {
        this.termId = termId;
    }

    @Override
    public String getApiPath() {
        return API_PATH;
    }

    @Override
    public Class<UnionPayBackTransResponse> getResponseClass() {
        return UnionPayBackTransResponse.class;
    }
}