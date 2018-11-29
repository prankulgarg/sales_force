/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rc.model;

/**
 *
 * @author Admin
 */
public class RequestUserDetail {
    private int employeeId;
    private String token;
    private String fcmId;
    private String deviceId;
    private String deviceName;
    private String emeiId;
    private String appVersion;

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getFcmId() {
        return fcmId;
    }

    public void setFcmId(String fcmId) {
        this.fcmId = fcmId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getEmeiId() {
        return emeiId;
    }

    public void setEmeiId(String emeiId) {
        this.emeiId = emeiId;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    @Override
    public String toString() {
        return "RequestUserDetail{" + "employeeId=" + employeeId + ", token=" + token + ", fcmId=" + fcmId + ", deviceId=" + deviceId + ", deviceName=" + deviceName + ", emeiId=" + emeiId + ", appVersion=" + appVersion + '}';
    }
    
    
    
    
}
