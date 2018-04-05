package org.firstinspires.ftc.teamcode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Sumanth on 4/2/18.
 */


@TeleOp(name = "doubley intake")
public class DoubleIntakeTest extends OpMode
{
    DcMotor lmotorIntake;
    DcMotor rmotorIntake;

    CRServo lservoIntkae;
    CRServo rservoIntkae;


    @Override
    public void init()
    {
//        lmotorIntake = hardwareMap.dcMotor.get("lmintake");
//        rmotorIntake = hardwareMap.dcMotor.get("rmintake");

        lservoIntkae = hardwareMap.crservo.get("lsintake");
        rservoIntkae = hardwareMap.crservo.get("rsintake");

    }

    @Override
    public void loop()
    {


//        if(gamepad1.left_bumper||gamepad1.right_bumper)
//        {
//            lmotorIntake.setPower(gamepad1.left_trigger);
//            rmotorIntake.setPower(-gamepad1.right_trigger);
//        }
//        else {
//            lmotorIntake.setPower(-gamepad1.left_trigger);
//            rmotorIntake.setPower(gamepad1.right_trigger);
//        }
        lservoIntkae.setPower(gamepad1.left_stick_y/2);
        rservoIntkae.setPower(-gamepad1.right_stick_y/2);
    }

    @Override
    public void stop()
    {
        lservoIntkae.setPower(0);
        rservoIntkae.setPower(0);
    }
}
