// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.Constants;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class Drivetrain extends SubsystemBase{ 
  private final WPI_TalonSRX leftDriveTalon;
  private final WPI_TalonSRX rightDriveTalon;
  private AHRS navx = new AHRS (SPI.Port.kMXP);
  


  public Drivetrain() {
    // Initialize the Talon motor controllers in the constructor
    leftDriveTalon = new WPI_TalonSRX(Constants.DriveTrainPort.leftDriveTalonPort);
    rightDriveTalon = new WPI_TalonSRX(Constants.DriveTrainPort.rightDriveTalonPort);
    leftDriveTalon.setNeutralMode(NeutralMode.Coast);
    rightDriveTalon.setNeutralMode(NeutralMode.Coast);
    leftDriveTalon.setSensorPhase(true);
    rightDriveTalon.setSensorPhase(true);

    leftDriveTalon.configFactoryDefault();
    leftDriveTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,0,10);
    rightDriveTalon.configFactoryDefault();
    rightDriveTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,0,10);
    
  

    // Optionally, initialize the navX if needed
    // navx = new AHRS(SPI.Port.kMXP); // Example for SPI
}

  public void tankDrive(double leftSpeed, double rightSpeed) {
    // You can implement tank driving logic here
    leftDriveTalon.set(leftSpeed);
    rightDriveTalon.set(rightSpeed);
    
}
  public void reset(){
    navx.reset();
  }
  public double getAngle(){
    return navx.getAngle();
  }
  
  public double getTicks(){
    return ((leftDriveTalon.getSelectedSensorPosition(0)+rightDriveTalon.getSelectedSensorPosition(0))/2);
  }
  public double getMeters(){
    return (getTicks()/4096)*Units.inchesToMeters(6)*Math.PI;
  }
  public void resetEnc(){
    leftDriveTalon.setSelectedSensorPosition(0,0,10);
    rightDriveTalon.setSelectedSensorPosition(0,0,10);
  }




  
  @Override
  public void periodic() {
    SmartDashboard.putNumber("L rizz volt", leftDriveTalon.getMotorOutputPercent());
    SmartDashboard.putNumber("R idfk volt", rightDriveTalon.getMotorOutputPercent());
    SmartDashboard.putNumber("ngle",navx.getAngle());
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
