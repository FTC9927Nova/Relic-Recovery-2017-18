package org.firstinspires.ftc.teamcode.Autons;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

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

    VisionUtil visionUtil = new VisionUtil();

    int pos;
    boolean isFound = false;

    int dist = 0;
    double initialYaw;

    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart();
        if (opModeIsActive()){
            initialYaw = gyro.getYaw();
            robot.driveTrain.setMoveDist(8);
            dist += 8;
            while (!isFound){
                if (visionUtil.getPicNumber(hardwareMap) == 1){
                    pos = 1;
                    isFound = true;
                } else if (visionUtil.getPicNumber(hardwareMap) == 2){
                    pos = 2;
                    isFound = true;
                } else if (visionUtil.getPicNumber(hardwareMap) == 3){
                    pos = 3;
                    isFound = true;
                } else {
                    robot.driveTrain.setMoveDist(1);
                    dist += 1;
                }

            }
            robot.driveTrain.setMoveDist(-dist + 28);
            robot.driveTrain.rotateDeg(-gyro.getYaw());
            robot.driveTrain.rotateDeg(-90);
            robot.driveTrain.setMoveDist(3);

            switch (pos){
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
