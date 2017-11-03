package org.firstinspires.ftc.teamcode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystems.Robot;
import org.firstinspires.ftc.teamcode.Util.Gyro;
import org.firstinspires.ftc.teamcode.Util.*;

/**
 * Created by therat0981 on 10/19/17.
 */

@Autonomous(name = "VuMark Method Test")

public class TestVISIONSJOJF extends LinearOpMode
{


    @Override
    public void runOpMode() throws InterruptedException
    {

        VumarkDetect vision = new VumarkDetect(hardwareMap);


        vision.initVufira();

        waitForStart();
        while(opModeIsActive())
        {
            telemetry.addData("graph", (vision.getLocation()));
            telemetry.update();
        }
    }
}
