package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Nikolas on 11/15/2017.
 */

@Autonomous(name = "TestAutonomous")
public class Autonomustest extends LinearOpMode
{
    private DcMotor motorLeft;
    private DcMotor motorRight;
    private DcMotor motorArm;

    private Servo servoLeft;
    private Servo servoRight;

    private static final double RELIC_GRAB_POSITION = 0.8;
    private static final double RELIC_RELEASE_POSITION = 0.2;

    @Override
    public void runOpMode() throws InterruptedException
    {
        motorLeft = hardwareMap.dcMotor.get("motorLeft" );
        motorRight = hardwareMap.dcMotor.get("motorRight");
        motorArm = hardwareMap.dcMotor.get("motorArm");

        motorLeft.setDirection(DcMotor.Direction.REVERSE);

        servoLeft = hardwareMap.servo.get("servoLeft");
        servoRight = hardwareMap.servo.get("servoRight");

        servoRight.setPosition(RELIC_GRAB_POSITION);
        servoLeft.setPosition(RELIC_GRAB_POSITION);

        waitForStart();

        // Drive Forward
        DriveForward(DRIVE_POWER);
        Thread.sleep(4000);
        //stop
       StopDriving();
    }
    double DRIVE_POWER = 1.0;

        public void DriveForward(double power)
        {
            motorLeft.setPower(power);
            motorRight.setPower(power);
        }

        public void TurnLeft(double power)
        {
            motorLeft.setPower(-power);
            motorRight.setPower(power);
        }

        public void StopDriving()
        {
            DriveForward(0);
        }

        public void TurnRight(double power)
        {
            motorLeft.setPower(power);
            motorRight.setPower(-power);
        }
}
