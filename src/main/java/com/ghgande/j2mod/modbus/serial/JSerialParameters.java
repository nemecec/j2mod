package com.ghgande.j2mod.modbus.serial;

import com.ghgande.j2mod.modbus.util.ModProperties;

import java.util.Properties;

public class JSerialParameters extends ModSerialParameters {

  public JSerialParameters() {
    super();
  }

  public JSerialParameters(String portName, int baudRate, int flowControlIn, int flowControlOut, int databits,
    int stopbits, int parity, boolean echo) {
    super(portName, baudRate, flowControlIn, flowControlOut, databits, stopbits, parity, echo);
  }

  public JSerialParameters(ModProperties props, String prefix) {
    super(props, prefix);
  }

  public ModSerialPort getCommPort() {
    return new JSerialPort(getPortName());
  }

}
