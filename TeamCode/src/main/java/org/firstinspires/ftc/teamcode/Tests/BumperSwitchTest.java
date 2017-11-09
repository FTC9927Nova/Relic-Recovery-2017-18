package org.firstinspires.ftc.teamcode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DigitalChannel;

/**
 * Created by therat0981 on 11/5/17.
 */

@TeleOp(name = "bumpTest")
public class BumperSwitchTest extends OpMode
{
    DigitalChannel digitalChannel;
    int x;

    @Override
    public void init() {
        digitalChannel = hardwareMap.digitalChannel.get("bumper");
    }

    public void loop()
    {
        boolean x = digitalChannel.getState();
        telemetry.addData("sensor", x);
        telemetry.update();
    }

    public void stop()
    {}

}
