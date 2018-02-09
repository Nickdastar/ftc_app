package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Nikolas on 11/15/2017.
 */

@Autonomous(name = "RoboAuto2")
public class RoboAuto2 extends LinearOpMode
{
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor motorLeft = null;
    private DcMotor motorRight = null;
    private DcMotor motorLift = null;

    private Servo servoLeft = null;
    private Servo servoRight = null;


    private static final double RELIC_GRAB_POSITION = 0.8;
    private static final double RELIC_RELEASE_POSITION = 0.2;

    @Override
    public void runOpMode() throws InterruptedException
    {

        motorLeft  =  hardwareMap.get(DcMotor.class, "motorLeft");
        motorRight = hardwareMap.get(DcMotor.class, "motorRight");
        motorLift = hardwareMap.get(DcMotor.class, "motorLift");
        motorLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorLift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        servoLeft = hardwareMap.get(Servo.class,"servoLeft");
        servoRight = hardwareMap.get(Servo.class,"servoRight");

        motorLeft.setDirection(DcMotor.Direction.FORWARD);
        motorRight.setDirection(DcMotor.Direction.REVERSE);
        waitForStart();

        // Drive Forward
        DriveForward(DRIVE_POWER);
        Thread.sleep(850);//DriveForward(DRIVE_POWER);
        TurnRight(DRIVE_POWER);
        Thread.sleep(1200);
        DriveForward(DRIVE_POWER);
        Thread.sleep(150);
        StopDriving();
        Thread.sleep(500);
        DriveBackward(DRIVE_POWER);
        Thread.sleep(250);
        //Thread.sleep(100);
        //ReleaseRelic(0.2);
        //Thread.sleep(100);
        //TurnLeft(DRIVE_POWER);
        //Thread.sleep(2);
        //TurnRight(DRIVE_POWER);
        //Thread.sleep(3);
        //stop
       StopDriving();
    }
    double DRIVE_POWER = 1.0;

        public void DriveForward(double power)
        {
            motorLeft.setPower(power);
            motorRight.setPower(power);
        }
        public void DriveBackward(double power)
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
        public void GrabRelic(double position)
    {
        servoLeft.setPosition(RELIC_GRAB_POSITION);
        servoRight.setPosition(RELIC_GRAB_POSITION);
    }
    public void ReleaseRelic(double position)
    {
        servoLeft.setPosition(RELIC_RELEASE_POSITION);
        servoRight.setPosition(RELIC_RELEASE_POSITION);
    }
    //public void TreadsUp(double power)
    //{
    //    motorTreads.setPower(power);
      //  motorTread.setPower(power);
   // }
   // public void TreadsDown(double power)
    //{
    //    motorTreads.setPower(-power);
    //    motorTread.setPower(-power);
    //}
}
