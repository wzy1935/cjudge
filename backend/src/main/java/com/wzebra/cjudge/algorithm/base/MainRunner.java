package com.wzebra.cjudge.algorithm.base;

import lombok.Getter;
import lombok.Setter;
import org.codehaus.commons.compiler.CompileException;
import org.codehaus.janino.SimpleCompiler;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.*;

@Getter
@Setter
public class MainRunner {
    static final InputStream consoleIn = System.in;
    static final PrintStream consoleOut = System.out;

    String code = null;
    String input = null;

    String output = "";
    long timeCost = 0;
    String errorMessage = null;
    boolean isCompileError = false;


    public MainRunner(String code, String input) {
        this.code = code;
        this.input = (input == null ? "" : input);
    }

    public synchronized static void run(MainRunner runner) {
        runner.run();
    }

    public synchronized static void runWithTimeLimit(MainRunner runner, int timeLimit) {
        runner.runWithTimeLimit(timeLimit);
    }

    private synchronized void run() {
        errorMessage = null;

        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        OutputStream outputStream = new ByteArrayOutputStream();
        System.setIn(inputStream);
        System.setOut(new PrintStream(outputStream));

        try {
            SimpleCompiler simpleCompiler = new SimpleCompiler("Main.java",new StringReader(code));
            Class<?> clazz = simpleCompiler.getClassLoader().loadClass("Main");
            long time = System.currentTimeMillis();
            clazz.getMethod("main", String[].class).invoke(null, (Object) new String[0]);
            timeCost = System.currentTimeMillis() - time;
        } catch (CompileException e) {
            Throwable cause = e.getCause();
            errorMessage = "[Exception] " + (cause == null ? e.toString() : e.getCause().toString());
            isCompileError = true;
        } catch (Exception e) {
            Throwable cause = e.getCause();
            errorMessage = "[Exception] " + (cause == null ? e.toString() : e.getCause().toString());
        }

        output = outputStream.toString();
        try {
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.setIn(consoleIn);
            System.setOut(consoleOut);
        }
    }

    private void runWithTimeLimit(int timeLimit) {
        Thread t1 = new Thread() {
            @Override
            public void run() {
                MainRunner.this.run();
            }
        };

        Thread t2 = new Thread() {
            @Override
            public void run() {
                try {
                    int x = 0;
                    while (x < timeLimit) {
                        x += 20;
                        sleep(20);
                        if (!t1.isAlive()) {
                            return;
                        }
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (t1.isAlive()) {
                    t1.stop();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    timeCost = timeLimit;
                    output = "";
                    errorMessage = null;
                    System.setIn(consoleIn);
                    System.setOut(consoleOut);
                } else {
                    System.out.println("?");
                }
            }
        };

        t1.start();
        t2.start();
        try {
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
