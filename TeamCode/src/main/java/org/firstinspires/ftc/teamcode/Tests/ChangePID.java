package org.firstinspires.ftc.teamcode.Tests;

import android.content.Context;
import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorControllerEx;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.PIDCoefficients;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;



/**
 * Created by tom on 9/26/17.
 * This assumes that you are using a REV Robotics Expansion Hub
 * as your DC motor controller. This op mode uses the extended/enhanced
 * PID-related functions of the DcMotorControllerEx class.  
 * The REV Robotics Expansion Hub supports the extended motor controller
 * functions, but other controllers (such as the Modern Robotics and 
 * Hitechnic DC Motor Controllers) do not.
 */

@TeleOp(name="PID Teleop Test ", group = "Examples")
@Disabled
public class ChangePID extends LinearOpMode {

    // our DC motor.
    DcMotor motorLeft1;
    DcMotor motorRight1;
    DcMotor motorLeft2;
    DcMotor motorRight2;

    //17.5 , 1, 1
    public static final double L1_P = 11.375;
    public static final double L1_I = 0.8;//0.8
    public static final double L1_D = 1.5;//1.5
    public static final double L2_P = 11.375;
    public static final double L2_I = 0.8;//1;
    public static final double L2_D = 1.5;//1;
    public static final double R1_P = 11.375;
    public static final double R1_I = 0.8;//1;
    public static final double R1_D = 1.5;//1;
    public static final double R2_P = 11.375;
    public static final double R2_I = 0.8;//1;
    public static final double R2_D = 1.5;


    public void runOpMode() {
        // get reference to DC motor.
        motorLeft1 = hardwareMap.get(DcMotor.class, "l1");
        motorRight1 = hardwareMap.get(DcMotor.class, "r1");
        motorLeft2 = hardwareMap.get(DcMotor.class, "l2");
        motorRight2 = hardwareMap.get(DcMotor.class, "r2");
        motorLeft1.setDirection(DcMotorSimple.Direction.REVERSE);
        motorLeft2.setDirection(DcMotorSimple.Direction.REVERSE);
        motorRight1.setDirection(DcMotorSimple.Direction.FORWARD);
        motorRight2.setDirection(DcMotorSimple.Direction.FORWARD);
        motorLeft1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        motorLeft2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        motorRight1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        motorRight2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);


        // wait for start command.
        waitForStart();


        // get a reference to the motor controller and cast it as an extended functionality controller.
        // we assume it's a REV Robotics Expansion Hub (which supports the extended controller functions).
        DcMotorControllerEx motorControllerEx1 = (DcMotorControllerEx) motorLeft1.getController();
        DcMotorControllerEx motorControllerEx2 = (DcMotorControllerEx) motorLeft2.getController();
        DcMotorControllerEx motorControllerEx3 = (DcMotorControllerEx) motorRight1.getController();
        DcMotorControllerEx motorControllerEx4 = (DcMotorControllerEx) motorRight2.getController();

        // get the port number of our configured motor.
        int motorIndex1 = ((DcMotorEx) motorLeft1).getPortNumber();
        int motorIndex2 = ((DcMotorEx) motorLeft2).getPortNumber();
        int motorIndex3 = ((DcMotorEx) motorRight1).getPortNumber();
        int motorIndex4 = ((DcMotorEx) motorRight2).getPortNumber();


        // get the PID coefficients for the RUN_USING_ENCODER  modes.
        // change coefficients.

        PIDCoefficients left1PID = new PIDCoefficients(L1_P, L1_I, L1_D);
        PIDCoefficients left2PID = new PIDCoefficients(L2_P, L2_I, L2_D);
        PIDCoefficients right1PID = new PIDCoefficients(R1_P, R1_I, R1_D);
        PIDCoefficients right2PID = new PIDCoefficients(R2_P, R2_I, R2_D);
        motorControllerEx1.setPIDCoefficients(motorIndex4, DcMotor.RunMode.RUN_USING_ENCODER, left1PID);
        motorControllerEx2.setPIDCoefficients(motorIndex3, DcMotor.RunMode.RUN_USING_ENCODER, left2PID);
        motorControllerEx3.setPIDCoefficients(motorIndex2, DcMotor.RunMode.RUN_USING_ENCODER, right1PID);
        motorControllerEx4.setPIDCoefficients(motorIndex1, DcMotor.RunMode.RUN_USING_ENCODER, right2PID);


