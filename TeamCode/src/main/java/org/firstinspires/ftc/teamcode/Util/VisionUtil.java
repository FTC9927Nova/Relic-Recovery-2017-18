package org.firstinspires.ftc.teamcode.Util;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcontroller.external.samples.ConceptVuforiaNavigation;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuMarkInstanceId;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;



public class VisionUtil{

    public static final String TAG = "Vuforia VuMark Sample";



    VuforiaLocalizer vuforia;

    boolean keepLooking = true;


    public RelicRecoveryVuMark readGraph(HardwareMap hardwareMap) {

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);


        parameters.vuforiaLicenseKey = "ARG9zPL/////AAAAGYRPMkfry0jjjbhWzSGUNbdSf/k8xZVEMsbpZl3g8XPJraKlzSwk7/tbjPjW71b0+nd0XUjLJ+IxKnEAlZI8vItQHZCHZ0y9PAInF40xCzJIcRLI0Kjl4zOJgVufAFP5C7C8vQsHx1KBwdki3MoUGW/HJncwthKOewDKJmNE0prBUM9Gdhf+sVS43wfLJ5Y6oOF7913DyvxFwgvQ7Fu3DqomZIQmeM/B2VU0Bff8x/7Klmom4YloL7pCtfeE0wb2+OpoC9Xtxc9vIuu6eF3kTP7PDJ+sWZ8KqNl7KEgMkKYgCM4oqPTwtcKrJw+a2l7bIeGY1s/bBlAE1VPUbk95h/4Ow00EFzjYcFgl2HmOY0UI";

        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.FRONT;
        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);

        VuforiaTrackables relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        VuforiaTrackable relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate"); // can help in debugging; otherwise not necessary

        relicTrackables.activate();


        while (keepLooking) {


            RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);

            if (vuMark != RelicRecoveryVuMark.UNKNOWN)
            {
                return vuMark;

            }

        }

        return RelicRecoveryVuMark.UNKNOWN;
    }

    public void stopLooking(){

        keepLooking = false;

    }
}