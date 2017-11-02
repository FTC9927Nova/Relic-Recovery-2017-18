package org.firstinspires.ftc.teamcode.Tests;

import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.PIDCoefficients;
import com.qualcomm.robotcore.hardware.VoltageSensor;
import com.qualcomm.robotcore.hardware.configuration.ExpansionHubMotorControllerParamsState;
import com.qualcomm.robotcore.hardware.configuration.ExpansionHubMotorControllerPositionParams;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.internal.android.dx.dex.file.StringDataItem;

/**
 * Created by tom on 9/26/17.
 * This assumes that you are using a REV Robotics Expansion Hub
 * as your DC motor controller.  This op mode uses the extended/enhanced
 * PID-related functions of the DcMotorEx class.  The REV Robotics Expansion Hub
 * supports the extended motor functions, but other controllers (such as the
 * Modern Robotics and Hitechnic DC Motor Controllers) do not.
 */

@Autonomous(name="Concept: Change PID", group = "Concept")
@Disabled
public class ConceptChangePID extends LinearOpMode {

    // our DC motor.
    DcMotorController dcMotorController;
    DcMotorEx motorExLeft;


    public static final double NEW_P = 2.5;
    public static final double NEW_I = 0.1;
    public static final double NEW_D = 0.2;

    VoltageSensor voltageSensor;


    public void runOpMode() {
        // get reference to DC motor.
        // since we are using the Expansion Hub,
        // cast this motor to a DcMotorEx object.
        motorExLeft = (DcMotorEx) hardwareMap.get(DcMotor.class, "l1");
        // wait for start command.
        waitForStart();

        // get the PID coefficients for the RUN_USING_ENCODER  modes.
        PIDCoefficients pidOrig = motorExLeft.getPIDCoefficients(DcMotor.RunMode.RUN_USING_ENCODER);

        // change coefficients using methods included with DcMotorEx class.
        PIDCoefficients pidNew = new PIDCoefficients(NEW_P, NEW_I, NEW_D);
        motorExLeft.setPIDCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidNew);

        // re-read coefficients and verify change.
        PIDCoefficients pidModified = motorExLeft.getPIDCoefficients(DcMotor.RunMode.RUN_USING_ENCODER);

        // display info to user.
        while (opModeIsActive()) {
            telemetry.addData("Runtime", "%.03f", getRuntime());
            telemetry.addData("P,I,D (orig)", "%.04f, %.04f, %.0f",
                    pidOrig.p, pidOrig.i, pidOrig.d);
            telemetry.addData("P,I,D (modified)", "%.04f, %.04f, %.04f",
                    pidModified.p, pidModified.i, pidModified.d);
            motorExLeft.setVelocity(90,AngleUnit.DEGREES);
            if(gamepad1.a)
                motorExLeft.setPower(0.5);
            else if (gamepad1.b)
                motorExLeft.setPower(1);
            Log.i("velocity", String.valueOf(motorExLeft.getVelocity(AngleUnit.DEGREES)));
            Log.i("power", String.valueOf((motorExLeft.getPower())));



            telemetry.update();
        }
    }
}