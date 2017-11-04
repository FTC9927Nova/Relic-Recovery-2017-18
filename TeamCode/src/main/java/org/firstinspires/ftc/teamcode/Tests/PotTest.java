package org.firstinspires.ftc.teamcode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Util.Pentiometer;

/**
 * Created by Sumanth on 11/3/17.
 */


@TeleOp(name = "Potentiometer Test")
public class PotTest extends LinearOpMode
{
    Pentiometer pet;

    @Override
    public void runOpMode() throws InterruptedException {
        pet = new Pentiometer(hardwareMap);
        waitForStart();
        while (opModeIsActive())
        {
            pet.getInput();
            telemetry.addData("Pot",pet.display());
            telemetry.update();
        }
    }
}
