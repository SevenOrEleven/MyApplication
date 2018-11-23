package com.ymdk.xghls.myapplication.bean;

import java.io.Serializable;

public class BaseEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private int status;
    private String msg;
    private String data;

    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }


    private String access_token;
    private String openid;
    private String nickname;
    private String headimgurl;
    private String refresh_token;

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getOpenid() {
        return openid;
    }

    private int state;//微信登录状态

    public void setState(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getRefresh_token() {
        return refresh_token;
    }


}
