package com.coolweather.android.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;
//当前空气质量
//@SerializedName()注解的目的让JSON 字段和Java字段之间建立映射关系
/*aqi具体内容：
{
  "code": "200",
  "updateTime": "2021-02-16T14:42+08:00",
  "fxLink": "http://hfx.link/2ax4",
  "now": {
    "pubTime": "2021-02-16T14:00+08:00",
    "aqi": "28",
    "level": "1",
    "category": "优",
    "primary": "NA",
    "pm10": "28",
    "pm2p5": "5",
    "no2": "3",
    "so2": "2",
    "co": "0.2",
    "o3": "76"
  },
  "station": [
    {
      "pubTime": "2021-02-16T14:00+08:00",
      "name": "密云镇",
      "id": "CNA3697",
      "aqi": "20",
      "level": "1",
      "category": "优",
      "primary": "NA",
      "pm10": "4",
      "pm2p5": "4",
      "no2": "4",
      "so2": "3",
      "co": "0.2",
      "o3": "63"
    },
  ],
  "refer": {
    "sources": [
      "cnemc"
    ],
    "license": [
      "QWeather Developers License"
    ]
  }
}
code 请参考状态码
updateTime 当前API的最近更新时间
fxLink 当前数据的响应式页面，便于嵌入网站或应用
now.pubTime 空气质量数据发布时间
now.aqi 空气质量指数
now.level 空气质量指数等级
now.category 空气质量指数级别
now.primary 空气质量的主要污染物，空气质量为优时，返回值为NA
now.pm10 PM10
now.pm2p5 PM2.5
now.no2 二氧化氮
now.so2 二氧化硫
now.co 一氧化碳
now.o3 臭氧
station.name 监测站名称
station.id 监测站ID
station.pubTime 空气质量数据发布时间
station.aqi 空气质量指数
station.level 空气质量指数等级
station.category 空气质量指数级别
station.primary 空气质量的主要污染物，空气质量为优时，返回值为NA
station.pm10 PM10
station.pm2p5 PM2.5
station.no2 二氧化氮
station.so2 二氧化硫
station.co 一氧化碳
station.o3 臭氧
refer.sources 原始数据来源，或数据源说明，可能为空
refer.license 数据许可或版权声明，可能为空
 * */
public class AQI {

    /**
     * code-----@SerializedName()注解的目的让JSON 字段和Java字段之间建立映射关系
     */
    @SerializedName("code")
    private String code;
    /**
     * updateTime
     */
    @SerializedName("updateTime")
    private String updateTime;
    /**
     * fxLink
     */
    @SerializedName("fxLink")
    private String fxLink;
    /**
     * now
     */
    @SerializedName("now")
    private NowDTO now;
    /**
     * refer
     */
    @SerializedName("refer")
    private ReferDTO refer;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getFxLink() {
        return fxLink;
    }

    public void setFxLink(String fxLink) {
        this.fxLink = fxLink;
    }

    public NowDTO getNow() {
        return now;
    }

    public void setNow(NowDTO now) {
        this.now = now;
    }

    public ReferDTO getRefer() {
        return refer;
    }

    public void setRefer(ReferDTO refer) {
        this.refer = refer;
    }

    public static class NowDTO {
        /**
         * pubTime
         */
        @SerializedName("pubTime")
        private String pubTime;
        /**
         * aqi
         */
        @SerializedName("aqi")
        private String aqi;
        /**
         * level
         */
        @SerializedName("level")
        private String level;
        /**
         * category
         */
        @SerializedName("category")
        private String category;
        /**
         * primary
         */
        @SerializedName("primary")
        private String primary;
        /**
         * pm10
         */
        @SerializedName("pm10")
        private String pm10;
        /**
         * pm2p5
         */
        @SerializedName("pm2p5")
        private String pm2p5;
        /**
         * no2
         */
        @SerializedName("no2")
        private String no2;
        /**
         * so2
         */
        @SerializedName("so2")
        private String so2;
        /**
         * co
         */
        @SerializedName("co")
        private String co;
        /**
         * o3
         */
        @SerializedName("o3")
        private String o3;

        public String getPubTime() {
            return pubTime;
        }

        public void setPubTime(String pubTime) {
            this.pubTime = pubTime;
        }

        public String getAqi() {
            return aqi;
        }

        public void setAqi(String aqi) {
            this.aqi = aqi;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getPrimary() {
            return primary;
        }

        public void setPrimary(String primary) {
            this.primary = primary;
        }

        public String getPm10() {
            return pm10;
        }

        public void setPm10(String pm10) {
            this.pm10 = pm10;
        }

        public String getPm2p5() {
            return pm2p5;
        }

        public void setPm2p5(String pm2p5) {
            this.pm2p5 = pm2p5;
        }

        public String getNo2() {
            return no2;
        }

        public void setNo2(String no2) {
            this.no2 = no2;
        }

        public String getSo2() {
            return so2;
        }

        public void setSo2(String so2) {
            this.so2 = so2;
        }

        public String getCo() {
            return co;
        }

        public void setCo(String co) {
            this.co = co;
        }

        public String getO3() {
            return o3;
        }

        public void setO3(String o3) {
            this.o3 = o3;
        }
    }

    public static class ReferDTO {
        /**
         * sources
         */
        @SerializedName("sources")
        private List<String> sources;
        /**
         * license
         */
        @SerializedName("license")
        private List<String> license;

        public List<String> getSources() {
            return sources;
        }

        public void setSources(List<String> sources) {
            this.sources = sources;
        }

        public List<String> getLicense() {
            return license;
        }

        public void setLicense(List<String> license) {
            this.license = license;
        }
    }
}
