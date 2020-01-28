/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {

  public TalonSRX shooterMaster;
  public VictorSPX shooterSlave;

  TalonSRX hoodMotor;

  Encoder shooterEncoder;
  Encoder hoodEncoder;

  public Shooter() {
    shooterMaster = new TalonSRX(Constants.ShooterConstrants.shooterMotorMaster);
    shooterSlave = new VictorSPX(Constants.ShooterConstrants.shooterMotorMaster);

    shooterSlave.follow(shooterMaster);

    hoodMotor = new TalonSRX(Constants.ShooterConstrants.hoodMotor);

    initShooterEncoder();
    initHoodEncoder();
  }
  //test

  private void initShooterEncoder()
  {
    shooterEncoder = new Encoder(2, 3, false, EncodingType.k4X);
    shooterEncoder.setSamplesToAverage(5);
    shooterEncoder.reset();
  }

  private void initHoodEncoder()
  {
    hoodEncoder = new Encoder(4, 5, false, EncodingType.k4X);
    hoodEncoder.setSamplesToAverage(5);
    hoodEncoder.reset();
  }

  public void resetShooterEncoder()
  {
    shooterEncoder.reset();
  }

  public void resetHoodEncoder()
  {
    hoodEncoder.reset();
  }

  public double getShooterEncoder() {
    return shooterEncoder.getDistance();
  }

  public double getHoodEncoder() {
    return hoodEncoder.getDistance();
  }

  public void shooterMove(double turn)
  {
    shooterMaster.set(ControlMode.PercentOutput, turn);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Elevator Encoder", getShooterEncoder());

  }
}
