package org.firstinspires.ftc.teamcode.Autons;

import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Ethan Pereira on 10/19/2017.
 */
@TeleOp(name = "JewlArmTest")
public class Test extends OpMode {
    Servo jewl;
    @Override
    public void init() {
        jewl = hardwareMap.servo.get("jewl");

    }

    @Override
    public void loop() {
        telemetry.addData("Pos", jewl.getPosition());
        telemetry.addData("Direction", jewl.getDirection());
        Log.i("DataLogs", String.valueOf(jewl.getPosition()));
    }
}
