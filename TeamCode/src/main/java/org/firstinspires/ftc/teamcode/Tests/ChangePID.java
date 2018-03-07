package org.firstinspires.ftc.teamcode.Tests;

import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorControllerEx;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.PIDCoefficients;
import com.qualcomm.robotcore.util.ElapsedTime;

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

@Autonomous(name="Concept: Change PID Controller", group = "Examples")
public class ChangePID extends LinearOpMode {

    // our DC motor.
    DcMotor motorLeft;

    public static final double NEW_P = 2.5;
    public static final double NEW_I = 0.1;
    public static final double NEW_D = 0.2;

    public void runOpMode() {
        // get reference to DC motor.
        motorLeft = hardwareMap.get(DcMotor.class, "l1");

        // wait for start command.
        waitForStart();

        // get a reference to the motor controller and cast it as an extended functionality controller.
        // we assume it's a REV Robotics Expansion Hub (which supports the extended controller functions).
        DcMotorControllerEx motorControllerEx = (DcMotorControllerEx)motorLeft.getController();

        // get the port number of our configured motor.
        int motorIndex = ((DcMotorEx)motorLeft).getPortNumber();

        // get the PID coefficients for the RUN_USING_ENCODER  modes.
        PIDCoefficients pidOrig = motorControllerEx.getPIDCoefficients(motorIndex, DcMotor.RunMode.RUN_USING_ENCODER);

        // change coefficients.

        PIDCoefficients pidNew = new PIDCoefficients(NEW_P, NEW_I, NEW_D);
        motorControllerEx.setPIDCoefficients(motorIndex, DcMotor.RunMode.RUN_USING_ENCODER, pidNew);

        // re-read coefficients and verify change.
        PIDCoefficients pidModified = motorControllerEx.getPIDCoefficients(motorIndex, DcMotor.RunMode.RUN_USING_ENCODER);

        ElapsedTime elapsedTime = new ElapsedTime();
        elapsedTime.reset();
        elapsedTime.startTimeNanoseconds();
        // display info to user.
        while(opModeIsActive()) {
//            ((DcMotorEx) motorLeft).setVelocity(180, AngleUnit.DEGREES);
            ((DcMotorEx) motorLeft).setVelocity(720,AngleUnit.DEGREES);
            telemetry.addData(((DcMotorEx) motorLeft).getVelocity(AngleUnit.DEGREES) + "", "currentPos " + motorLeft.getCurrentPosition());
            Log.i("DegPerSec:", ((DcMotorEx) motorLeft).getVelocity(AngleUnit.DEGREES)+"" + elapsedTime.milliseconds());
            telemetry.addData("" + motorLeft.getPower(), "");
            telemetry.update();
        }
    }
}
