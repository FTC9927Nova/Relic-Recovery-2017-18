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
@Autonomous(name = "GlyphInBox2")
public class PutGlyphInBox2 extends LinearOpMode {

    Robot robot = new Robot();
    Gyro gyro = new Gyro();

    @Override
    public void runOpMode() throws InterruptedException {


        gyro.initGyro(hardwareMap);
        robot.init(hardwareMap, this, gyro);



        waitForStart();
        if (opModeIsActive()){

            robot.driveTrain.setMoveDist(17);

            VisionUtil visionUtil = new VisionUtil();

            RelicRecoveryVuMark reading = visionUtil.readGraph(hardwareMap);

            sleep(2000);

            telemetry.addData("vumark 1", reading);
            telemetry.update();


            switch (reading){
                case RIGHT:{
                    robot.driveTrain.setMoveDist(-66-25);
                    placeBlock();
                    break;
                }
                case CENTER:{

                    robot.driveTrain.setMoveDist(-66-10);
                    placeBlock();
                    break;
                }
                case LEFT:{
                    robot.driveTrain.setMoveDist(-64);
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

        robot.driveTrain.rotateDeg(87.5);
        robot.driveTrain.setMoveDist(3);
        robot.wheels.outtakeLeft();
        robot.wheels.outtakeRight();
        sleep(500);
        robot.wheels.stopLeft();
        robot.wheels.stopRight();
        robot.driveTrain.setMoveDist(-3);
    }
}
