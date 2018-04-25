package org.firstinspires.ftc.teamcode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.teamcode.Util.AutoTransitioner;
import org.firstinspires.ftc.teamcode.Util.VisionUtil;

/**
 * Created by Sumanth on 12/13/17.
 */

@TeleOp(name = "vision")
@Disabled
public class Vision extends LinearOpMode {

    VisionUtil vision = new VisionUtil(this);

    RelicRecoveryVuMark reading;

    @Override
    public void runOpMode() throws InterruptedException {

        AutoTransitioner.transitionOnStop(this, "teleop" +
                "");
        reading = vision.readGraph(hardwareMap);

        waitForStart();
        while(opModeIsActive()) {

            telemetry.addData("graph", reading);
            telemetry.update();
        }


    }
}
