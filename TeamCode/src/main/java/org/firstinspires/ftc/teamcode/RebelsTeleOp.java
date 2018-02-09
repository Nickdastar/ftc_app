package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Nikolas on 11/15/2017.
 */


@TeleOp(name="RebelsTeleOp", group="Linear Opmode")

public class RebelsTeleOp extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();

    private DcMotor motorLeft = null;
    private DcMotor motorRight = null;
    private DcMotor motorArm = null;
    //private DcMotor motorTurn = null;

    private Servo servoLeft = null;
    private Servo servoRight = null;
    private Servo servoBack = null;

    private static final double RELIC_GRAB_POSITION = 0.6;
    private static final double RELIC_RELEASE_POSITION = 0.08;

    @Override
    public void runOpMode() {
        telemetry.addData("Say", "Have A Good Day");
        telemetry.update();

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        motorLeft  =  hardwareMap.get(DcMotor.class, "motorLeft");
        motorRight = hardwareMap.get(DcMotor.class, "motorRight");
        motorArm = hardwareMap.get(DcMotor.class, "motorArm");
        //motorTurn = hardwareMap.get(DcMotor.class,"motorTurn");
        motorLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorArm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        //motorTurn.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        servoLeft = hardwareMap.get(Servo.class,"servoLeft");
        servoRight = hardwareMap.get(Servo.class,"servoRight");
        servoBack = hardwareMap.get(Servo.class,"servoBack");

        servoRight.setPosition(0.85);
        servoLeft.setPosition(0);
        servoBack.setPosition(0.1);

        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        motorLeft.setDirection(DcMotor.Direction.REVERSE);
        motorRight.setDirection(DcMotor.Direction.FORWARD);
        motorArm.setDirection(DcMotor.Direction.FORWARD);
        //motorTurn.setDirection(DcMotor.Direction.FORWARD);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            // Setup a variable for each drive wheel to save power level for telemetry
            double leftPower;
            double rightPower;
            double armPower;
            //double backPower;
            //double turnPower;

            // Choose to drive using either Tank Mode, or POV Mode
            // Comment out the method that's not used.  The default below is POV.

            // POV Mode uses left stick to go forward, and right stick to turn.
            // - This uses basic math to combine motions and is easier to drive straight.
            //double drive = -gamepad1.left_stick_y;
            //double turn  =  gamepad1.right_stick_x;
            //leftPower    = Range.clip(drive + turn, -1.0, 1.0) ;
            //rightPower   = Range.clip(drive - turn, -1.0, 1.0) ;

            // Tank Mode uses one stick to control each wheel.
            // - This requires no math, but it is hard to drive forward slowly and keep straight.
            leftPower  = gamepad1.right_stick_y ;
            rightPower = gamepad1.left_stick_y ;
            armPower = -gamepad2.right_stick_y;
            //turnPower = gamepad1.right_stick_x;

            // Send calculated power to wheels
            motorLeft.setPower(leftPower);
            motorRight.setPower(rightPower);
            motorArm.setPower(armPower);
            //motorTurn.setPower(turnPower);

            if (gamepad2.left_bumper) {
                servoLeft.setPosition(0.1);
            }

            if (gamepad2.right_bumper) {
                servoLeft.setPosition(0.7);
            }

            if (gamepad2.right_bumper) {
                servoRight.setPosition(0.55);
            }

            if (gamepad2.left_bumper)
            {
                servoRight.setPosition(0.8);
            }

            if (gamepad2.y) {
                servoBack.setPosition(1);

            }

            if (gamepad2.x) {
                servoBack.setPosition(0.1);
            }

            if (gamepad1.right_bumper) {
                servoLeft.setPosition(0.375);
            }

            if (gamepad1.right_bumper) {
                servoRight.setPosition(0.625);
            }

            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
            telemetry.update();
        }
    }
}