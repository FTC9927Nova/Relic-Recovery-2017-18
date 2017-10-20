package org.firstinspires.ftc.teamcode.Tests;

import android.provider.Settings;
import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Ethan Pereira on 10/19/2017.
 */
@TeleOp(name = "JewlTest")
public class Test extends OpMode {
    Servo jewl;
    @Override

    public void init() {
        jewl = hardwareMap.get(Servo.class, "jewl");
//                hardwareMap.servo.get("jewl");
    }


    @Override
    public void loop() {
        telemetry.addData("Pos", jewl.getPosition());
        telemetry.update();
        jewl.setPosition(1.0);

//        Log.i("DataLogs", String.valueOf(jewl.getPosition()));
    }
}
