/**
 * 
 */
package com.scriptbasic.interfaces;

/**
 * @author Peter Verhas
 * @date June 14, 2012
 * 
 */
public interface GenericList<Z extends AnalysisResult> extends Iterable<Z>,
		AnalysisResult {
	public void add(Z element);
}
