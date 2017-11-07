package org.firstinspires.ftc.teamcode.Autons;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import org.firstinspires.ftc.teamcode.Subsystems.*;

import org.firstinspires.ftc.teamcode.Util.*;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * Created by Sumanth on 10/21/17.
 */

@Autonomous(name = "VlueforiaFar")
public class BLUEVuforiaJewlParkFar extends LinearOpMode{

    Robot robot = new Robot();

    Gyro gyro = new Gyro();

    int dist = 20;

    boolean isDone = false;

    RobotConstants constants = new RobotConstants();


    public static final String TAG = "Vuforia VuMark Sample";

//    OpenGLMatrix lastLocation = null;


    VuforiaLocalizer vuforia;

    @Override public void runOpMode() {

        gyro.initGyro(hardwareMap);
        robot.init(hardwareMap, gyro);

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);


        parameters.vuforiaLicenseKey = "ARG9zPL/////AAAAGYRPMkfry0jjjbhWzSGUNbdSf/k8xZVEMsbpZl3g8XPJraKlzSwk7/tbjPjW71b0+nd0XUjLJ+IxKnEAlZI8vItQHZCHZ0y9PAInF40xCzJIcRLI0Kjl4zOJgVufAFP5C7C8vQsHx1KBwdki3MoUGW/HJncwthKOewDKJmNE0prBUM9Gdhf+sVS43wfLJ5Y6oOF7913DyvxFwgvQ7Fu3DqomZIQmeM/B2VU0Bff8x/7Klmom4YloL7pCtfeE0wb2+OpoC9Xtxc9vIuu6eF3kTP7PDJ+sWZ8KqNl7KEgMkKYgCM4oqPTwtcKrJw+a2l7bIeGY1s/bBlAE1VPUbk95h/4Ow00EFzjYcFgl2HmOY0UI";

        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);

        VuforiaTrackables relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        VuforiaTrackable relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate"); // can help in debugging; otherwise not necessary

        telemetry.addData(">", "Press Play to start");
        telemetry.update();

        waitForStart();

        relicTrackables.activate();
        while (opModeIsActive() && isDone != true) {

            RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);
            if (vuMark != RelicRecoveryVuMark.UNKNOWN) {


                if(vuMark == RelicRecoveryVuMark.CENTER){

                    telemetry.addData("Status: ", "I see center");

                    //TODO: Wheel intake here
                    robot.driveTrain.setMoveDist(-2);
                    hitJewl();

                    robot.driveTrain.setMoveDist(20);
                    robot.driveTrain.rotateDeg(90);
                    robot.driveTrain.setMoveDist(12);
                    robot.driveTrain.rotateDeg(-90);
                    robot.driveTrain.setMoveDist(15);
                    //TODO: Wheel spit out here
                    robot.driveTrain.setMoveDist(-2.5);

                    isDone = true;

                }
                if(vuMark == RelicRecoveryVuMark.LEFT){

                    //TODO: Wheel intake here
                    robot.driveTrain.setMoveDist(-2);
                    hitJewl();

                    robot.driveTrain.setMoveDist(20);
                    robot.driveTrain.rotateDeg(90);
                    robot.driveTrain.setMoveDist(6);
                    robot.driveTrain.rotateDeg(-90);
                    robot.driveTrain.setMoveDist(15);
                    //TODO: Wheel spit out here
                    robot.driveTrain.setMoveDist(-2.5);

                    isDone = true;


                }
                if(vuMark == RelicRecoveryVuMark.RIGHT){

                    //TODO: Wheel intake here
                    robot.driveTrain.setMoveDist(-2);
                    hitJewl();

                    robot.driveTrain.setMoveDist(20);
                    robot.driveTrain.rotateDeg(90);
                    robot.driveTrain.setMoveDist(18);
                    robot.driveTrain.rotateDeg(-90);
                    robot.driveTrain.setMoveDist(15);
                    //TODO: Wheel spit out here
                    robot.driveTrain.setMoveDist(-2.5);

                    isDone = true;


                }

            }
            else {
                telemetry.addData("VuMark", "not visible");
            }

            telemetry.update();
        }


    }

    public void hitJewl(){

        robot.jewelArm.armDown();

        sleep(1000);

        if(String.valueOf(robot.jewelArm.getColor()) == "BLUE"){

            robot.driveTrain.setMoveDist(-4);
            sleep(1000);
            robot.jewelArm.armMid();
            sleep(1000);
            robot.driveTrain.setMoveDist(8);


        }


        else if(String.valueOf(robot.jewelArm.getColor()) == "RED"){

            robot.driveTrain.setMoveDist(4);
            sleep(2000);
            robot.jewelArm.armMid();


        }

    }


}