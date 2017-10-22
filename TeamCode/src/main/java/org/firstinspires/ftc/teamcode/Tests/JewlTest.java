package org.firstinspires.ftc.teamcode.Tests;

import android.provider.Settings;
import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by Ethan Pereira on 10/19/2017.
 */
@TeleOp(name = "JewlTest")
public class JewlTest extends OpMode {
    Servo jewl;
    double pos = 0;
    @Override

    public void init() {
        jewl = hardwareMap.get(Servo.class, "jewl");

//                hardwareMap.servo.get("jewl");
    }


    @Override
    public void loop() {

        telemetry.addData("servo pos ", jewl.getPosition());
        telemetry.addData("pos", pos);

        if(gamepad1.a)
            jewl.setPosition(0.17);
        else if(gamepad1.b)
            jewl.setPosition(0.3);
        else if(gamepad1.y)
            jewl.setPosition(0.82);

        telemetry.update();

//        Log.i("DataLogs", String.valueOf(jewl.getPosition()));
    }
}
