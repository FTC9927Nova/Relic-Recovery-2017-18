package org.firstinspires.ftc.teamcode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Sumanth on 4/2/18.
 */


@TeleOp(name = "doubley intake")
@Disabled
public class DoubleIntakeTest extends OpMode
{
    DcMotor leftIntake;
    DcMotor rightIntake;
    DcMotor fourBar;

    CRServo lservoIntkae;
    CRServo rservoIntkae;

    DcMotor l1, l2, r1, r2;


    @Override
    public void init()
    {
        leftIntake = hardwareMap.dcMotor.get("leftIntake");
        rightIntake = hardwareMap.dcMotor.get("rightIntake");

        lservoIntkae = hardwareMap.crservo.get("lsintake");
        rservoIntkae = hardwareMap.crservo.get("rsintake");

        fourBar = hardwareMap.dcMotor.get("bar4");


        l1 = hardwareMap.dcMotor.get("l1");
        l2 = hardwareMap.dcMotor.get("l2");
        r1 = hardwareMap.dcMotor.get("r1");
        r2 = hardwareMap.dcMotor.get("r2");



    }

    @Override
    public void loop()
    {

        if(gamepad2.right_bumper||gamepad2.left_bumper)
        {
            lservoIntkae.setPower(gamepad2.left_trigger / 2.0);
            rservoIntkae.setPower(gamepad2.right_trigger / 2.0);
            leftIntake.setPower(gamepad2.left_trigger);
            rightIntake.setPower(gamepad2.right_trigger);
        }
        else {
            lservoIntkae.setPower(-gamepad2.left_trigger / 2.0);
            rservoIntkae.setPower(-gamepad2.right_trigger / 2.0);
            leftIntake.setPower(-gamepad2.left_trigger);
            rightIntake.setPower(-gamepad2.right_trigger);
        }

        fourBar.setPower(-gamepad2.left_stick_y/2.0);

        float yval = gamepad1.left_stick_y;
        float xval = gamepad1.right_stick_x;

        float lpwr = (float) Math.pow(((yval - xval)), 3);
        float rpwr = (float) Math.pow((yval + xval), 3);
//
//        float lpwr = gamepad1.left_stick_y;
//        float rpwr = gamepad1.right_stick_y;


//        turtle mode
        if (gamepad1.right_trigger != 0) {
            lpwr /= 3.0f;
            rpwr /= 3.0f;
        }

        else if(gamepad1.left_trigger != 0){
            lpwr /= 9.0f;
            rpwr /= 9.0f;
        }

        if(Math.abs(lpwr)<0.1)
            lpwr = 0;

        if(Math.abs(rpwr)<0.1)
            rpwr = 0;

//      Setting the power for dt
        l1.setPower(lpwr);
        l2.setPower(lpwr);
        r1.setPower(rpwr);
        r2.setPower(rpwr);

        telemetry.addData("leftEnc", leftIntake.getCurrentPosition());
        telemetry.addData("rightEnc", rightIntake.getCurrentPosition());
        telemetry.update();

    }

    @Override
    public void stop()
    {
        lservoIntkae.setPower(0);
        rservoIntkae.setPower(0);
        leftIntake.setPower(0);
        rightIntake.setPower(0);
    }
}
