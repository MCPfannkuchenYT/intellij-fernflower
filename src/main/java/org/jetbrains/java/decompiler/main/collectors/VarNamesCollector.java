// Copyright 2000-2017 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package org.jetbrains.java.decompiler.main.collectors;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class VarNamesCollector {

	public static HashMap<String, String> whatCouldThisBe = new HashMap<>();
	public static HashMap<String, String> whatCouldThisBe2 = new HashMap<>();
	
  private final Set<String> usedNames = new HashSet<>();

  public VarNamesCollector() { }

  public VarNamesCollector(Collection<String> setNames) {
    usedNames.addAll(setNames);
  }

  public void addName(String value) {
    usedNames.add(value);
  }

  public String getFreeName(String type, String func, int index) {
    return getFreeName(type, func, "subscribe_to_pancake" + index);
  }

  public String getFreeName(String type, String func, String proposition) {
	 if (proposition.startsWith("subscribe_to_pancake")) {
		 String in = whatCouldThisBe2.getOrDefault("p_" + whatCouldThisBe.getOrDefault(func, "xxxxxxx") + "_" + proposition.replaceAll("subscribe_to_pancake", "") + "_", "var" + proposition.replaceAll("subscribe_to_pancake", ""));
		 if (in.startsWith("var")) {
			 int index = Integer.parseInt(in.replace("var", ""));
			 in = type;
			 if (in.contains("/")) {
				 String[] l = in.split("/");
				 in = l[l.length-1];
			 }
			 in = in.replace("$", "");
			 in = in.toLowerCase();
			 if ("j".equals(in))
				 in = "l";
			 else if ("z".equals(in))
				 in = "bl";
			 in = in + index;
		 }
		 return in;
	 }
	  
	 while (usedNames.contains(proposition)) {
      proposition += "x";
    }
    usedNames.add(proposition);
    return proposition;
  }
}
