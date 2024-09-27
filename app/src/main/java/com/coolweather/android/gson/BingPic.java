package com.coolweather.android.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BingPic {

    @SerializedName("code")
    private Integer code;

    @SerializedName("data")
    private DataDTO data;

    @SerializedName("error")
    private Object error;

    @SerializedName("updateTime")
    private Long updataTime;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public DataDTO getData() {
        return data;
    }

    public void setData(DataDTO data) {
        this.data = data;
    }

    public Object getError() {
        return error;
    }

    public Long getUpdataTime() {
        return updataTime;
    }

    public void setUpdataTime(Long updataTime) {
        this.updataTime = updataTime;
    }

    public static class DataDTO {
        @SerializedName("id")
        private Integer id;

        @SerializedName("time")
        private String time;

        @SerializedName("title")
        private String title;

        @SerializedName("url")
        private String url;

        @SerializedName("urlbase")
        private String urlbase;

        @SerializedName("copyright")
        private String copyright;

        @SerializedName("copyrightlink")
        private String copyrightlink;

        @SerializedName("urls")
        private List<String> urls;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUrlbase() {
            return urlbase;
        }

        public void setUrlbase(String urlbase) {
            this.urlbase = urlbase;
        }

        public String getCopyright() {
            return copyright;
        }

        public void setCopyright(String copyright) {
            this.copyright = copyright;
        }

        public String getCopyrightlink() {
            return copyrightlink;
        }

        public void setCopyrightlink(String copyrightlink) {
            this.copyrightlink = copyrightlink;
        }

        public List<String> getUrls() {
            return urls;
        }

        public void setUrls(List<String> urls) {
            this.urls = urls;
        }
    }

}
