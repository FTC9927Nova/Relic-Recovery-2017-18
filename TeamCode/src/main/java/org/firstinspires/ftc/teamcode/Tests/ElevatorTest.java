package org.firstinspires.ftc.teamcode.Tests;

import android.provider.Settings;
import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Subsystems.Robot;
import org.firstinspires.ftc.teamcode.Util.Gyro;
import org.firstinspires.ftc.teamcode.Util.PIDLoop;
import org.firstinspires.ftc.teamcode.Util.RobotConstants;

/**
 * Created by Ethan Pereira on 10/19/2017.
 */
@TeleOp(name = "ElevatorTest")
public class ElevatorTest extends OpMode {

    Robot robot = new Robot();
    Gyro gyro = new Gyro();
    int x;
    int y;
    @Override

    public void init() {

        gyro.initGyro(hardwareMap);
        robot.init(hardwareMap, gyro);

    }


    @Override
    public void loop() {
//        telemetry.addData("Pos", claw.getPosition());
//        telemetry.update();
//        if (gamepad1.a){
//            robot.elevator.moveLevel(1);
//        } else if (gamepad1.b){
//            robot.elevator.moveLevel(2);
//        } else if (gamepad1.y){
//            robot.elevator.moveLevel(3);
//        } else {
//            robot.elevator.setPower(0.0);
//        }

        //telemetry.addData("Pos", robot.claw.showPos());
        telemetry.addData("ElevatorEnc", robot.elevator.getEnc());
        telemetry.update();
        if(gamepad2.y){

            robot.elevator.setPower(-0.2);

        }

        else if(gamepad2.x){

            robot.elevator.setPower(0.2);

        }

        else {
//            if (Math.abs(x - robot.elevator.getEnc()) > robotConstants.getELEVATOR_TICKS_PER_INCH()) {
//                robot.elevator.setMoveDist((x - robot.elevator.getEnc()) / robotConstants.getELEVATOR_TICKS_PER_INCH());
//            }
//            else
//            {
//                robot.
//                robot.elevator.setPower(0);
//            }
            robot.elevator.setPower(0);
        }


        if (gamepad1.right_bumper){
            robot.claw.open();
//            robot.elevator.resetEnc();

        } else if (gamepad1.left_bumper){
            robot.claw.close();
        }

//        if(gamepad1.dpad_up){
//
//            robot.elevator.moveLevel(robot.elevator.getCurrentLevel() + 1);
//
//        }
//
//        else if(gamepad1.dpad_down){
//
//            robot.elevator.moveLevel(robot.elevator.getCurrentLevel() - 1);
//
//        }
//        else
//        {
//            robot.elevator.getPastPos();
//            robot.elevator.stayInPlace();
//            telemetry.addData(robot.elevator.display(),"");
//
//        }



//
//        if(gamepad1.right_bumper){
//
//            claw.setPosition(0.35);
//
//        }
//
//        else if(gamepad1.left_bumper){
//
//            claw.setPosition(0.0);
//
//        }
//
//        if(gamepad1.y){
//
//            elevator.setPower(0.5);
//
//        }
//
//        else if(gamepad1.x){
//
//            elevator.setPower(-0.5);
//
//        }
//        else{
//
//            elevator.setPower(0);
//
//        }
//
////        Log.i("DataLogs", String.valueOf(jewl.getPosition()));
        //Driver Code
        float yval = gamepad1.left_stick_y;
        float xval = gamepad1.right_stick_x;


        float lpwr = (float) Math.pow((yval + xval), 3);
        float rpwr = (float) Math.pow((yval - xval), 3);


//        turtle mode
        if (gamepad1.right_trigger != 0 || gamepad1.left_trigger != 0)
        {
            lpwr = lpwr/2.0f;
            rpwr = rpwr/2.0f;
        }

        robot.driveTrain.setLeftPower(rpwr);
        robot.driveTrain.setRightPower(lpwr);
    }
}
