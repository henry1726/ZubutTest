package com.example.zubuttest.sys.providers;

import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AppExecutors {

    private static final int THREAD_COUNT = 1;

    private final ExecutorService diskIO;

    public AppExecutors(ExecutorService diskIO) {
        this.diskIO = diskIO;
    }

    public AppExecutors() {
        this(Executors.newSingleThreadExecutor());
    }

    public ExecutorService diskIO() {
        return diskIO;
    }

}
