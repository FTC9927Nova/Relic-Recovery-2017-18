package org.firstinspires.ftc.teamcode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystems.JewelArm;

/**
 * Created by Sumanth on 12/15/17.
 */

@TeleOp(name= "leftServoTest", group  = "")
@Disabled
public class LeftServoTest extends OpMode {

    JewelArm arm;

    public void init(){

        arm = new JewelArm(hardwareMap);


    }

    @Override
    public void loop() {

        telemetry.addData("servo", arm.getPos2());
        telemetry.update();

    }
}
