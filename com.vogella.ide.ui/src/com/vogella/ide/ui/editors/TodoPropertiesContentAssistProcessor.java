package com.vogella.ide.ui.editors;

import java.util.Arrays;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.contentassist.CompletionProposal;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.text.contentassist.IContextInformationValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TodoPropertiesContentAssistProcessor implements IContentAssistProcessor {

	private static final Logger logger = LoggerFactory.getLogger(TodoPropertiesContentAssistProcessor.class);

	public static final String[] PROPOSALS = new String[] { "ID:", "Summary:", "Description:", "Done:", "Duedate:",
			"Dependent:" };

	@Override
	public ICompletionProposal[] computeCompletionProposals(ITextViewer viewer, int offset) {

		IDocument document = viewer.getDocument();

		try {
			int lineOfOffset = document.getLineOfOffset(offset);
			int lineOffset = document.getLineOffset(lineOfOffset);

			int lineTextLenght = offset - lineOffset;
			String lineStartToOffsetValue = document.get(lineOffset, lineTextLenght).toLowerCase();

			return Arrays.asList(PROPOSALS).stream()
					.filter(proposal -> !viewer.getDocument().get().contains(proposal)
							&& proposal.toLowerCase().startsWith(lineStartToOffsetValue))
					.map(proposal -> new CompletionProposal(proposal, lineOffset, lineTextLenght, proposal.length()))
					.toArray(ICompletionProposal[]::new);
		} catch (BadLocationException e) {
			logger.error(e.getMessage(), e);
		}
		return new ICompletionProposal[0];
	}

	@Override
	public IContextInformation[] computeContextInformation(ITextViewer viewer, int offset) {
		return null;
	}

	@Override
	public char[] getCompletionProposalAutoActivationCharacters() {
		return null;
	}

	@Override
	public char[] getContextInformationAutoActivationCharacters() {
		return null;
	}

	@Override
	public String getErrorMessage() {
		return null;
	}

	@Override
	public IContextInformationValidator getContextInformationValidator() {
		return null;
	}

}
