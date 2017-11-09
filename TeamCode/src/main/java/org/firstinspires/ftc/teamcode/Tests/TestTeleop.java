//package org.firstinspires.ftc.teamcode.Tests;
//
//
//import com.qualcomm.robotcore.eventloop.opmode.Disabled;
//import com.qualcomm.robotcore.eventloop.opmode.OpMode;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//
//import org.firstinspires.ftc.teamcode.Subsystems.Robot;
//import org.firstinspires.ftc.teamcode.Util.Gyro;
//
///**
// * Created by therat0981 on 10/1/17.
// */
//@TeleOp(name = "TankDrive Teleop")
//@Disabled
//public class TestTeleop extends OpMode
//{
//    Robot robot = new Robot();
//    Gyro gyro = new Gyro();
//    @Override
//    public void init()
//    {
//        gyro.initGyro(hardwareMap);
//        robot.init(hardwareMap, gyro);
//    }
//
//    public void loop()
//    {
//        //tank drive
//        float Lyval = -gamepad1.left_stick_y;
//        float Ryval = -gamepad1.right_stick_y;
//
//        float lpwr = (float) Math.pow(((Lyval)), 3);
//        float rpwr = (float) Math.pow((Ryval), 3);
//
//        if(gamepad1.left_trigger!=0 ||
//                gamepad1.right_trigger!=0)
//        {
//            lpwr/=3.0f;
//            rpwr/=3.0f;
//        }
//
//        robot.driveTrain.setRightPower(rpwr);
//        robot.driveTrain.setLeftPower(lpwr);
//
//        //Operator
//        if(gamepad2.y){
//
//            robot.elevator.setPower(-0.5);
//
//        }
//
//        else if(gamepad2.x){
//
//            robot.elevator.setPower(0.5);
//
//        }
//        else{
//
//            robot.elevator.setPower(0.0);
//
//        }
//
//        if (gamepad2.left_bumper){
//            robot.claw.open();
//        } else if (gamepad2.right_bumper){
//            robot.claw.close();
//        }
//
//
//    }
//
//
//
//    public void stop()
//    {
//
//    }
//
//
//}
