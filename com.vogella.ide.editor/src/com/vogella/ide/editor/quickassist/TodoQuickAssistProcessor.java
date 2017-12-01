package com.vogella.ide.editor.quickassist;

import org.eclipse.jface.text.contentassist.CompletionProposal;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.quickassist.IQuickAssistInvocationContext;
import org.eclipse.jface.text.quickassist.IQuickAssistProcessor;
import org.eclipse.jface.text.source.Annotation;

public class TodoQuickAssistProcessor implements IQuickAssistProcessor {

	@Override
	public String getErrorMessage() {
		return null;
	}

	@Override
	public boolean canFix(Annotation annotation) {
		return false;
	}

	@Override
	public boolean canAssist(IQuickAssistInvocationContext invocationContext) {
		return true;
	}

	@Override
	public ICompletionProposal[] computeQuickAssistProposals(IQuickAssistInvocationContext invocationContext) {
		String string = invocationContext.getSourceViewer().getDocument().get();

		return new ICompletionProposal[] { new CompletionProposal("ID:", invocationContext.getOffset(), 0, 3) };
	}

}
