package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by Sumanth on 11/8/17.
 */

public class RelicMech implements SubsystemTemplate
{
    DcMotor relic;

    public RelicMech(HardwareMap hardwareMap)
    {
        relic = hardwareMap.dcMotor.get("relic");
        relic.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void setPower(double pwr)
    {
        relic.setPower(pwr);
    }

    @Override
    public String display() {
        return null;
    }
}
