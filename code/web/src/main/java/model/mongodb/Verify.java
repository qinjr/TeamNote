package model.mongodb;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by qjr on 2017/6/27.
 */
public class Verify {
    private int verifyId;
    private Date date;
    private int type;//comment:0, note:1
    private int targetId;
    private String reason;
    private int checked;//checked:1, unchecked:0
    private int reporterId;
    public Verify() {}

    public Verify(Date date, int type, int targetId, String reason, int checked, int reporterId) {
        this.date = date;
        this.type = type;
        this.targetId = targetId;
        this.reason = reason;
        this.checked = checked;
        this.reporterId = reporterId;
    }

    public int getVerifyId() {
        return verifyId;
    }

    public void setVerifyId(int verifyId) {
        this.verifyId = verifyId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getTargetId() {
        return targetId;
    }

    public void setTargetId(int targetId) {
        this.targetId = targetId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getChecked() {
        return checked;
    }

    public void setChecked(int checked) {
        this.checked = checked;
    }

    public int getReporterId() {
        return reporterId;
    }

    public void setReporterId(int reporterId) {
        this.reporterId = reporterId;
    }
}
