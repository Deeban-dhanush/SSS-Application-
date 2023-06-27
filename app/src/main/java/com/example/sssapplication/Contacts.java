package com.example.sssapplication;

public class Contacts {

    String msgNumberOne,msgNumberTwo,msgNumberThree,msgNumberFour,msgNumberFive,callNumberOne,altMessage;

    public Contacts() {
    }

    public Contacts(String msgNumberOne, String msgNumberTwo, String msgNumberThree, String msgNumberFour, String msgNumberFive, String callNumberOne, String altMessage) {
        this.msgNumberOne = msgNumberOne;
        this.msgNumberTwo = msgNumberTwo;
        this.msgNumberThree = msgNumberThree;
        this.msgNumberFour = msgNumberFour;
        this.msgNumberFive = msgNumberFive;
        this.callNumberOne = callNumberOne;
        this.altMessage = altMessage;
    }

    public String getMsgNumberOne() {
        return msgNumberOne;
    }

    public void setMsgNumberOne(String msgNumberOne) {
        this.msgNumberOne = msgNumberOne;
    }

    public String getMsgNumberTwo() {
        return msgNumberTwo;
    }

    public void setMsgNumberTwo(String msgNumberTwo) {
        this.msgNumberTwo = msgNumberTwo;
    }

    public String getMsgNumberThree() {
        return msgNumberThree;
    }

    public void setMsgNumberThree(String msgNumberThree) {
        this.msgNumberThree = msgNumberThree;
    }

    public String getMsgNumberFour() {
        return msgNumberFour;
    }

    public void setMsgNumberFour(String msgNumberFour) {
        this.msgNumberFour = msgNumberFour;
    }

    public String getMsgNumberFive() {
        return msgNumberFive;
    }

    public void setMsgNumberFive(String msgNumberFive) {
        this.msgNumberFive = msgNumberFive;
    }

    public String getCallNumberOne() {
        return callNumberOne;
    }

    public void setCallNumberOne(String callNumberOne) {
        this.callNumberOne = callNumberOne;
    }

    public String getAltMessage() {
        return altMessage;
    }

    public void setAltMessage(String altMessage) {
        this.altMessage = altMessage;
    }
}
