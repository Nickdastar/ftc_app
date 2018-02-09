package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by nicks on 11/16/2017.
 */

@TeleOp(name="RoboSquadTeleOp", group="Linear Opmode")

public class RoboSquadTeleOp extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor motorLeft = null;
    private DcMotor motorRight = null;
    private DcMotor motorLift = null;

    private Servo servoLeft = null;
    private Servo servoRight = null;

    private static final double RELIC_GRAB_POSITION = 1;
    private static final double RELIC_RELEASE_POSITION = 0.2;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        motorLeft  =  hardwareMap.get(DcMotor.class, "motorLeft");
        motorRight = hardwareMap.get(DcMotor.class, "motorRight");
        motorLift = hardwareMap.get(DcMotor.class, "motorLift");
        motorLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorLift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        servoLeft = hardwareMap.get(Servo.class,"servoLeft");
        servoRight = hardwareMap.get(Servo.class,"servoRight");

        //servoRight.setPosition(0);
        //servoLeft.setPosition(1);

        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        motorLeft.setDirection(DcMotor.Direction.FORWARD);
        motorRight.setDirection(DcMotor.Direction.REVERSE);
        motorLift.setDirection(DcMotor.Direction.FORWARD);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            // Setup a variable for each drive wheel to save power level for telemetry
            double leftPower;
            double rightPower;
            double liftPower;


            // Choose to drive using either Tank Mode, or POV Mode
            // Comment out the method that's not used.  The default below is POV.

            // POV Mode uses left stick to go forward, and right stick to turn.
            // - This uses basic math to combine motions and is easier to drive straight.
            double drive = -gamepad1.left_stick_y;
            double turn  =  gamepad1.left_stick_x;
            leftPower    = Range.clip(drive + turn, -1.0, 1.0) ;
            rightPower   = Range.clip(drive - turn, -1.0, 1.0) ;

            // Tank Mode uses one stick to control each wheel.
            // - This requires no math, but it is hard to drive forward slowly and keep straight.
            //leftPower  = -gamepad1.left_stick_y ;
            //rightPower = -gamepad1.right_stick_y ;
            liftPower = gamepad2.right_stick_y;

            // Send calculated power to wheels
            motorLeft.setPower(leftPower);
            motorRight.setPower(rightPower);
            motorLift.setPower(liftPower);

            if (gamepad2.right_bumper) {
                servoLeft.setPosition(0.9);
            }

            if (gamepad2.left_bumper) {
                servoLeft.setPosition(0.4);
            }

            if (gamepad2.right_bumper) {
                servoRight.setPosition(0.2);
            }

            if (gamepad2.left_bumper)
            {
                servoRight.setPosition(0.7);
            }




            //if (gamepad2.x){
              //  motorLift.setPower(-1);
                //sleep(2400);
                //motorLift.setPower(0);
            //}



            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
            telemetry.update();
        }
    }
}
