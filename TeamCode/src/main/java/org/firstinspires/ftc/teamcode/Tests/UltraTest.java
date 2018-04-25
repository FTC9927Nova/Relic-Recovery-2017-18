package org.firstinspires.ftc.teamcode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystems.Robot;
import org.firstinspires.ftc.teamcode.Util.Gyro;

/**
 * Created by Sumanth on 2/23/18.
 */
@TeleOp(name = "ultraTest")
@Disabled

public class UltraTest extends LinearOpMode
{
    Robot robot = new Robot();
    Gyro gyro = new Gyro();

    @Override
    public void runOpMode() throws InterruptedException
    {
        gyro.initGyro(this.hardwareMap);
        robot.init(this.hardwareMap,this,gyro);
        waitForStart();
        while(opModeIsActive())
        {
            telemetry.addData("hasGlyph",robot.range.isGlyph());
            telemetry.addData("dist",robot.range.getDist());
            telemetry.update();
        }
    }
}
