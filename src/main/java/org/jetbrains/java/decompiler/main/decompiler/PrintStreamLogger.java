// Copyright 2000-2018 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package org.jetbrains.java.decompiler.main.decompiler;

import org.jetbrains.java.decompiler.main.extern.IFernflowerLogger;

public class PrintStreamLogger extends IFernflowerLogger {

	@Override public void startClass(String className) {}
	@Override public void writeMessage(String message, Severity severity) {}
	@Override public void writeMessage(String message, Severity severity, Throwable t) { }
	
}
