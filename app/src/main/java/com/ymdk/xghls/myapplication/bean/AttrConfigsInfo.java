package com.ymdk.xghls.myapplication.bean;

import java.io.Serializable;
import java.util.List;

public class AttrConfigsInfo implements Serializable {


    /**
     * specList : [{"attrId":"1","attrValue":"黑","attrName":"color"},{"attrId":"3","attrValue":"粉","attrName":"color"},{"attrId":"2","attrValue":"蓝","attrName":"color"}]
     * title : 颜色
     */

    private String title;
    private List<SpecListBean> specList;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<SpecListBean> getSpecList() {
        return specList;
    }

    public void setSpecList(List<SpecListBean> specList) {
        this.specList = specList;
    }

    public static class SpecListBean {
        /**
         * attrId : 1
         * attrValue : 黑
         * attrName : color
         */

        private String attrId;
        private String attrValue;
        private String attrName;
        public boolean isSelect = false;


        public String getAttrId() {
            return attrId;
        }

        public void setAttrId(String attrId) {
            this.attrId = attrId;
        }

        public String getAttrValue() {
            return attrValue;
        }

        public void setAttrValue(String attrValue) {
            this.attrValue = attrValue;
        }

        public String getAttrName() {
            return attrName;
        }

        public void setAttrName(String attrName) {
            this.attrName = attrName;
        }
    }

}
