//package org.firstinspires.ftc.robotcontroller.internal;
//
//import android.app.Activity;
//import android.util.Log;
//
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.OutputStreamWriter;
//import java.util.concurrent.Executors;
//import java.util.concurrent.ScheduledExecutorService;
//import java.util.concurrent.TimeUnit;
//
///**
// * Created by Ethan Pereira on 10/7/2017.
// */
//
//public class DataLogging extends Activity {
//    int data_block = 100;
//
//    public void dataLog(){
//        Runnable runCode = new Runnable() {
//
//            public void run() {
//                //Enter code that you want to run every second below
//                String Message = "Hello World";
//                try {
//                    FileOutputStream fileOutputStream = openFileOutput("text.txt", MODE_APPEND);
//                    fileOutputStream.write(Message.getBytes());
//                    fileOutputStream.close();
////                    message.setText("");
//                    OutputStreamWriter osw = new OutputStreamWriter(fileOutputStream);
//                    try {
//                        osw.write(Message);
//                        osw.flush();
//                        osw.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//                try {
//                    String getMessage;
//                    FileInputStream fileInputStream = openFileInput("text.txt");
//                    InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
////                      BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
////                      StringBuffer stringBuffer = new StringBuffer();
////                      while((getMessage = bufferedReader.readLine()) != null){
////                        stringBuffer.append(getMessage + "/n");
////                      }
////                      message.setText(stringBuffer.toString());
////                      Log.i("Message", stringBuffer.toString());
//
//                    char[] data = new char[data_block];
//                    String final_data="";
//                    int size;
//                    try {
//                        while ((size = inputStreamReader.read(data))>0){
//                            String read_data = String.copyValueOf(data, 0, size);
//                            final_data +=read_data;
//                            data = new char[data_block];
//                        }
//                        Log.i("Message: ", final_data);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                }
//            }
//        };
//
//        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
//        executor.scheduleAtFixedRate(runCode, 0, 5000, TimeUnit.MILLISECONDS);
//
//    }
//}
