package org.firstinspires.ftc.teamcode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Util.VumarkDetect;

/**
 * Created by therat0981 on 11/3/17.
 */

public class NoorsVumarkTest extends LinearOpMode
{
    VumarkDetect vumarkDetect;
    @Override
    public void runOpMode() throws InterruptedException {

        vumarkDetect.initVumark(hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName()));

        waitForStart();
        vumarkDetect.activateVumark();

        telemetry.addData("Position %s", vumarkDetect.GetPosition());
        telemetry.update();
    }
}
