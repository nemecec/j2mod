package com.ghgande.j2mod.modbus.util;

import java.util.Hashtable;

public class ModProperties {

  private final Hashtable values;

  public ModProperties() {
    this(new Hashtable());
  }

  public ModProperties(Hashtable values) {
    this.values = values;
  }

  public String getProperty(String key) {
    return getProperty(key, null);
  }

  public String getProperty(String key, String defaultValue) {
    String v = (String) values.get(key);
    return v != null ? v : defaultValue;
  }

}
