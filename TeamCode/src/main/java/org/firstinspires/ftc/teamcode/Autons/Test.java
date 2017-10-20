package org.firstinspires.ftc.teamcode.Autons;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Ethan Pereira on 10/19/2017.
 */
@TeleOp(name = "JewlArmTest")
public class Test extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        Servo jewl = hardwareMap.servo.get("jewl");
        waitForStart();
        while (opModeIsActive()){
            telemetry.addData("Pos", jewl.getPosition());
            telemetry.addData("Direction", jewl.getDirection());
            telemetry.update();
        }
    }
}
