package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Nikolas on 11/15/2017.
 */

@Autonomous(name = "RebelAuto")
public class RebelAuto extends LinearOpMode
{
    private DcMotor motorLeft = null;
    private DcMotor motorRight = null;
    private DcMotor motorArm = null;

    private Servo servoLeft = null;
    private Servo servoRight = null;
    private Servo servoBack = null;

    private static final double RELIC_GRAB_POSITION = 0.8;
    private static final double RELIC_RELEASE_POSITION = 0.2;

    @Override
    public void runOpMode() throws InterruptedException
    {

        motorLeft = hardwareMap.dcMotor.get("motorLeft" );
        motorRight = hardwareMap.dcMotor.get("motorRight");
        motorArm = hardwareMap.dcMotor.get("motorArm");

        motorLeft.setDirection(DcMotor.Direction.REVERSE);
        motorRight.setDirection(DcMotor.Direction.FORWARD);
        motorArm.setDirection(DcMotor.Direction.FORWARD);

        servoLeft = hardwareMap.servo.get("servoLeft");
        servoRight = hardwareMap.servo.get("servoRight");
        servoBack = hardwareMap.servo.get("servoBack");

        servoRight.setPosition(0.85);
        servoLeft.setPosition(0);
        servoBack.setPosition(0.1);

        motorLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorArm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        waitForStart();

        // Drive Forward
        servoLeft.setPosition(0.45);
        servoRight.setPosition(0.5);
        DriveForward(DRIVE_POWER);
        Thread.sleep(1250);
        servoLeft.setPosition(0.1);
        servoRight.setPosition(0.8);
        Thread.sleep(650);
        DriveBackwards(DRIVE_POWER);
        Thread.sleep(425);
        //stop
       StopDriving();
    }
    double DRIVE_POWER = 0.5;

        public void DriveForward(double power)
        {
            motorLeft.setPower(-power);
            motorRight.setPower(-power);
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
        public void DriveBackwards(double power)
        {
        motorLeft.setPower(power);
        motorRight.setPower(power);
        }
}
