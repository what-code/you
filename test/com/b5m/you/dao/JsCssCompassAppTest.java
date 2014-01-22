package com.b5m.you.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.mozilla.javascript.ErrorReporter;
import org.mozilla.javascript.EvaluatorException;

import com.yahoo.platform.yui.compressor.JavaScriptCompressor;

public class JsCssCompassAppTest {

	@Test
	public void testExecute(){
		try {
			this.execute("/home/gsj/workspace1/git_data/b5m-you-new/");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void execute(String projectPath) throws Exception {
		String dir = projectPath + "WebRoot/js";
		InputStream in = new FileInputStream(projectPath + "test/com/b5m/you/dao/jslist.txt");
		List<String> files = IOUtils.readLines(in);
		for (String file : files) {
			String fileName = dir + "/" + file + ".js";
			System.out.println(fileName);
			String js = javaScriptCompressor(new FileInputStream(fileName));
			File minFile = new File(dir + "/" + file + ".min.js");
			if (minFile.exists())
				minFile.delete();
			IOUtils.write(js.getBytes("UTF-8"), new FileOutputStream(minFile));
		}
	}

	public static String javaScriptCompressor(FileInputStream inputStream) throws Exception {
		int linebreakpos = -1;
		boolean munge = true;
		boolean verbose = false;
		boolean preserveAllSemiColons = true;
		boolean disableOptimizations = true;

		InputStreamReader reader = new InputStreamReader(inputStream);
		ErrorReporter reporter = new ErrorReporter() {

			public void warning(String message, String sourceName, int line, String lineSource, int lineOffset) {
				if (line < 0) {
					System.err.println("\n[WARNING] " + message);
				} else {
					System.err.println("\n[WARNING] " + line + ':' + lineOffset + ':' + message);
				}
			}

			public void error(String message, String sourceName, int line, String lineSource, int lineOffset) {
				if (line < 0) {
					System.err.println("\n[ERROR] " + message);
				} else {
					System.err.println("\n[ERROR] " + line + ':' + lineOffset + ':' + message);
				}
			}

			public EvaluatorException runtimeError(String message, String sourceName, int line, String lineSource, int lineOffset) {
				error(message, sourceName, line, lineSource, lineOffset);
				return new EvaluatorException(message);
			}
		};
		JavaScriptCompressor compressor = new JavaScriptCompressor(reader, reporter);
		Writer out = new StringWriter();
		compressor.compress(out, linebreakpos, munge, verbose, preserveAllSemiColons, disableOptimizations);
		return out.toString();
	}
}
