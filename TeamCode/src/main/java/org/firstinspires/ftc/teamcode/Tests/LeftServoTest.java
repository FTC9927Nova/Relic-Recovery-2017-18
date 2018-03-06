package org.firstinspires.ftc.teamcode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystems.JewelArm;

/**
 * Created by Sumanth on 12/15/17.
 */

@Disabled

@TeleOp(name= "leftServoTest", group  = "")
public class LeftServoTest extends OpMode {

    JewelArm arm;

    public void init(){

        arm = new JewelArm(hardwareMap);


    }

    @Override
    public void loop() {

        if(gamepad2.a)
            arm.arm2Mid();
        else if(gamepad2.b)
            arm.arm2Down();
        else if(gamepad2.y)
            arm.arm2Up();
        else{

        }

        if(gamepad1.a)
            arm.armMid();
        else if(gamepad1.b)
            arm.armDown();
        else if(gamepad1.y)
            arm.armDown();
        else
        { }
        telemetry.addData("servo", arm);


    }
}
