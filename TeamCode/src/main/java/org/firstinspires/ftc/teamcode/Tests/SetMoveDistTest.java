package org.firstinspires.ftc.teamcode.Tests;

import android.provider.Settings;
import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Subsystems.Robot;
import org.firstinspires.ftc.teamcode.Util.Gyro;

/**
 * Created by Ethan Pereira on 10/19/2017.
 */
@Autonomous(name = "MoveDist Test")
public class SetMoveDistTest extends LinearOpMode {

    Robot robot = new Robot();
    Gyro gyro = new Gyro();


    @Override
    public void runOpMode() throws InterruptedException {

        gyro.initGyro(hardwareMap);
        robot.init(hardwareMap, this, gyro);


        waitForStart();
        if (opModeIsActive()) {

            robot.driveTrain.setMoveDist(30);


        }


    }

}