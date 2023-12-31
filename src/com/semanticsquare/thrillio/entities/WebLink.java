package com.semanticsquare.thrillio.entities;

import com.semanticsquare.thrillio.partner.Shareable;
import org.apache.commons.lang3.StringUtils;

public class WebLink extends Bookmark implements Shareable {
    private String url;
    private String host;
    private String htmlPage;
    private DownloadStatus downloadStatus =  DownloadStatus.NOT_ATTEMPTED;

    public String getHtmlPage() {
        return htmlPage;
    }

    public void setHtmlPage(String htmlPage) {
        this.htmlPage = htmlPage;
    }

    public enum DownloadStatus{
        NOT_ATTEMPTED,
        SUCCESS,
        FAILED,
        NOT_ELIGIBLE
    }
    public DownloadStatus getDownloadStatus(){
        return downloadStatus;
    }

    public void setDownloadStatus(DownloadStatus downloadStatus) {
        this.downloadStatus = downloadStatus;
    }
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    @Override
    public boolean isKidFriendlyEligible() {
        if(url.contains("porn") || getTitle().contains("porn") || host.contains("adult"))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "WebLink{" +
                "url='" + url + '\'' +
                ", host='" + host + '\'' + ", htmlpage='" + getHtmlPage() + "'"+
                '}';
    }

    @Override
    public String getItemData() {
        StringBuilder builder = new StringBuilder();
        builder.append("<item>");
        builder.append("<type>WebLinks</type>");
        builder.append("<title>").append(getTitle()).append("</title>");
        builder.append("<url>").append(getUrl()).append("</url>");
        builder.append("<host>").append(getHost()).append("</host>");
        builder.append("</item>");
        return builder.toString();
    }
}
