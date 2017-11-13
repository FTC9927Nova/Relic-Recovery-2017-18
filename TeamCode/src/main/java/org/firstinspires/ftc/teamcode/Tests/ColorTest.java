package org.firstinspires.ftc.teamcode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystems.Robot;
import org.firstinspires.ftc.teamcode.Util.Gyro;

/**
 * Created by Sumanth on 11/11/17.
 */
@TeleOp(name = "asdf")
public class ColorTest extends OpMode
{
    Robot robot = new Robot();
    Gyro gyro = new Gyro();


    @Override
    public void init()
    {
        robot.init(this.hardwareMap,gyro);
    }

    @Override
    public void loop()
    {
        telemetry.addData("color: ", robot.jewelArm.display());
        telemetry.update();
    }
}
