package com.vogella.ide.editor;

import java.util.Map;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.quickassist.IQuickAssistAssistant;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.ui.editors.text.TextSourceViewerConfiguration;
import org.eclipse.ui.texteditor.ITextEditor;

import com.vogella.ide.editor.quickassist.TodoQuickAssistProcessor;

public class TodoTextViewerConfiguration extends TextSourceViewerConfiguration {

	private ITextEditor textEditor;

	public TodoTextViewerConfiguration(IPreferenceStore preferenceStore, ITextEditor textEditor) {
		super(preferenceStore);
		this.textEditor = textEditor;
	}
	
	@Override
	public IQuickAssistAssistant getQuickAssistAssistant(ISourceViewer sourceViewer) {
		IQuickAssistAssistant quickAssistAssistant = super.getQuickAssistAssistant(sourceViewer);
		
		quickAssistAssistant.setQuickAssistProcessor(new TodoQuickAssistProcessor());
		
		return quickAssistAssistant;
	}
	
	@Override
	protected Map<String, IAdaptable> getHyperlinkDetectorTargets(ISourceViewer sourceViewer) {
		Map<String, IAdaptable> hyperlinkDetectorTargets = super.getHyperlinkDetectorTargets(sourceViewer);
		// TODO add custom target see generic editor sample
		return hyperlinkDetectorTargets;
	}
}
