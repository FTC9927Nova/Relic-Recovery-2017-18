package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by Ethan Pereira on 11/5/2017.
 */

public class LimitDevices implements SubsystemTemplate {


    private DigitalChannel limitDevice;

    public LimitDevices(HardwareMap hardwareMap){
        limitDevice = hardwareMap.digitalChannel.get("limitDevice");
    }

    public String getReading(){
        return String.valueOf(limitDevice.getState());
    }

    
    @Override
    public String display() {
        return null;
    }

}
