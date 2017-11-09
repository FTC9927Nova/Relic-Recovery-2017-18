package org.firstinspires.ftc.teamcode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DigitalChannel;

/**
 * Created by Sumanth on 11/5/17.
 */

@TeleOp(name = "bump")
public class BumperTest extends OpMode
{
    DigitalChannel dt;
    boolean state;
    @Override
    public void init() {
        dt = hardwareMap.digitalChannel.get("bumper");
    }

    @Override
    public void loop() {
        state = dt.getState();
        telemetry.addData("state", state);
        telemetry.update();
    }

    @Override
    public void stop() {
        super.stop();
    }
}
