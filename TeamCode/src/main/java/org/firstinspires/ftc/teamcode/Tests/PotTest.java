package org.firstinspires.ftc.teamcode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Util.Potentiometer;

/**
 * Created by Sumanth on 11/3/17.
 */


@TeleOp(name = "Potentiometer Test")
public class PotTest extends LinearOpMode
{
    Potentiometer potentiometer;

    @Override
    public void runOpMode() throws InterruptedException {
        potentiometer = new Potentiometer(hardwareMap);
        waitForStart();
        while (opModeIsActive())
        {
            potentiometer.getInput();
            telemetry.addData("Pot", potentiometer.display());
            telemetry.update();
        }
    }
}
