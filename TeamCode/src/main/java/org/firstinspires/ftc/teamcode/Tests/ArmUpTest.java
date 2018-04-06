//package org.firstinspires.ftc.teamcode.Tests;
//
//import android.util.Log;
//
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.qualcomm.robotcore.eventloop.opmode.Disabled;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//
//import org.firstinspires.ftc.teamcode.Subsystems.Robot;
//import org.firstinspires.ftc.teamcode.Util.Gyro;
//
///**
// * Created by therat0981 on 11/30/17.
// */
//@Disabled
//@Autonomous(name = "        jewlCheck = hardwareMap.colorSensor.get(\"color\");\n")
//public class ArmUpTest extends LinearOpMode
//{
//    Robot robot = new Robot();
//    Gyro gyro = new Gyro();
//
//
//    @Override
//    public void runOpMode() throws InterruptedException
//    {
//        gyro.initGyro(hardwareMap);
//        robot.init(hardwareMap,this,gyro);
//        Thread armThread = new keepArmUp();
//        waitForStart();
//        armThread.start();
//
//        try {
//            if(opModeIsActive())
//            {
//
//                robot.bar4.setMoveAngle(robot.bar4.setHeight(10));
//                robot.bar4.shouldStayTrue();
//                robot.driveTrain.setMoveDist(10);
//            }
//        }catch (Exception e)
//        {
//            Log.e("error",e.getMessage());
//        }
//
//        armThread.interrupt();
//
//
//
//
//    }
//
//    private class keepArmUp extends Thread
//    {
//
//        public keepArmUp()
//        {
//
//        }
//
//        @Override
//        public void run() {
//            try {
//                while(!isInterrupted())
//                {
//                    robot.bar4.setTargetAngle();
//                    robot.bar4.getCurrentAngle();
//                    robot.bar4.setMoveAngle(robot.bar4.getTargetAngle());
//                    idle();
//                }
//            }
//            catch (Exception e) {}
//
//        }
//    }
//
//}
