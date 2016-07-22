package com.ghgande.j2mod.modbus.util;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

public class ModCollections {

  public static Vector getValues(Hashtable ht) {
    Vector results = new Vector(ht.size());
    Enumeration values = ht.elements();
    while (values.hasMoreElements()) {
      results.addElement(values.nextElement());
    }
    return results;
  }

}
