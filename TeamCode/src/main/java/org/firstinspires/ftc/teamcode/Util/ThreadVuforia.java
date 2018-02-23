package org.firstinspires.ftc.teamcode.Util;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Created by Ethan Pereira on 2/21/2018.
 */

public class ThreadVuforia extends Thread{

    LinearOpMode linearOpMode;
    HardwareMap hmap;
    public ThreadVuforia(LinearOpMode linearOpMode1, HardwareMap hardwareMap){
        hmap = hardwareMap;
        linearOpMode = linearOpMode1;
    }

    VisionUtil visionUtil = new VisionUtil(linearOpMode);
    @Override
    public void run() {
        visionUtil.readGraph(hmap);
    }
}
