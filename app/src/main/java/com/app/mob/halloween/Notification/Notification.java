package com.app.mob.halloween.Notification;

/**
 * Created by adnan on 3/20/2018.
 */

public class Notification {
    public String body;
    public String title;
    public String factId;
    public String quoteId;

    public Notification() {
    }

    public Notification(String body, String title, String factId, String quoteId) {
        this.body = body;
        this.title = title;
        this.factId = factId;
        this.quoteId = quoteId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFactId() {
        return factId;
    }

    public void setFactId(String factId) {
        this.factId = factId;
    }

    public String getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(String quoteId) {
        this.quoteId = quoteId;
    }
}
