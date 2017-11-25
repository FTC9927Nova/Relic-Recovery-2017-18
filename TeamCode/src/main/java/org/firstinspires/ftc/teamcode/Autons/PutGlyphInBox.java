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

    @Override
    public void runOpMode() throws InterruptedException {


        gyro.initGyro(hardwareMap);
        robot.init(hardwareMap, this, gyro);



        waitForStart();
        if (opModeIsActive()){

            robot.driveTrain.setMoveDist(16);

            VisionUtil visionUtil = new VisionUtil();

            RelicRecoveryVuMark reading = visionUtil.readGraph(hardwareMap);

            sleep(2000);

            telemetry.addData("vumark 1", reading);
            telemetry.update();


            robot.driveTrain.setMoveDist(-28);

            robot.driveTrain.rotateDeg(-90);
            robot.driveTrain.setMoveDist(3);


            switch (reading){
                case CENTER:{
                    placeBlock();
                    break;
                }
                case LEFT:{

                    robot.driveTrain.setMoveDist(7);
                    placeBlock();
                    break;
                }
                case RIGHT:{
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
