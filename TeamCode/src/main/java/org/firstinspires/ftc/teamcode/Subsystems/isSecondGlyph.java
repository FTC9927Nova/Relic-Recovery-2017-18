package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Util.Gyro;

@TeleOp(name = "second glyphy test")
@Disabled
public class isSecondGlyph extends OpMode
{
    Robot robot = new Robot();
    Gyro gyro = new Gyro();

    @Override
    public void init() {
        gyro.initGyro(hardwareMap);
        robot.init(hardwareMap,gyro);
    }

    public void loop() {

//        if(!robot.wheels.checkSecondGlyph())
//            robot.wheels.secondGlyphIntake();
//        telemetry.addData("", robot.wheels.checkSecondGlyph());
//        telemetry.update();

    }
    public void stop()
    {

    }

}
