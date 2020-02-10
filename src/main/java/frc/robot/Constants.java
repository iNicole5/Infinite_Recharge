/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static final class DriveConstrants {
        public static final int leftMotor = 3; //3   //Comp Bot = 14
        public static final int rightMotor = 1; //1   //Comp Bot = 2
        public static final int leftMotor2 = 2; //2  //Comp Bot = 15
        public static final int rightMotor2 = 0; //0  ////Comp Bot = 0
    }

    public static final class ShooterConstrants {
        public static final int shooterMotorMaster = 6; //4  //Comp Bot = 6
        public static final int shooterMotorSlave = 4; //5    //Comp Bot = 4

        public static final int hoodMotor = 8;  //7     //Comp Bot = 8
    }

    public static final class IntakeConstrants {
        public static final int intake = 3;  //8      //Comp Bot = 3
        public static final int ballStorageMotor = 5; //6     //Comp Bot = 5
    }

    public static final class ClimberConstrants {
        public static final int irrectionmotor = 9;
    }

    public static final class OIConstrants {
        public static final int DriverController = 0;
    }
}
