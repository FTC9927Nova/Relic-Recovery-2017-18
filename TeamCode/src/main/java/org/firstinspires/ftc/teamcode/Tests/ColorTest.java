package org.firstinspires.ftc.teamcode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystems.JewelArm;
import org.firstinspires.ftc.teamcode.Subsystems.RelicMech;
import org.firstinspires.ftc.teamcode.Subsystems.Robot;
import org.firstinspires.ftc.teamcode.Util.Gyro;

/**
 * Created by Sumanth on 11/11/17.
 */


@TeleOp(name = "ColorTest")
@Disabled
public class ColorTest extends OpMode
{
    JewelArm j;
    @Override
    public void init()
    {

        j = new JewelArm(hardwareMap,true);

    }

    @Override
    public void loop()
    {
        telemetry.addData("",j.display());
        telemetry.update();
    }
}
