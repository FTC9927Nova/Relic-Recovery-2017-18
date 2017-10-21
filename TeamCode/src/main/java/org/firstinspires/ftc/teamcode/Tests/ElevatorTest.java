package org.firstinspires.ftc.teamcode.Tests;

import android.provider.Settings;
import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Subsystems.Robot;
import org.firstinspires.ftc.teamcode.Util.Gyro;

/**
 * Created by Ethan Pereira on 10/19/2017.
 */
@TeleOp(name = "ElevatorTest")
public class ElevatorTest extends OpMode {

    Robot robot = new Robot();
    Gyro gyro = new Gyro();
    @Override

    public void init() {

        gyro.initGyro(hardwareMap);
        robot.init(hardwareMap, gyro);

    }


    @Override
    public void loop() {
//        telemetry.addData("Pos", claw.getPosition());
//        telemetry.update();
        if (gamepad1.a){
            robot.elevator.moveLevel(1);
        } else if (gamepad1.b){
            robot.elevator.moveLevel(2);
        } else if (gamepad1.y){
            robot.elevator.moveLevel(3);
        } else {
            robot.elevator.setPower(0.0);
        }

        if (gamepad1.right_bumper){
            robot.claw.close();
        } else if (gamepad1.left_bumper){
            robot.claw.open();
        }



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
    }
}
