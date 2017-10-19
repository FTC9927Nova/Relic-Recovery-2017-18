package org.firstinspires.ftc.teamcode.Autons;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Ethan Pereira on 10/19/2017.
 */

public class Test extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        Servo jewl = hardwareMap.servo.get("jewl");
        if (opModeIsActive()){
            if (jewl.getPosition() == 1.0){
                jewl.setPosition(0.0);
            } else {

            }
        }
    }
}
