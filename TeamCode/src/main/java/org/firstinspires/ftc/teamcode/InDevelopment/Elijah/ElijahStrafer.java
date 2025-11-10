package org.firstinspires.ftc.teamcode.InDevelopment.Elijah;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

@Disabled
@TeleOp(name="Elijah Strafer with shooters")
public class ElijahStrafer extends LinearOpMode {
    public DcMotor rightBackDrive = null;
    public DcMotor rightFrontDrive = null;
    public DcMotor leftBackDrive = null;
    public DcMotor leftFrontDrive = null;
    public DcMotorEx leftFlywheel = null;
    public DcMotorEx rightFlywheel = null;
    public CRServo releaseServo = null;
    public Servo   BallTransfer = null;
    public DcMotor leftActiveIntake = null;
    public DcMotor rightActiveIntake = null;
    public double leftBumperLastTime, rightBumperLastTime = 0;
    public static double MAX_SHOOTER_SPEED = 6000;
    double shooterSpeed = 3000;
    public boolean shootersOn = false;
    public boolean aButtonPressed = false;


    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Hardware mapping

        ///  motors
        rightBackDrive = hardwareMap.get(DcMotor.class, "rightBackDrive");
        leftBackDrive = hardwareMap.get(DcMotor.class, "leftBackDrive");
        leftFrontDrive = hardwareMap.get(DcMotor.class, "leftFrontDrive");
        rightFrontDrive = hardwareMap.get(DcMotor.class, "rightFrontDrive");
        leftFlywheel = hardwareMap.get(DcMotorEx.class, "leftFlywheel");
        rightFlywheel = hardwareMap.get(DcMotorEx.class, "rightFlywheel");
        rightActiveIntake = hardwareMap.get(DcMotor.class, "rightactiveIntake");
        leftActiveIntake = hardwareMap.get(DcMotor.class, "rightactiveIntake");


        /// servos
        BallTransfer = hardwareMap.get(Servo.class, "ballTransfer");
        releaseServo = hardwareMap.get(CRServo.class, "ballRelease");

        // Set motor directions
        rightFlywheel.setDirection(DcMotor.Direction.REVERSE);
        leftFlywheel.setDirection(DcMotor.Direction.FORWARD);
        rightFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        rightBackDrive.setDirection(DcMotor.Direction.FORWARD);
        leftFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        leftBackDrive.setDirection(DcMotor.Direction.REVERSE);
        leftActiveIntake.setDirection(DcMotorSimple.Direction.FORWARD);
        rightActiveIntake.setDirection(DcMotorSimple.Direction.FORWARD);
        
        leftFrontDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFrontDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftBackDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightBackDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        
        // ball releaseServo system
        releaseServo.setPower(0);

        // Wait for the driver to pr2ess START
        waitForStart();

            // This loop runs continuously after START is pressed
             while (opModeIsActive()) {
            //should make active intakeMotor start "intaking"
            leftActiveIntake.setPower(1);
            rightActiveIntake.setPower(1);

            // Get joystick values
            double drive = -gamepad1.left_stick_y;  // Controls forward and backward movement
            double strafe = gamepad1.left_stick_x;   // Controls side-to-side movement
            double turn = gamepad1.right_stick_x;    // Controls turning/rotation
            double ballRelease = gamepad1.right_trigger;


            // shooter wheel power
            if (gamepad1.right_bumper) {
                if (getRuntime() - rightBumperLastTime > .075) {
                    if (shooterSpeed < MAX_SHOOTER_SPEED) shooterSpeed += 100;
                    rightBumperLastTime = getRuntime();
                }
            }

            else if (gamepad1.left_bumper) {
                if (getRuntime() - leftBumperLastTime > .075) {
                    if (shooterSpeed > 0) shooterSpeed -= 100;
                    leftBumperLastTime = getRuntime();
                }
            }

            if(gamepad1.a){
                if (!aButtonPressed)
                {
                    aButtonPressed = true;
                    shootersOn = !shootersOn;
                }
            } else aButtonPressed = false;

            if (shootersOn) {
                rightFlywheel.setVelocity(shooterSpeed * 28 / 60); // goBilda yellow jacket 6000rpm motor reads 28 ticks/rev, convert from rev/minute to rev/sec
                leftFlywheel.setVelocity(shooterSpeed * 28 / 60);
            }else {
                rightFlywheel.setVelocity(0);
                leftFlywheel.setVelocity(0);
            }
            // releaseServo
            releaseServo.setPower(ballRelease);
            if (gamepad1.right_trigger > .5 ) {
               releaseServo.setPower(1);
            } else {
                releaseServo.setPower(0);
            }


            // Combine the joystick inputs to calculate the power for each wheel
            double leftFrontPower = drive + strafe + turn;
            double rightFrontPower = drive - strafe - turn;
            double leftBackPower = drive - strafe + turn;
            double rightBackPower = drive + strafe - turn;

            // Normalize the wheel speeds to be between -1 and 1
            double max = Math.max(Math.abs(leftFrontPower), Math.abs(rightFrontPower));
            max = Math.max(max, Math.abs(leftBackPower));
            max = Math.max(max, Math.abs(rightBackPower));

            if (max > 1.0) {
                leftFrontPower /= max;
                rightFrontPower /= max;
                leftBackPower /= max;
                rightBackPower /= max;
            }


            // Send the calculated to the motors
            leftFrontDrive.setPower(leftFrontPower);
            rightFrontDrive.setPower(rightFrontPower);
            leftBackDrive.setPower(leftBackPower);
            rightBackDrive.setPower(rightBackPower);





            // Show the joystick and wheel roundupower values on the Driver Hub screen
            telemetry.addData("Status", "Running");
            telemetry.addData("Drive (Fwd/Rev)", "%.2f", drive);
            telemetry.addData("Strafe (Side)", "%.2f", strafe);
            telemetry.addData("Turn (Rotate)", "%.2f", turn);
            telemetry.addData("Front left/Right Power", "%.2f, %.2f", leftFrontPower, rightFrontPower);
            telemetry.addData("Back  left/Right Power", "%.2f, %.2f", leftBackPower, rightBackPower);
            telemetry.addData("Flywheels ", shootersOn? "ON": "OFF");
            telemetry.addData("Target Flywheel Speed", "%4f", shooterSpeed);
            telemetry.addData("Left/Right Flywheel Speed", "%.0f, %.0f", leftFlywheel.getVelocity() / 28 * 60, rightFlywheel.getVelocity() / 28 * 60);

            telemetry.update();
        }
    }
}
