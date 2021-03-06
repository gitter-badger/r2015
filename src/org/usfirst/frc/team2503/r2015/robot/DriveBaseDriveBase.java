package org.usfirst.frc.team2503.r2015.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Talon;

import org.usfirst.frc.team2503.r2015.Constants;

public class DriveBaseDriveBase extends DriveBase {
	public Talon left;
	public Talon right;
	public Talon winch;
	
	public Relay upperLights;
	public Relay upperChannelLights;
	public Relay underGlowLights;
	public Relay cameraLightLights;
	
	public DigitalInput winchLowerLimitSwitch;
	public DigitalInput winchUpperLimitSwitch;
	
	public Compressor compressor;
	
	public Clamp clamp;
	
	public void setLeftPort(final int port) { left = new Talon(port); }
	public void setRightPort(final int port) { right = new Talon(port); }
	public void setWinchPort(final int port) { winch = new Talon(port); }
	public void setUpperLightsPort(final int port) { upperLights = new Relay(port, Relay.Direction.kReverse); }
	public void setUpperChannelLightsPort(final int port) { upperChannelLights = new Relay(port, Relay.Direction.kReverse); }
	public void setUnderGlowLightsPort(final int port) { underGlowLights = new Relay(port, Relay.Direction.kReverse); }
	public void setCameraLightLightsPort(final int port) { cameraLightLights = new Relay(port, Relay.Direction.kReverse); }
	public void setupWinchLowerLimitSwitch(final int channel) { winchLowerLimitSwitch = new DigitalInput(channel); }
	public void setupWinchUpperLimitSwitch(final int channel) { winchUpperLimitSwitch = new DigitalInput(channel); }
	public void setCompressorPort(final int port) { compressor = new Compressor(port); }
	
	public boolean indicateWinching = false;
	public boolean indicateDriving = false;

	public void drive(double bothValue) {
		drive(bothValue, bothValue);
	}
	
	public void drive(double leftValue, double rightValue) {
		double totalValue = Math.abs(leftValue) + Math.abs(rightValue);
		
		if(totalValue >= Constants.inputIndicationNullZone || totalValue <= -Constants.inputIndicationNullZone) {
			indicateDriving = true;
		} else {
			indicateDriving = false;
		}
		
		left.set(leftValue * Constants.masterPowerMultiplier);
		right.set(rightValue * Constants.masterPowerMultiplier);
	}

	public void winch(double winchValue) {
		if(winchValue >= Constants.inputIndicationNullZone || winchValue <= -Constants.inputIndicationNullZone) {
			indicateWinching = true;
		} else {
			indicateWinching = false;
		}
		
		winch.set(winchValue);
	}
	
	public DriveBaseDriveBase() {
		setLeftPort(Constants.leftTalonPort);
		setRightPort(Constants.rightTalonPort);
		setWinchPort(Constants.winchTalonPort);
		
		setUpperLightsPort(Constants.upperLightsRelayPort);
		setUpperChannelLightsPort(Constants.upperChannelLightsRelayPort);
		setUnderGlowLightsPort(Constants.underGlowLightsRelayPort);
		setCameraLightLightsPort(Constants.cameraLightLightsRelayPort);
		
		setupWinchLowerLimitSwitch(Constants.winchLowerLimitSwitchChannel);
		setupWinchUpperLimitSwitch(Constants.winchUpperLimitSwitchChannel);
		
		setCompressorPort(Constants.compressorPort);
		
		clamp = new DriveBaseClamp();
	}
	
	public DriveBaseDriveBase(final int leftPort, final int rightPort, final int slipPort, final int winchPort, final int upperLightsPort, final int upperChannelLightsPort, final int underGlowLightsPort, final int cameraLightLightsPort, final int winchLowerLimitSwitchChannel, final int winchUpperLimitSwitchChannel, final int compressorPort) {
		setLeftPort(leftPort);
		setRightPort(rightPort);
		setWinchPort(winchPort);
		
		setUpperLightsPort(upperLightsPort);
		setUpperChannelLightsPort(upperChannelLightsPort);
		setUnderGlowLightsPort(underGlowLightsPort);
		setCameraLightLightsPort(cameraLightLightsPort);
		
		setupWinchLowerLimitSwitch(winchLowerLimitSwitchChannel);
		setupWinchUpperLimitSwitch(winchUpperLimitSwitchChannel);
		
		setCompressorPort(compressorPort);
		
		clamp = new DriveBaseClamp();
	}
}
