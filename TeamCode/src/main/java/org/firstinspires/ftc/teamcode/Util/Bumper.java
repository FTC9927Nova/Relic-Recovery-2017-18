package org.firstinspires.ftc.teamcode.Util;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by Sumanth on 11/9/17.
 */


//TODO: MAKE BUMPER BETERERERR
public class Bumper {

    DigitalChannel dt;

    public Bumper(HardwareMap hardwareMap){
        dt = hardwareMap.digitalChannel.get("bumper");
    }

    public boolean getState(){
        return dt.getState();
    }


}
