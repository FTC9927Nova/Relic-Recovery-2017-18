package org.firstinspires.ftc.teamcode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Subsystems.Robot;
import org.firstinspires.ftc.teamcode.Util.Gyro;
@TeleOp(name = "intAccTester")
@Disabled
public class IntegrationTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Robot robot = new Robot();
        Gyro gyro = new Gyro();
        ElapsedTime time = new ElapsedTime();
        double timeElapsed = 0;

        double areaVelocity = 0;
        double areaPosition = 0;
        double sumOfPosX = 0;


        waitForStart();
        while (opModeIsActive()){
            telemetry.addData("Acceleration", gyro.getAccelerationX() + " (m/s)2");
            telemetry.addData("Velocity", areaVelocity + " m/s");
            telemetry.addData("Position", sumOfPosX + " m");
            telemetry.update();

            time.startTime();

            if (time.milliseconds() >= 5){
                timeElapsed = time.milliseconds();
                time.reset();
                areaVelocity = (timeElapsed * 1000)*gyro.getAccelerationX();
                areaPosition = (timeElapsed * 1000)*areaVelocity;
                sumOfPosX += areaPosition;
            } else {

            }

        }
    }
}
