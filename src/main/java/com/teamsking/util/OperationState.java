package com.teamsking.util;

/**
 * @author ynfeng
 */
public enum OperationState {

    SUCCESS("成功", 0),

    FAIL("失败", 1),

    EXCEPTION("异常发生", -1);

    String mgs;

    int code;

    OperationState(String mgs, int code) {
        this.mgs = mgs;
        this.code = code;
    }

    public String getMgs() {
        return mgs;
    }

    public void setMgs(String mgs) {
        this.mgs = mgs;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
