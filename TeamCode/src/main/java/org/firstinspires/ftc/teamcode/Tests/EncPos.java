package org.firstinspires.ftc.teamcode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Ethan Pereira on 12/11/2017.
 */
@TeleOp(name = "EncPos")
public class EncPos extends OpMode{
    DcMotor r1;

    @Override
    public void init() {
        r1 = hardwareMap.dcMotor.get("r1");
    }

    @Override
    public void loop() {
        telemetry.addData("Motor Pos", r1.getCurrentPosition());
        telemetry.update();
    }
}
