package org.firstinspires.ftc.teamcode.Autons;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.teamcode.Subsystems.Robot;
import org.firstinspires.ftc.teamcode.Util.Gyro;
import org.firstinspires.ftc.teamcode.Util.VisionUtil;

/**
 * Created by Ethan Pereira on 11/16/2017.
 */
@Autonomous(name = "GlyphInBox")
public class PutGlyphInBox extends LinearOpMode {

    Robot robot = new Robot();
    Gyro gyro = new Gyro();


    boolean isFound = false;

    int dist = 0;
    int graph = 0;


    @Override
    public void runOpMode() throws InterruptedException {


        gyro.initGyro(hardwareMap);
        robot.init(hardwareMap, this, gyro);

        VisionUtil visionUtil = new VisionUtil();

        RelicRecoveryVuMark reading = visionUtil.readGraph(this);

        waitForStart();
        if (opModeIsActive()){

//            robot.driveTrain.setMoveDist(16);

            sleep(3000);


//            dist += 16;
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

            sleep(2000);

            telemetry.addData("graph", graph);
            telemetry.addData("relic", reading);
            telemetry.update();

            robot.driveTrain.setMoveDist(-dist - 28);

            robot.driveTrain.rotateDeg(-90);
            robot.driveTrain.setMoveDist(3);

            visionUtil.stopLooking();

            switch (graph){
                case 1:{
                    placeBlock();
                    break;
                }
                case 2:{

                    robot.driveTrain.setMoveDist(7);
                    placeBlock();
                    break;
                }
                case 3:{
                    robot.driveTrain.setMoveDist(14);
                    placeBlock();
                    break;
                }
                default:{

                    placeBlock();
                    break;

                }
            }

        }

    }

    public void placeBlock(){

        robot.driveTrain.rotateDeg(-90);
        robot.driveTrain.setMoveDist(2);
        //TODO: OUTTAKE CODE HERE
        robot.driveTrain.setMoveDist(-2);
    }
}
