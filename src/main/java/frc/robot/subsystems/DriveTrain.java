/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.TalonFXSensorCollection;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import io.github.pseudoresonance.pixy2api.Pixy2;
import io.github.pseudoresonance.pixy2api.Pixy2.LinkType;

public class DriveTrain extends SubsystemBase {

  private TalonFX leftMotor;
  private TalonFX rightMotor;
  private TalonFX leftMotor2;
  private TalonFX rightMotor2;

  private DoubleSolenoid shifter = new DoubleSolenoid(1, 0, 1);

  private Limelight lm = new Limelight(); // lm = limelight

  private double DRIVE_K = 0.1;
  private double turnK = -0.01;

  private double targetArea = 3.7;

  public DriveTrain() {
    leftMotor = new TalonFX(Constants.DriveConstrants.leftMotor);
    rightMotor = new TalonFX(Constants.DriveConstrants.rightMotor);
    leftMotor2 = new TalonFX(Constants.DriveConstrants.leftMotor2);
    rightMotor2 = new TalonFX(Constants.DriveConstrants.rightMotor2);

    leftMotor2.follow(leftMotor);
    rightMotor2.follow(rightMotor);

    leftMotor.setInverted(false);
    rightMotor.setInverted(true);

    //leftMotor.setInverted(false);
    //rightMotor.setInverted(false);
    //leftMotor2.setInverted(false);
    //rightMotor2.setInverted(false);

    leftMotor.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
  }

  public void turnMotors(double left, double right) {
    //leftMotor.set(ControlMode.PercentOutput, left);
    //rightMotor.set(ControlMode.PercentOutput, -right); // -
    //leftMotor2.set(ControlMode.PercentOutput, left);
    //rightMotor2.set(ControlMode.PercentOutput, -right); // -
    leftMotor.set(ControlMode.PercentOutput, left);
    rightMotor.set(ControlMode.PercentOutput, right);
  }

  public void targetArea()
  {
    double drive_cmd = (targetArea - lm.getTa()) * DRIVE_K;   // Taking the area I want and subracting the actual area. Drive K turning it into motor output

    double leftPower = turnK * lm.getTx();  //Power of turning and multiplying by tx value, distance in X direction
    double rightPower = -turnK * lm.getTx(); //Power of turning and multiplying by tx value, distance in X direction

    turnMotors(leftPower + drive_cmd, rightPower + drive_cmd);   //Connecting to driveTrain motors
  }

  public void stopDriveMotors() {
    turnMotors(0, 0);
  }

  public void Shifters()
  {
     if(shifter.get() == Value.kForward)
     {
       shifter.set(Value.kReverse);
     } else {
       shifter.set(Value.kForward);
     }
  }

  public void moveForward()
  {
    int leftMotorEncoder = leftMotor.getSelectedSensorPosition(0);
    if (leftMotorEncoder < 10000)
    {
      System.out.printf("Drive Encoder %d |", leftMotorEncoder);
      turnMotors(.25, .25);
    } else {
      stopDriveMotors();
    }
  }

  public void moveBack()
  {
    int leftMotorEncoder = leftMotor.getSelectedSensorPosition(0);
    if(leftMotorEncoder > 10000)
    {
      turnMotors(-.25, -.25);
    } else {
      stopDriveMotors();
    }
  }



  //@Override
  public void periodic() {
    //leftMotor.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
    int leftMotorEncoder = leftMotor.getSelectedSensorPosition(0);
    SmartDashboard.putNumber("Left Encoder", leftMotorEncoder);


//do {
  //turnMotors(.25, .25);
//} while(leftMotorEncoder < 1643);


  }
}
