package org.firstinspires.ftc.teamcode.Autons;

import org.firstinspires.ftc.teamcode.*;
import org.firstinspires.ftc.teamcode.Subsystems.Robot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by Sumanth on 11/10/17.
 */

@TeleOp(name = "potTest")
public class PotTest extends LinearOpMode {
    Robot robot = new Robot();

    @Override
    public void runOpMode() throws InterruptedException
    {
        robot.init(hardwareMap,this,null);
        waitForStart();
        while (opModeIsActive())
        {
            robot.potentiometer.getInput();

            telemetry.addData("angle",robot.potentiometer.getAngle());
            telemetry.update();
        }


    }
}
