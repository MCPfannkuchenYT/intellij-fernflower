// Copyright 2000-2017 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package org.jetbrains.java.decompiler.main.collectors;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class VarNamesCollector {

	public static Map<String, String> params = new HashMap<>();
	
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
		 String id = proposition.replaceAll("subscribe_to_pancake", "");
		 String in = params.getOrDefault("param_" + func + id, "var" + id);
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
