package org.firstinspires.ftc.teamcode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Util.Bumper;
import org.firstinspires.ftc.teamcode.Util.Potentiometer;

/**
 * Created by Sumanth on 11/3/17.
 */


@TeleOp(name = "Potentiometer Test")
@Disabled
public class PotTest extends LinearOpMode
{
    Bumper bump;

    @Override
    public void runOpMode() throws InterruptedException {
        bump = new Bumper(hardwareMap);
        waitForStart();
        while (opModeIsActive())
        {

            telemetry.addData("Pot", bump.isPressed());
            telemetry.update();
        }
    }
}
