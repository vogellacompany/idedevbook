package com.vogella.ide.editor;

import org.eclipse.ui.editors.text.TextEditor;

public class TodoTextEditor extends TextEditor {
	private TodoTextViewerConfiguration todoTextViewerConfiguration;

	public TodoTextEditor() {
		todoTextViewerConfiguration = new TodoTextViewerConfiguration(getPreferenceStore(), this);
		setSourceViewerConfiguration(todoTextViewerConfiguration);
	}
}
