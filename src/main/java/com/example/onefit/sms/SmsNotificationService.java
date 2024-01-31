package com.example.onefit.sms;

public interface SmsNotificationService {
    void sendNotification(String phoneNumber, String message);
}
