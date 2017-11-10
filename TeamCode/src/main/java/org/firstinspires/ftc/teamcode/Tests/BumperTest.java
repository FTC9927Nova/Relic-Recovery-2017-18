package org.firstinspires.ftc.teamcode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DigitalChannel;

import org.firstinspires.ftc.teamcode.Util.Sound;

/**
 * Created by Sumanth on 11/5/17.
 */

@TeleOp(name = "bump")
public class BumperTest extends OpMode
{
    DigitalChannel dt;
    Sound sound = new Sound();
    @Override

    public void init() {
        dt = hardwareMap.digitalChannel.get("bumper");
    }

    @Override
    public void loop() {
        if (dt.getState()){
            sound.startSound();

        } else {
            sound.stopSound();
            telemetry.addData("Stop Sound", "sound stopped");
        }

        telemetry.addData("state", String.valueOf(dt.getState()));
        telemetry.update();
    }

    @Override
    public void stop() {
        super.stop();
    }
}
