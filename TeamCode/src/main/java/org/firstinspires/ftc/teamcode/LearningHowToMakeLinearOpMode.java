package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@TeleOp(name = "LearnLinearOpMode")

public class LearningHowToMakeLinearOpMode extends LinearOpMode {

    //create objects
    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor backLeft;
    DcMotor backRight;
    DcMotor motor;
    Servo servo;
    CRServo servoContinuous;
    IMU imu;
    IMU.Parameters parameters;

    @Override
    public void runOpMode() {

        //configure motors
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");

        motor = hardwareMap.get(DcMotor.class, "motor");
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //configure servos
        servo = hardwareMap.get(Servo.class, "servo");
        servoContinuous = hardwareMap.get(CRServo.class, "servoContinuous");

        //configure imu
        imu = hardwareMap.get(IMU.class, "imu");
        parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD
        ));
        imu.initialize(parameters);

        //wait until start is pressed on the driver hub/phone
        waitForStart();

        //this code runs starting when start is pressed until stop is pressed
        while(opModeIsActive()){
            //get robot heading
            double yaw = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES);

            //tank drive
            frontLeft.setPower(gamepad1.left_stick_y);
            backLeft.setPower(gamepad1.left_stick_y);
            frontRight.setPower(gamepad1.right_stick_y);
            backRight.setPower(gamepad1.right_stick_y);

            //run motor to encoder tick mark
            if(gamepad1.a){
                motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                motor.setTargetPosition(100);
                motor.setPower(0.3);
                motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            }

            //run servo
            if(gamepad1.b){
                servo.setPosition(0.5);
            }

            //run continuous servo
            while(gamepad1.x){
                servoContinuous.setPower(1);
            }
            servoContinuous.setPower(0);

            //check heading
            if(yaw > 30 && yaw < 60){
                telemetry.addData("Heading Status:", "In Position");
            }else{
                telemetry.addData("Heading Status", "Not In Position");
            }

            telemetry.update();
        }

    }
}
