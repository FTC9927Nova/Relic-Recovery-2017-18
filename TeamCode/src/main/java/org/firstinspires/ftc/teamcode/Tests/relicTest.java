package org.firstinspires.ftc.teamcode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystems.RelicMech;

/**
 * Created by Sumanth on 12/16/17.
 */
@Disabled

@TeleOp (name = "TEleop", group = "sd")
public class relicTest extends OpMode {

    RelicMech relic;

    @Override
    public void init() {

        relic = new RelicMech(hardwareMap);

    }

    @Override
    public void loop() {

        telemetry.addData("",relic.display());
        telemetry.update();

    }
}