        // re-read coefficients and verify change.
        PIDCoefficients pidModified1 = motorControllerEx1.getPIDCoefficients(motorIndex1, DcMotor.RunMode.RUN_USING_ENCODER);
        PIDCoefficients pidModified2 = motorControllerEx1.getPIDCoefficients(motorIndex2, DcMotor.RunMode.RUN_USING_ENCODER);
        PIDCoefficients pidModified3 = motorControllerEx1.getPIDCoefficients(motorIndex3, DcMotor.RunMode.RUN_USING_ENCODER);
        PIDCoefficients pidModified4 = motorControllerEx1.getPIDCoefficients(motorIndex4, DcMotor.RunMode.RUN_USING_ENCODER);


        ElapsedTime elapsedTime = new ElapsedTime();
        elapsedTime.reset();
        elapsedTime.startTimeNanoseconds();
        // display info to user.
        while (opModeIsActive()) {

            float yval = gamepad1.left_stick_y;
            float xval = gamepad1.right_stick_x;


            float lpwr = (float) Math.pow(((yval - xval)), 3);
            float rpwr = (float) Math.pow((yval + xval), 3);
//
//        float lpwr = gamepad1.left_stick_y;
//        float rpwr = gamepad1.right_stick_y;


//        turtle mode
            if (gamepad1.right_trigger != 0 || gamepad1.left_trigger != 0) {
                lpwr = lpwr / 3.0f;
                rpwr = rpwr / 3.0f;
            }
            if (Math.abs(lpwr) < 0.1)
                lpwr = 0;
            if (Math.abs(rpwr) < 0.1)
                rpwr = 0;
//            setLeftPower(lpwr,((DcMotorEx) motorLeft1).getVelocity(AngleUnit.DEGREES),((DcMotorEx) motorLeft2).getVelocity(AngleUnit.DEGREES));
//            setLeftPower(rpwr,((DcMotorEx) motorRight1).getVelocity(AngleUnit.DEGREES),((DcMotorEx) motorRight2).getVelocity(AngleUnit.DEGREES));

            String val1 = ((DcMotorEx) motorLeft1).getVelocity(AngleUnit.DEGREES) + "   Power L1: " + motorLeft1.getPower();
            String val2 = ((DcMotorEx) motorLeft2).getVelocity(AngleUnit.DEGREES) + "    Power L2: " + motorLeft2.getPower();
            String val3 = ((DcMotorEx) motorRight1).getVelocity(AngleUnit.DEGREES) + "   Power R1 " + motorRight1.getPower();
            String val4 = ((DcMotorEx) motorRight2).getVelocity(AngleUnit.DEGREES) + "     Power R2 " + motorRight2.getPower();

            Log.i("drift", val1 + " \n " + val2 + " \n" + val3 + " \n" + val4);


            telemetry.addData("leftWheel1Vel", val1);
            telemetry.addData("leftWheel2Vel", val2);
            telemetry.addData("rightWheel1Vel", val3);
            telemetry.addData("rightWheel2Vel", val4);
            telemetry.update();
        }

    }

//
//    public void setLeftPower(double power, double v1, double v2)
//    {
//        double diff = 0;
//        if((Math.abs(v1)-Math.abs(v2))>30)
//            diff+=0.005;
//        else if(Math.abs(v2)-Math.abs(v1)>30)
//            diff-=0.005;
//        else
//            diff = 0;
//        motorLeft1.setPower(Range.clip(power+diff,-1,1));
//        motorLeft1.setPower(Range.clip(power+diff,-1,1));
//    }

    public void setRightPower(double power,double v1, double v2)
    {
        double diff = 0;
        if((Math.abs(v1)-Math.abs(v2))>30)
            diff+=0.005;
        else if(Math.abs(v2)-Math.abs(v1)>30)
            diff-=0.005;
        else
            diff = 0;
        motorRight2.setPower(Range.clip(power+diff,-1,1));
        motorRight1.setPower(Range.clip(power+diff,-1,1));
    }

}



