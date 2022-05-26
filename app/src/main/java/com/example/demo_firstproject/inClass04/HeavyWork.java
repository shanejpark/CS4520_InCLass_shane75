package com.example.demo_firstproject.inClass04;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class HeavyWork implements Runnable {
    public final static int STATUS_START = 0x001;
    public final static int STATUS_PROGRESS = 0x002;
    public final static int STATUS_END = 0x003;
    public final static String KEY_PROGRESS = "KEY_PROGRESS";
    public final static String MIN = "MIN";
    public final static String MAX = "MAX";
    public final static String AVG = "AVG";
    static final int COUNT = 900000;
    private final int n;
    Handler messageQueue;

    public HeavyWork(int n, Handler messageQueue) {
        this.n = n;
        this.messageQueue = messageQueue;
    }

    private ArrayList<Double> getArrayNumbers(int n) {
        ArrayList<Double> returnArray = new ArrayList<>();


        for (int i = 0; i < n; i++) {
            returnArray.add(getNumber(i));
        }

        return returnArray;
    }

    private double getNumber(int index) {
        double num = 0;
        Random rand = new Random();
        int percent = 0;
        for (int i = 0; i < COUNT; i++) {
            num = num + rand.nextDouble();
            double decimal = ((double) (i + (index * COUNT)) / (double) (n * COUNT)) * 100;
            int newPercent = (int) decimal;
            if (newPercent > percent) {
                Message progressMessage = new Message();
                percent = newPercent;
                progressMessage.what = STATUS_PROGRESS;
                Bundle bundle = new Bundle();
                bundle.putInt(KEY_PROGRESS, percent);
                progressMessage.setData(bundle);
                messageQueue.sendMessage(progressMessage);
            }

        }
        return num / ((double) COUNT);
    }

    @Override
    public void run() {
        Message startMessage = new Message();
        startMessage.what = STATUS_START;
        messageQueue.sendMessage(startMessage);

        ArrayList<Double> arrayNumbers = getArrayNumbers(n);
        Message endMessage = new Message();
        endMessage.what = STATUS_END;
        Bundle bundle = new Bundle();
        bundle.putDouble(MIN, Collections.min(arrayNumbers));
        bundle.putDouble(MAX, Collections.max(arrayNumbers));
        bundle.putDouble(AVG, arrayNumbers.stream()
                .mapToDouble(d -> d)
                .average()
                .orElse(0.0));
        endMessage.setData(bundle);
        messageQueue.sendMessage(endMessage);
    }
}