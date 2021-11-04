package com.app.mob.halloween;

import java.util.Date;

public class Hallow {

    String  HallowId,HallowImg,HallowName,timeId,crtDateText,HallowStory;

    private long crtdDate;

    public Hallow(String hallowId, String hallowImg, String hallowName, String timeId, String crtDateText, String hallowStory, long crtdDate) {
        HallowId = hallowId;
        HallowImg = hallowImg;
        HallowName = hallowName;
        this.timeId = timeId;
        this.crtDateText = crtDateText;
        HallowStory = hallowStory;
        this.crtdDate = crtdDate;
    }

    public String getHallowStory() {
        return HallowStory;
    }

    public void setHallowStory(String hallowStory) {
        HallowStory = hallowStory;
    }

    public Hallow() {
        this.crtdDate = new Date().getTime();

    }

    public String getHallowId() {
        return HallowId;
    }

    public void setHallowId(String hallowId) {
        HallowId = hallowId;
    }

    public String getHallowImg() {
        return HallowImg;
    }

    public void setHallowImg(String hallowImg) {
        HallowImg = hallowImg;
    }

    public String getHallowName() {
        return HallowName;
    }

    public void setHallowName(String hallowName) {
        HallowName = hallowName;
    }

    public String getTimeId() {
        return timeId;
    }

    public void setTimeId(String timeId) {
        this.timeId = timeId;
    }

    public String getCrtDateText() {
        return crtDateText;
    }

    public void setCrtDateText(String crtDateText) {
        this.crtDateText = crtDateText;
    }

    public long getCrtdDate() {
        return crtdDate;
    }

    public void setCrtdDate(long crtdDate) {
        this.crtdDate = crtdDate;
    }
}
