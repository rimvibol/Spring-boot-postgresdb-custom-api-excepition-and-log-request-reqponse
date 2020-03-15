package com.rvb.springwithapectlog.model;


import javax.persistence.*;

@Entity
@Table (name = "SYS_LOG")
public class SysLog {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column (name = "ID")
    private Long id;

    @Column (name = "REQUEST_DATA")
    private String requestData;

    @Column (name = "RESPONE_DATA")
    private String responseData;

    @Column (name = "REQUEST_URL")
    private  String requestUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRequestData() {
        return requestData;
    }

    public void setRequestData(String requestData) {
        this.requestData = requestData;
    }

    public String getResponseData() {
        return responseData;
    }

    public void setResponseData(String responseData) {
        this.responseData = responseData;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }
}
