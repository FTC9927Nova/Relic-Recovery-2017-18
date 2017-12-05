package org.firstinspires.ftc.teamcode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.teamcode.Subsystems.Robot;
import org.firstinspires.ftc.teamcode.Util.VisionUtil;

/**
 * Created by Ethan Pereira on 11/30/2017.
 */

public class CheckVision extends Thread {
    Robot robot = new Robot();
    VisionUtil visionUtil;
    HardwareMap hardwareMap;
    LinearOpMode linearOpMode;
    public void setHardwareMap(HardwareMap hmap){

        hardwareMap = hmap;
    }

    void startThread(){
        visionUtil = new VisionUtil();
        start();

    }

    public void run(){
        RelicRecoveryVuMark reading = visionUtil.readGraph(hardwareMap);
        while (reading.equals(RelicRecoveryVuMark.UNKNOWN)){

            reading = visionUtil.readGraph(hardwareMap);
//            robot.driveTrain.setMoveDist(2);
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            linearOpMode.telemetry.addData("read", reading);
            linearOpMode.telemetry.update();

        }

    }
}
