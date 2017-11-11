//package org.firstinspires.ftc.teamcode.Tests;
//
//import com.qualcomm.robotcore.eventloop.opmode.Disabled;
//import com.qualcomm.robotcore.eventloop.opmode.OpMode;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//import com.qualcomm.robotcore.hardware.DigitalChannel;
//
//import org.firstinspires.ftc.teamcode.Subsystems.Robot;
//import org.firstinspires.ftc.teamcode.Util.Gyro;
//import org.firstinspires.ftc.teamcode.Util.Sound;
//
///**
// * Created by Sumanth on 11/5/17.
// */
//
//@TeleOp(name = "bump")
//@Disabled
//public class BumperTest extends OpMode
//{
//Robot robot = new Robot();
//Gyro gyro;
//Sound sound = new Sound();
//    @Override
//
//    public void init() {
//
//        robot.init(hardwareMap, gyro);
//    }
//
//    @Override
//    public void loop() {
//        if (!robot.bumper.getState()){
//            sound.startSound();
//
//        } else {
//            sound.stopSound();
//            telemetry.addData("Stop Sound", "sound stopped");
//        }
//
//        telemetry.addData("state", String.valueOf(robot.bumper.getState()));
//        telemetry.update();
//    }
//
//    @Override
//    public void stop() {
//        super.stop();
//    }
//}
