package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

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


        //wait until start is pressed on the driver hub/phone
        waitForStart();

        //this code runs starting when start is pressed until stop is pressed
        while(opModeIsActive()){

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






        }

    }
}
