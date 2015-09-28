package com.yihen.jdb.bean;

/**
 * @author Yuanbin
 * @ProjectName: jdb_app
 * @Description:
 * @date 15-2-15 上午10:13
 */
public class ServiceProjectViewModel {

    private String seqId;

    private String itemName;

    private boolean isSelected;

    public String getSeqId() {
        return seqId;
    }

    public void setSeqId(String seqId) {
        this.seqId = seqId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }
}
