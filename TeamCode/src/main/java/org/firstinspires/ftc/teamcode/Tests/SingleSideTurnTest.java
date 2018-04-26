//package org.firstinspires.ftc.teamcode.Tests;
//
//
//import com.qualcomm.robotcore.eventloop.opmode.Disabled;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//
//import org.firstinspires.ftc.teamcode.Subsystems.DriveTrain;
//import org.firstinspires.ftc.teamcode.Subsystems.Robot;
//import org.firstinspires.ftc.teamcode.Util.Gyro;
//
///**
// * Created by therat0981 on 11/25/17.
// */
//@Disabled
//public class SingleSideTurnTest extends LinearOpMode
//{
//    Robot robot = new Robot();
//    Gyro gyro = new Gyro();
//    @Override
//    public void runOpMode() throws InterruptedException
//    {
//        gyro.initGyro(hardwareMap);
//        robot.init(hardwareMap,this,gyro);
//        waitForStart();
//        if(opModeIsActive())
//        {
//            robot.driveTrain.singleSideRotateDeg(DriveTrain.Side.LEFT_SIDE,90);
//        }
//    }
//}
