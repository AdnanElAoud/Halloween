package com.app.mob.halloween.Notification;



/**
 * Created by adnan on 3/20/2018.
 */

public class Sender {
    public String to;
    public Notification notification;

    public Sender() {
    }

    public Sender(String to, Notification notification) {
        this.to = to;
        this.notification = notification;
    }


    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }
}
