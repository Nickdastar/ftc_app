package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Nikolas on 11/15/2017.
 */


@TeleOp(name = "RebelTeleOp")
public class RebelsTeleOp extends LinearOpMode
{
    private DcMotor motorLeft;
    private DcMotor motorRight;
    private DcMotor motorArm;

    private Servo servoLeft;
    private Servo servoRight;

    private static final double RELIC_GRAB_POSITION = 0.8;
    private static final double RELIC_RELASE_POSITION = 0.2;

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

        while(opModeIsActive())
        {
            motorLeft.setPower(-gamepad1.left_stick_y);
            motorRight.setPower(-gamepad1.right_stick_y);
            motorArm.setPower(-gamepad2.right_stick_y);

            if(gamepad2.a)
            {
                servoLeft.setPosition(RELIC_GRAB_POSITION);
            }

            if(gamepad2.b)
            {
                servoLeft.setPosition(RELIC_RELASE_POSITION);
            }

            if(gamepad2.x)
            {
                servoRight.setPosition(RELIC_GRAB_POSITION);
            }

            if(gamepad2.y)
            {
                servoRight.setPosition(RELIC_RELASE_POSITION);
            }

            idle();
        }
    }
}
