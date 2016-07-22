package com.ghgande.j2mod.modbus.serial;

import java.io.InputStream;

public interface ModSerialPort {

  //copied from com.fazecast.jSerialComm.SerialPort
  public static final int NO_PARITY = 0;
  public static final int ODD_PARITY = 1;
  public static final int EVEN_PARITY = 2;
  public static final int MARK_PARITY = 3;
  public static final int SPACE_PARITY = 4;

  public static final int ONE_STOP_BIT = 1;
  public static final int ONE_POINT_FIVE_STOP_BITS = 2;
  public static final int TWO_STOP_BITS = 3;

  public static final int FLOW_CONTROL_DISABLED = 0;
  public static final int FLOW_CONTROL_RTS_ENABLED = 1;
  public static final int FLOW_CONTROL_CTS_ENABLED = 16;
  public static final int FLOW_CONTROL_DSR_ENABLED = 256;
  public static final int FLOW_CONTROL_DTR_ENABLED = 4096;
  public static final int FLOW_CONTROL_XONXOFF_IN_ENABLED = 65536;
  public static final int FLOW_CONTROL_XONXOFF_OUT_ENABLED = 1048576;
  public static final int TIMEOUT_NONBLOCKING = 0;
  public static final int TIMEOUT_READ_SEMI_BLOCKING = 1;
  public static final int TIMEOUT_WRITE_SEMI_BLOCKING = 16;
  public static final int TIMEOUT_READ_BLOCKING = 256;
  public static final int TIMEOUT_WRITE_BLOCKING = 4096;
  public static final int TIMEOUT_SCANNER = 65536;
  public static final int LISTENING_EVENT_DATA_AVAILABLE = 1;
  public static final int LISTENING_EVENT_DATA_RECEIVED = 16;
  public static final int LISTENING_EVENT_DATA_WRITTEN = 256;

  boolean openPort();

  void closePort();

  InputStream getInputStream();

  void setComPortParameters(int baudRate, int databits, int stopbits, int parity);

  void setFlowControl(int flowControlSettings);

  int getBaudRate();

  int getNumDataBits();

  int getNumStopBits();

  int getParity();

  boolean isOpen();

  String getDescriptivePortName();

  void setComPortTimeouts(int timeoutMode, int readTimeout, int writeTimeout);

  void setBaudRate(int baud);

  int readBytes(byte[] buffer, long len);

  int writeBytes(byte[] buffer, long bytesToWrite);

  int bytesAvailable();

}
