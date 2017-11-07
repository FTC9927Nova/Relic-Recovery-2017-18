package org.firstinspires.ftc.teamcode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystems.Wheels;

/**
 * Created by Sumanth on 11/7/17.
 */

@TeleOp(name = "wheel")
public class wheelsTest extends OpMode
{
    Wheels wheel;

    @Override
    public void init()
    {
        wheel = new Wheels(this.hardwareMap);
    }

    @Override
    public void loop()
    {
        wheel.intakeLeft();
        wheel.intakeRight();

    }

    @Override
    public void stop()
    {
        wheel.stopLeft();
        wheel.stopRight();

    }
}
