package org.firstinspires.ftc.teamcode.Tests;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.teamcode.Util.*;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * Created by Sumanth on 11/9/17.
 */

@Autonomous (name = "VisionUtilTest", group = "")
public class ConceptVisionUtil extends LinearOpMode{

    @Override
    public void runOpMode() throws InterruptedException {

        VisionUtil vuMarkTheLark = new VisionUtil();

        int graph = 4;

        RelicRecoveryVuMark reading = vuMarkTheLark.readGraph(hardwareMap);

        waitForStart();

        while(opModeIsActive()){

            if(reading == RelicRecoveryVuMark.CENTER){

                graph = 2;

            }
            else if(reading == RelicRecoveryVuMark.LEFT){

                graph = 1;

            }
            else if(reading == RelicRecoveryVuMark.RIGHT){

                graph = 3;

            }
            else if(reading == RelicRecoveryVuMark.UNKNOWN){
                graph = 0;
            }


        }




    }
}
