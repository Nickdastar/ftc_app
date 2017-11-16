package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Nikolas on 11/15/2017.
 */

@TeleOp(name = "RobosquadsTeleOp")
public class RoboSquadTeleOp extends LinearOpMode
{
    private DcMotor motorLeft;
    private DcMotor motorRight;
    private DcMotor motorTreads;

    private Servo servoLeft;
    private Servo servoRight;

    private static final double RELIC_GRAB_POSITION = 0.8;
    private static final double RELIC_RELEASE_POSITION = 0.2;

    @Override
    public void runOpMode() throws InterruptedException
    {
        motorLeft = hardwareMap.dcMotor.get("motorLeft");
        motorRight = hardwareMap.dcMotor.get("motorRight");
        motorTreads = hardwareMap.dcMotor.get("motorArm");

        motorLeft.setDirection(DcMotor.Direction.REVERSE);

        servoLeft = hardwareMap.servo.get("servoLeft");
        servoRight = hardwareMap.servo.get("servoRight");

        servoRight.setPosition(RELIC_GRAB_POSITION);
        servoLeft.setPosition(RELIC_GRAB_POSITION);

        waitForStart();

        while (opModeIsActive()) {
            motorLeft.setPower(-gamepad1.left_stick_y);
            motorRight.setPower(-gamepad1.right_stick_y);
            motorTreads.setPower(-gamepad2.right_stick_y);

            if (gamepad2.right_bumper) {
                servoLeft.setPosition(RELIC_GRAB_POSITION);
            }

            if (gamepad2.left_bumper) {
                servoLeft.setPosition(RELIC_RELEASE_POSITION);
            }

            if (gamepad2.right_trigger) {
                servoRight.setPosition(RELIC_GRAB_POSITION);
            }

            if (gamepad2.left_trigger) {
                servoRight.setPosition(RELIC_RELEASE_POSITION);
            }
        }
    }
}
