package org.firstinspires.ftc.teamcode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Subsystems.Robot;
import org.firstinspires.ftc.teamcode.Util.VisionUtil;

/**
 * Created by Ethan Pereira on 12/2/2017.
 */

public class HitJewl extends Thread {
    Robot robot = new Robot();
    VisionUtil visionUtil;
    HardwareMap hardwareMap;
    LinearOpMode linearOpMode;

    void startThread(){
        visionUtil = new VisionUtil();
        start();

    }

    public void run(){

            if (String.valueOf(robot.jewelArm.getColor()).equals("BLUE")) {

                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                robot.driveTrain.setLeftPower(1);
                robot.driveTrain.setRightPower(1);
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            } else if (String.valueOf(robot.jewelArm.getColor()).equals("RED")) {
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                robot.driveTrain.setLeftPower(-1);
                robot.driveTrain.setRightPower(-1);
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            } else {

            }

    }
}
