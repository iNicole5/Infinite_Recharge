/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Climber extends SubsystemBase {

  VictorSPX irrectionMotor;

  public Climber() {
    irrectionMotor = new VictorSPX(Constants.ClimberConstrants.irrectionmotor);
  }

  public void irrection(boolean direction)
  {
    if(direction)
    {
      irrectionMotor.set(ControlMode.PercentOutput, 1);
    } else {
      irrectionMotor.set(ControlMode.PercentOutput, -1);
    }
  }

  public void climberStop()
  {
    irrectionMotor.set(ControlMode.PercentOutput, 0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
