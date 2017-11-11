package org.firstinspires.ftc.teamcode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Subsystems.RelicMech;

/**
 * Created by Ethan Pereira on 11/10/2017.
 */

public class ServoTest extends LinearOpMode {
    RelicMech relicMech = new RelicMech(hardwareMap);
    int pos = 0;
    int extenderPos = 0;
    @Override
    public void runOpMode() throws InterruptedException {
        if (gamepad1.dpad_up){
            pos += 0.15;
            relicMech.claw.setPosition(pos);
        }else if (gamepad1.dpad_down){
            pos -= 0.15;
            relicMech.claw.setPosition(pos);
        } else {
            relicMech.claw.setPosition(pos);
        }

        if (gamepad2.dpad_up){
            extenderPos += 0.15;
            relicMech.claw.setPosition(extenderPos);
        }else if (gamepad2.dpad_down){
            extenderPos -= 0.15;
            relicMech.claw.setPosition(extenderPos);
        } else {
            relicMech.claw.setPosition(extenderPos);
        }


    }
}
