package com.example.fitlifepro;

public class Reminder
{
    private String RemDate;
    private String RemMemo;

    // Constructor
    public Reminder(String remDate, String remMemo) {
        RemDate = remDate;
        RemMemo = remMemo;
    }

    // getter and setter for RemDate
    public String getRemDate() {
        return RemDate;
    }

    public void setRemDate(String remDate) {
        RemDate = remDate;
    }

    // getter and setter for RemMemo
    public String getRemMemo() {
        return RemMemo;
    }

    public void setRemMemo(String remMemo) {
        RemMemo = remMemo;
    }
}
