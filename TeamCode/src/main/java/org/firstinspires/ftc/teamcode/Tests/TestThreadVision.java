package org.firstinspires.ftc.teamcode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Subsystems.Robot;
import org.firstinspires.ftc.teamcode.Util.Gyro;
import org.firstinspires.ftc.teamcode.Util.MultiThreadBar4Lift;
import org.firstinspires.ftc.teamcode.Util.ThreadVuforia;

/**
 * Created by Ethan Pereira on 2/23/2018.
 */

public class TestThreadVision extends LinearOpMode {
    Robot robot = new Robot();
    Gyro gyro;
    int x = 0;
    MultiThreadBar4Lift threadBar4Lift = new MultiThreadBar4Lift();
    //ThreadVuforia threadVuforia = new ThreadVuforia(this, hardwareMap);
    @Override
    public void runOpMode() throws InterruptedException {
        gyro.initGyro(hardwareMap);
        robot.init(hardwareMap, this, gyro);

        waitForStart();
        if (opModeIsActive()){
//            threadVuforia.run();
            threadBar4Lift.run();
            while (opModeIsActive()){
                if (x == 0){
                    robot.jewelArm.armDown();
                    x++;
                } else {
                    robot.jewelArm.armMid();
                    x--;
                }
            }

        }
    }
}
