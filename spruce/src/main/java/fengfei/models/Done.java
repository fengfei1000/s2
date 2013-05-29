package fengfei.models;

import java.util.HashMap;

public class Done extends HashMap<String, Object> {

    public static enum Status {
            Success,
            Fail,
            Error
    }

    private static final long serialVersionUID = 1L;
    final static String KeyMsg = "msg";
    final static String KeyStatus = "status";
    public String msg;
    public Status status;

    public Done() {
    }

    public Done(String msg, Status status) {
        super();
        setMsg(msg);
        setStatus(status);
    }

    public Done(Status status) {
        super();
        setStatus(status);
        setMsg(status.name());
    }

    public Done(String msg) {
        super();
        setMsg(msg);
        setStatus(Status.Success);
    }

    public String getMsg() {
        return msg = (String) get(KeyMsg);
    }

    public void setMsg(String msg) {
        this.msg = msg;
        this.put(KeyMsg, msg);
    }

    public Status getStatus() {
        return status = (Status) get(KeyStatus);
    }

    public void setStatus(Status status) {
        this.status = status;
        this.put(KeyStatus, status);
    }

}
